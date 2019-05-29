package com.example.demo.web;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.demo.model.DeliveryAddress;
import com.example.demo.payload.EditUserInfoRequest;
import com.example.demo.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.VerificationToken;
import com.example.demo.payload.LoginRequest;
import com.example.demo.payload.SignUpRequest;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import com.example.demo.service.VerificationTokenService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private VerificationTokenService tokenService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		User user = userService.findByUsernameOrEmail(loginRequest.getUsernameOrEmail());
		if (user != null) {
			if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
				if (!user.isEnabled()) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account is not activated!");
				}
				String jwt = jwtService.generateToken(user.getUserId(), user.getUsername());
				return ResponseEntity.ok(jwt);
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or email");
	}

	@PutMapping("/my-profile/change-ava")
	public ResponseEntity<?> changeAvatar(HttpServletRequest request, @RequestPart(value="id") String user_id,
			@RequestPart(value="ava")MultipartFile file){
		User user = userService.findById(Long.parseLong(user_id));
		try {
			byte[] ava = file.getBytes();
			String path = request.getSession().getServletContext().getRealPath("/")+"resources\\images\\users\\"+file.getOriginalFilename();
			OutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
			stream.write(ava);
			stream.close();
			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/img/users/{filename}")
					.buildAndExpand(file.getOriginalFilename()).toUri();
			user.setAvatar(location.toString());
			User user1 = userService.save(user);
			return ResponseEntity.ok(user1.getAvatar());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().body("VCL");
	}

	@PutMapping("/my-profile/edit-info")
	public ResponseEntity<?> editProfile(HttpServletRequest request, @RequestBody EditUserInfoRequest user){
		String token = request.getHeader("Authorization");
		Long userId = jwtService.getUserIdFromToken(token);
		User u = userService.findById(userId);
		if (u==null){
			return ResponseEntity.badRequest().body("The user is no longer exist");
		}else{
			u.setPhoneNumber(user.getPhoneNumber());
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			u.setBirthDate(user.getBirthDate());
			u.setAddress(user.getAddress());
			u= userService.save(u);
			return ResponseEntity.ok(u);
		}
	}

	@GetMapping("/all-users")
	public ResponseEntity<?> allUser() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/my-profile/get-info")
	public ResponseEntity<?> myProfile(HttpServletRequest request){
		String token = request.getHeader("Authorization");
		Long userId = jwtService.getUserIdFromToken(token);
		User u = userService.findById(userId);
		if (u==null){
			return ResponseEntity.badRequest().body("The user is no longer exist");
		}else{
			return ResponseEntity.ok(u);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity("Request payload invalid!", HttpStatus.BAD_REQUEST);
		}
		if (userService.findByUsername(signUpRequest.getUsername()) != null) {
			return new ResponseEntity("Username is already taken!", HttpStatus.BAD_REQUEST);
		}
		if (userService.findByUserEmail(signUpRequest.getEmail()) != null) {
			return new ResponseEntity("Email Address already in use!", HttpStatus.BAD_REQUEST);
		}
		// Creating user's account
		User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getUsername(),
				signUpRequest.getEmail(), signUpRequest.getPassword());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setEnabled(false);

		user.setCreateDate(new Date(System.currentTimeMillis()));

		Set<VerificationToken> tokens = new HashSet<>();

		VerificationToken token = new VerificationToken(user);

		tokens.add(token);

		user.setVerificationTokens(tokens);

		Set<Role> userRole = new HashSet<>();

		userRole.add(new Role(1l, "ROLE_USER"));

		user.setRoles(userRole);

		User result = userService.save(user);

		Properties props = new Properties();
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("doandung270160@gmail.com", "buidoandung123");
			}
		});

		Message verificationEmail = new MimeMessage(session);
		try {
			verificationEmail.setSubject("Verification email");
			verificationEmail.setSentDate(new Date(System.currentTimeMillis()));
			verificationEmail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
			verificationEmail.setText("You have registerd successfully, please click here:\n"
					+ "http://localhost:8080/users/verification?token=" + token.getToken() + " to verify your account\n"
					+ "Your verification token will expire in one day.");
			Transport.send(verificationEmail);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location)
				.body("User registered successfully! Waiting for verification!Please Check Your Email!");
	}

	@GetMapping("/verification")
	public ResponseEntity<?> verifyAccount(@NotNull @RequestParam(name = "token") String token) {
		VerificationToken vToken = tokenService.findByToken(token);
		if (vToken == null) {
			return ResponseEntity.badRequest().body("Wrong token!");
		}

		if (vToken.getTokenExDate().before(new Date(System.currentTimeMillis()))) {
			return ResponseEntity.badRequest().body("Token Expired!");
		}

		User user = userService.findById(vToken.getUser().getUserId());
		user.setEnabled(true);
		userService.save(user);

		return ResponseEntity.ok("Verification Successfully");
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, true));
	}
}

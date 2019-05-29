package com.example.demo.service;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.security.UserPrincipal;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
@PropertySource(value = {"classpath:application.properties"})
public class JwtService {

	public final String ID = "ID" ;

	public final String SECRET = "711AD345RJDJSDSDdfhaskdkhasgdahsdsadasdasdasdasdasdasdasdasdasdasdasdJSD7868";

	public final Long EXP_TIME = 864000000l;


	public String generateToken(Long userId,String username) {
		try {
			
			JWSSigner jwsSigner = new MACSigner(SECRET);

			JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
			builder.claim(ID, userId);
			builder.claim("USERNAME", username);
			builder.expirationTime(new Date(System.currentTimeMillis() + EXP_TIME));

			JWTClaimsSet claimsSet = builder.build();

			SignedJWT jwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

			jwt.sign(jwsSigner);

			return jwt.serialize();
		} catch (KeyLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private JWTClaimsSet getClaimSetFromToken(String token) {
		try {
			SignedJWT jwt = SignedJWT.parse(token);
			JWSVerifier verifier = new MACVerifier(SECRET);
			if (jwt.verify(verifier)) {
				return jwt.getJWTClaimsSet();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Long getUserIdFromToken(String token) {
		JWTClaimsSet claimsSet = getClaimSetFromToken(token);
		if (token != null) {
			try {
				return claimsSet.getLongClaim(ID);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public Date getExpirationDateFromToken(String token) {
		JWTClaimsSet claimsSet = getClaimSetFromToken(token);
		if (token != null) {
			return claimsSet.getExpirationTime();
		}
		return null;
	}

	public boolean isTokenExpired(String token) {
		Date exDate = getExpirationDateFromToken(token);
		return exDate.before(new Date());
	}

	public boolean validate(String token) {
		if (token == null || token.equals("") || token.length() <= 0) {
			return false;
		}
		Long userId = getUserIdFromToken(token);
		if (userId < 0 || userId == null) {
			return false;
		}
		if (isTokenExpired(token))
			return false;
		
		return true;
	}
}

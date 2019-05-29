package com.example.demo.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Oder implements Serializable{

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "address_to")
    private String address;

    @Column(name = "contact_phone")
    private String phone;

    @Column(name = "customer_full_name")
    private String customerFullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_address_type")
    private AddressType customerAddressType;

    @ManyToOne
    @JoinColumn(name = "payment_method")
    private PaymentMethod paymentMethod;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<OrderDetail> orderDetails;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_shipped")
    private Date dateShipped;

    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_status")
    private ShippingStatus shippingStatus;

    @Column(name = "is_paid",columnDefinition = "TINYINT",length = 1)
    private boolean paid;

    @Column(name = "charge_id")
    private String chargeId;

    public Oder(User user, String address, String phone, String customerFullName,
                AddressType customerAddressType, PaymentMethod paymentMethod, Set<OrderDetail> orderDetails,
                Date dateCreated, Date dateShipped, Date paymentDate, boolean paid, ShippingStatus shippingStatus) {
        this.user = user;
        this.address = address;
        this.phone = phone;
        this.customerFullName = customerFullName;
        this.customerAddressType = customerAddressType;
        this.paymentMethod = paymentMethod;
        this.orderDetails = orderDetails;
        this.dateCreated = dateCreated;
        this.dateShipped = dateShipped;
        this.paymentDate = paymentDate;
        this.paid = paid;
        this.shippingStatus = shippingStatus;
    }

    public Oder() {
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public AddressType getCustomerAddressType() {
        return customerAddressType;
    }

    public void setCustomerAddressType(AddressType customerAddressType) {
        this.customerAddressType = customerAddressType;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}

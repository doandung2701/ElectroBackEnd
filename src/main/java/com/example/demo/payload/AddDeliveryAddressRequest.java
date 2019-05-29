package com.example.demo.payload;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.io.Serializable;

public class AddDeliveryAddressRequest implements Serializable {

    @NotNull
//    @Pattern(regexp = "\\w+\\s+\\w+")
    private String fullName;

    @NotNull
    private String address;

    @NotNull
    private Long addressTypeId;

    @NotNull
    @Length(min = 9,max = 15)
//    @Pattern(regexp = "\\d*")
    private String phone;

    @Nullable
    private Long addressId;

    public AddDeliveryAddressRequest() {
    }

    public AddDeliveryAddressRequest(String fullName, String address, Long addressTypeId, String phone) {
        this.fullName = fullName;
        this.address = address;
        this.addressTypeId = addressTypeId;
        this.phone = phone;
    }

    public AddDeliveryAddressRequest(@NotNull String fullName, @NotNull String address, @NotNull Long addressTypeId,
                                     @NotNull @Length(min = 9, max = 15) String phone, @Nullable Long addressId) {
        this.fullName = fullName;
        this.address = address;
        this.addressTypeId = addressTypeId;
        this.phone = phone;
        this.addressId = addressId;
    }

    @Nullable
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(@Nullable Long addressId) {
        this.addressId = addressId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAddressTypeId() {
        return addressTypeId;
    }

    public void setAddressTypeId(Long addressTypeId) {
        this.addressTypeId = addressTypeId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

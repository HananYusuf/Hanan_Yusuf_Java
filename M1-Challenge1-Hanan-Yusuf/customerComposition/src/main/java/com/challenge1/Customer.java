package com.challenge1;

public class Customer {

    private String firstName;

    private String lastName;

    private String email;

    private int PhoneNumber;


    private String shippingAddress;

    private String street1;

    private String street2;

    private String city;

    private String state;

    private String zip;


    private String billingAddress;

//    private String street1;
//
//    private String street2;
//
//    private String city;
//
//    private String state;
//
//    private String zip;

    private boolean customerIsARewardsMember;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public boolean isCustomerIsARewardsMember() {
        return customerIsARewardsMember;
    }

    public void setCustomerIsARewardsMember(boolean customerIsARewardsMember) {
        this.customerIsARewardsMember = customerIsARewardsMember;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", PhoneNumber=" + PhoneNumber +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", customerIsARewardsMember=" + customerIsARewardsMember +
                '}';
    }
}

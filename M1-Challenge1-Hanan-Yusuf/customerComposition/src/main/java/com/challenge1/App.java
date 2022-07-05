package com.challenge1;

public class App {

    public static void main(String[] args) {

        Customer customer1 = new Customer();

        customer1.setFirstName("Hanan");
        customer1.setLastName("Yusuf");
        customer1.setEmail("hy@limo.com");
        customer1.setPhoneNumber("333-444-555");
        customer1.setCustomerIsARewardsMember(true);

        Address address = new Address();

        address.setStreet1("888 candy ter");
        address.setStreet2("678 dr");
        address.setCity("Richmond");
        address.setState("Tx");
        address.setZip("81093");

        System.out.println(address);

        customer1.setBillingAddress(address);
        customer1.setShippingAddress(address);

        System.out.println(customer1);
    }

}

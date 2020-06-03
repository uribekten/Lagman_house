package com.devxschool.food_delivery.models;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

public class RegistrationData {

    private String username;
    private String password;

    private String fullName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public CustomUser toCustomUser(PasswordEncoder passwordEncoder) {
        UserProfile userProfile = new UserProfile();
        userProfile.setFullName(fullName);
        userProfile.setAddress(address);
        userProfile.setCity(city);
        userProfile.setState(state);
        userProfile.setZip(zip);
        userProfile.setPhone(phone);

        CustomUser customUser = new CustomUser();
        customUser.setUserProfile(userProfile);
        customUser.setCredentialsNonExpired(true);
        customUser.setAccountNonExpired(true);
        customUser.setAccountNonLocked(true);
        customUser.setEnabled(true);
        customUser.setUsername(username);
        customUser.setPassword(passwordEncoder.encode(password));
        return customUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

package com.conestoga.APIHousing.dtos;

import java.time.LocalDate;

public class AccountDTO{
    private Long id;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String fcm;
    private String collegeName;
    private String studentId;
    private String postalCode;


    // Constructors
    public AccountDTO() {
    }

    public AccountDTO(Long id,  String password, String email, String firstName, String lastName,
                      String phoneNumber, String address, LocalDate dateOfBirth, String fcm, String collegeName, String studentId, String postalCode) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.fcm = fcm;
        this.collegeName = collegeName;
        this.studentId = studentId;
        this.postalCode = postalCode;

    }

    public AccountDTO( String password, String email, String firstName, String lastName,
                      String phoneNumber, String address, LocalDate dateOfBirth, String fcm, String collegeName, String studentId, String postalCode) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.fcm = fcm;
        this.collegeName = collegeName;
        this.studentId = studentId;
        this.postalCode = postalCode;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setFcm(String fcm) {
        this.fcm = fcm;
    }

    public String getFcm() {
        return fcm;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AccountDTO copy() {
        return new AccountDTO(id, password, email, firstName, lastName, phoneNumber, address, dateOfBirth, fcm, collegeName, studentId, postalCode);
    }
}

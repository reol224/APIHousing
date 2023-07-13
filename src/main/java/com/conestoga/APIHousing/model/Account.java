package com.conestoga.APIHousing.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "user_sequence", sequenceName = "users_seq", allocationSize = 1)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "fcm")
    private String fcm;

    @Column(name = "college_name")
    private String collegeName;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "postal_code")
    private String postalCode;

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return id;
    }

    public Account setUserId(Long userId) {
        this.id = userId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFcm() {
        return fcm;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        password = new BCryptPasswordEncoder().encode(password);
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setFcm(String fcm) {
        this.fcm = fcm;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}

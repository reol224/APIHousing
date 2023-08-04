package com.conestoga.APIHousing.model.booking;

import javax.persistence.*;

@Entity
@Table(name = "bookable_object")
public class BookableObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String type;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "is_booked")
    private boolean booked;

    //constructor, getters, setters, etc.
    public BookableObject() {
    }

    public BookableObject(String name, String description, String type, String imgUrl, boolean booked) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.imgUrl = imgUrl;
        this.booked = booked;
    }

    public BookableObject(Long id, String name, String description, String type, String imgUrl, boolean booked) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.imgUrl = imgUrl;
        this.booked = booked;
    }

    //getters and setters
    public Long getId() {
        return id; }

    public void setId(Long id) {
        this.id = id; }

    
    public boolean isBooked() {
        return booked; }

    public void setBooked(boolean booked) {
        this.booked = booked; }

    public String getName() {
        return name; }

    public void setName(String name) {
        this.name = name; }

    
    public boolean getBooked() {
        return booked; }

    public void setBooked(Boolean booked) {
        this.booked = booked; }

    public String getDescription() {
        return description; }

    public void setDescription(String description) {
        this.description = description; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getImgUrl() { return imgUrl; }

    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }


}
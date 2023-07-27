package com.conestoga.APIHousing.model.booking;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.conestoga.APIHousing.model.Account; 

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private BookableObject item;

    @Column(name = "booked_from")
    private LocalDate bookedFrom;

    @Column(name = "booked_to")
    private LocalDate bookedTo;

    public Booking() {
    }

    public Booking(Long id, Account user, BookableObject item, LocalDate bookedFrom, LocalDate bookedTo) {
        this.id = id;
        this.user = user;
        this.item = item;
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public BookableObject getItem() {
        return item;
    }

    public void setItem(BookableObject item) {
        this.item = item;
    }

    public LocalDate getBookedFrom() {
        return bookedFrom;
    }

    public void setBookedFrom(LocalDate bookedFrom) {
        this.bookedFrom = bookedFrom;
    }

    public LocalDate getBookedTo() {
        return bookedTo;
    }

    public void setBookedTo(LocalDate bookedTo) {
        this.bookedTo = bookedTo;
    }

}
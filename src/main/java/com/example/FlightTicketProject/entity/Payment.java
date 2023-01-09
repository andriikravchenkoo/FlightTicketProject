package com.example.FlightTicketProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PAYMENTS")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "SUM")
    private double sum;

    @Column(name = "DATE")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private PaymentStatus status;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private Ticket ticket;

    public Payment(String owner, double sum, LocalDate date) {
        this.owner = owner;
        this.sum = sum;
        this.date = date;
    }
}

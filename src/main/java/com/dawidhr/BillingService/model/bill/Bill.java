package com.dawidhr.BillingService.model.bill;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String description;

    @Column(name="creation_date",nullable = false)
    private LocalDateTime creationDate;

    @Column(name="buy_date",nullable = false)
    private LocalDateTime buyDate;

    @Column(name="total_price",nullable = false)
    private Double totalPrice;

    @OneToMany(mappedBy = "bill")
    private List<BillItem> items;

}

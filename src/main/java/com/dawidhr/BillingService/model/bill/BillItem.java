package com.dawidhr.BillingService.model.bill;

import com.dawidhr.BillingService.dto.bill.BillItemDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class BillItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    public static BillItem create(BillItemDto itemDto) {
        return BillItem.builder()
                .name(itemDto.getName())
                .price(itemDto.getPrice())
                .build();
    }
}

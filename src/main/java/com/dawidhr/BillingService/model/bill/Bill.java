package com.dawidhr.BillingService.model.bill;

import com.dawidhr.BillingService.dto.bill.BillDto;
import com.dawidhr.BillingService.dto.bill.BillItemDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String description;

    @Column(name="creation_date",nullable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name="buy_date",nullable = false)
    private LocalDateTime buyDate;

    @Column(name="total_price",nullable = false)
    private Double totalPrice;

    @OneToMany(mappedBy = "bill")
    private List<BillItem> items = new ArrayList<>();

    public static Bill create(BillDto billDto) {
        return Bill.builder()
                .name(billDto.getName())
                .description(billDto.getDescription())
                .buyDate(billDto.getBuyDate())
                .totalPrice(billDto.getTotalPrice())
                .items(assignItems(billDto.getItems()))
                .build();
    }

    private static List<BillItem> assignItems(List<BillItemDto> itemDtos) {
        List<BillItem> items = new ArrayList<>();
        for(BillItemDto itemDto: itemDtos) {
            BillItem singleItem = BillItem.create(itemDto);
            items.add(singleItem);
        }

        return items;
    }
}

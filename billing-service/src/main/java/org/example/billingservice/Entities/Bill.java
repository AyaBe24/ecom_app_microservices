package org.example.billingservice.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import org.example.billingservice.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private long customerId;
    @OneToMany(mappedBy = "bill")
    @Builder.Default
    private List<ProductItem> productItems = new ArrayList<>();
    @Transient
    private Customer customer;

    public double getTotal() {
        double total = 0;
        for (ProductItem productItem : productItems) {
            total += productItem.getUnitPrice() * productItem.getQuantity();
        }
        return total;
    }
}
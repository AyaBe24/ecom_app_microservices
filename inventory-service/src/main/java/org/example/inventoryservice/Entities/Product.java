package org.example.inventoryservice.Entities;


import jakarta.persistence.*;
import lombok.*;

@Entity @NoArgsConstructor @AllArgsConstructor @Data @Builder @ToString
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;
}

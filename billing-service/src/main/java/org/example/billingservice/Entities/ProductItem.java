package org.example.billingservice.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.ToString;
import org.example.billingservice.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItem {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Bill bill;
    private int quantity;
    private double unitPrice;
    @Transient
    // indiquer qu’un champ d’une entité ne doit pas être persisté dans la base de
    // données
    private Product product;

    public double getAmount() {
        return unitPrice * quantity;
    }
}
// WRITE_ONLY signifie que :
// Le champ peut être envoyé depuis le client vers le serveur (désérialisation).
// Le champ ne sera jamais renvoyé au client dans la réponse JSON
// (sérialisation).
// Autrement dit : le client peut écrire la valeur, mais ne peut pas la lire via
// l’API.
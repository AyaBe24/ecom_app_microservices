package org.example.billingservice.Repository;


import org.example.billingservice.Entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
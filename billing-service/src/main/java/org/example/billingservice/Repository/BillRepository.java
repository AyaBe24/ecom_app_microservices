package org.example.billingservice.Repository;

import org.example.billingservice.Entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


public interface BillRepository extends JpaRepository<Bill, Long> {
    @RestResource(path = "byCustomerID")
    List<Bill> findByCustomerId(@Param("customerId") Long customerId);
}
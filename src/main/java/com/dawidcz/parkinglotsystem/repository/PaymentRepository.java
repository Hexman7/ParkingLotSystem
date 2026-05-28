package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment,Long> {
}

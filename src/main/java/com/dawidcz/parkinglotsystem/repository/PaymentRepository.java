package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.Payment;
import com.dawidcz.parkinglotsystem.model.PaymentStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment,Long> {


    Payment getPaymentByIdAndParkingLotId(Long paymentId, int parkingLotId);

    List<Payment> findByParkingLotId(int parkingLotId);

    List<Payment> getPaymentByStatus(PaymentStatus status);
}

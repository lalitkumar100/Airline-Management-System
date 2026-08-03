package com.crimsonlogic.arilinemanangmentsystem.mapper;

import com.crimsonlogic.arilinemanangmentsystem.model.Payment;
import com.crimsonlogic.arilinemanangmentsystem.model.Refund;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface PaymentMapper {
    // Payment CRUD
    void insertPayment(Payment payment);
    Payment selectPaymentById(@Param("paymentId") String paymentId);
    void updatePayment(Payment payment);

    // Refund CRUD
    void insertRefund(Refund refund);
    Refund selectRefundById(@Param("refundId") String refundId);
    List<Refund> selectAllRefunds();
    String selectMaxPaymentId();
}

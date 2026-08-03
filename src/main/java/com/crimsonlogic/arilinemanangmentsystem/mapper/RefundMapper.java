package com.crimsonlogic.arilinemanangmentsystem.mapper;

import com.crimsonlogic.arilinemanangmentsystem.model.Refund;
import java.util.List;

public interface RefundMapper {
    void insertRefund(Refund refund);
    Refund selectRefundById(String refundId);
    List<Refund> selectAllRefunds();
    List<Refund> selectRefundsByFlightId(String flightId);
}

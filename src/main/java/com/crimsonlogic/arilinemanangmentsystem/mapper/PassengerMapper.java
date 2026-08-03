package com.crimsonlogic.arilinemanangmentsystem.mapper;

import com.crimsonlogic.arilinemanangmentsystem.model.Passenger;
import com.crimsonlogic.arilinemanangmentsystem.model.LoyaltyAccount;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface PassengerMapper {
    // Passenger operations
    void insertPassenger(Passenger passenger);
    Passenger selectPassengerById(@Param("passengerId") String passengerId);
    Passenger selectPassengerByEmail(@Param("email") String email);
    List<Passenger> selectAllPassengers();
    void updatePassenger(Passenger passenger);
    void deletePassenger(@Param("passengerId") String passengerId);

    // Loyalty Account operations
    void insertLoyaltyAccount(LoyaltyAccount loyaltyAccount);
    void updateLoyaltyAccount(LoyaltyAccount loyaltyAccount);
    LoyaltyAccount selectLoyaltyAccountById(@Param("loyaltyId") int loyaltyId);

    String selectMaxPassengerId();
}

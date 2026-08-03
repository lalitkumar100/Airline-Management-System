package com.crimsonlogic.arilinemanangmentsystem.service;

import com.crimsonlogic.arilinemanangmentsystem.model.*;
import com.crimsonlogic.arilinemanangmentsystem.utility.InputUtil;
import com.crimsonlogic.arilinemanangmentsystem.config.MyBatisUtil;
import com.crimsonlogic.arilinemanangmentsystem.mapper.BookingMapper;
import com.crimsonlogic.arilinemanangmentsystem.mapper.RefundMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class RevenueReportService {

    private final FlightService flightService;
    private final InputUtil input = new InputUtil();
    private final BookingService bookingService;

    public RevenueReportService(FlightService flightService, BookingService bookingService) {
        this.flightService = flightService;
        this.bookingService = bookingService;
    }

    /**
     * Displays revenue of one flight.
     */
    public void revenueByFlight() {

        try {

            flightService.displayAllFlights();

            String flightId = input.getString("Enter Flight ID : ");

            flightService.findFlightById(flightId);

            double totalBookingRevenue = 0;
            double totalRefund = 0;

            try (SqlSession session = MyBatisUtil.getSqlSession()) {
                BookingMapper bookingMapper = session.getMapper(BookingMapper.class);
                RefundMapper refundMapper = session.getMapper(RefundMapper.class);

                List<Booking> bookings = bookingMapper.selectBookingsByFlightId(flightId);
                totalBookingRevenue = bookings.stream().mapToDouble(Booking::getAmount).sum();

                List<Refund> refunds = refundMapper.selectRefundsByFlightId(flightId);
                totalRefund = refunds.stream().mapToDouble(Refund::getAmount).sum();
            }

            Route.RevenueReport report =
                    new Route.RevenueReport(
                            flightId,
                            totalBookingRevenue,
                            totalRefund);

            report.displayInfo();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays revenue of all flights.
     */
    public void revenueOfAllFlights() {

        System.out.println("\n================== REVENUE REPORT ==================");

        System.out.printf("%-10s %-15s %-15s %-15s%n",
                "Flight",
                "Booking",
                "Refund",
                "Revenue");

        System.out.println("---------------------------------------------------------------");

        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            BookingMapper bookingMapper = session.getMapper(BookingMapper.class);
            RefundMapper refundMapper = session.getMapper(RefundMapper.class);
            
            flightService.getFlightList()
                    .stream()
                    .map(flight -> {
                        double bookingAmount = bookingMapper.selectBookingsByFlightId(flight.getFlightId())
                                        .stream()
                                        .mapToDouble(Booking::getAmount)
                                        .sum();

                        double refundAmount = refundMapper.selectRefundsByFlightId(flight.getFlightId())
                                        .stream()
                                        .mapToDouble(Refund::getAmount)
                                        .sum();

                        return new RevenueReport(
                                flight.getFlightId(),
                                bookingAmount,
                                refundAmount);

                    })
                    .forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    /**
     * Displays total airline revenue.
     */
    public void totalRevenue() {
        
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            BookingMapper bookingMapper = session.getMapper(BookingMapper.class);
            RefundMapper refundMapper = session.getMapper(RefundMapper.class);

            double bookingTotal = bookingMapper.selectAllBookings()
                    .stream()
                    .mapToDouble(Booking::getAmount)
                    .sum();

            double refundTotal = refundMapper.selectAllRefunds()
                    .stream()
                    .mapToDouble(Refund::getAmount)
                    .sum();

            System.out.println("\n========== AIRLINE REVENUE ==========");
            System.out.printf("Total Booking Revenue : %.2f%n", bookingTotal);
            System.out.printf("Total Refund Amount   : %.2f%n", refundTotal);
            System.out.printf("Net Revenue           : %.2f%n",
                    bookingTotal - refundTotal);
                    
        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public void revenueMenu() {

        while (true) {

            System.out.println("\n========== REVENUE REPORT ==========");
            System.out.println("1. Revenue By Flight");
            System.out.println("2. Revenue Of All Flights");
            System.out.println("3. Total Airline Revenue");
            System.out.println("0. Back");

            int choice = input.getInt("Enter Choice : ");

            switch (choice) {

                case 1:
                    revenueByFlight();
                    break;

                case 2:
                    revenueOfAllFlights();
                    break;

                case 3:
                    totalRevenue();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}

package com.crimsonlogic.arilinemanangmentsystem.utility;

public final class IdGenerator {

    private static int passengerCounter = 1001;
    private static int crewCounter = 501;
    private static int flightCounter = 101;
    private static int airportCounter = 11;
    private static int aircraftCounter = 51;
    private static int bookingCounter = 10001;
    private static int paymentCounter = 20001;
    private static int ticketCounter = 30001;
    private static int loyaltyCounter = 40001;

    private IdGenerator() {
    }

    public static String generatePassengerId() {
        return "PAS" + passengerCounter++;
    }

    public static String generateCrewId() {
        return "CRW" + crewCounter++;
    }

    public static String generateFlightId() {
        return "FLT" + flightCounter++;
    }

    public static String generateAirportId() {
        return "APT" + airportCounter++;
    }

    public static String generateAircraftId() {
        return "AIR" + aircraftCounter++;
    }

    public static String generateBookingId() {
        return "BKG" + bookingCounter++;
    }

    public static String generatePaymentId() {
        return "PAY" + paymentCounter++;
    }

    public static String generateTicketId() {
        return "TKT" + ticketCounter++;
    }

    public static String generateLoyaltyId() {
        return "LOY" + loyaltyCounter++;
    }

    public static void initializeCounters() {
        try (org.apache.ibatis.session.SqlSession session = com.crimsonlogic.arilinemanangmentsystem.config.MyBatisUtil.getSqlSession()) {
            com.crimsonlogic.arilinemanangmentsystem.mapper.PassengerMapper passengerMapper = session.getMapper(com.crimsonlogic.arilinemanangmentsystem.mapper.PassengerMapper.class);
            String maxPassenger = passengerMapper.selectMaxPassengerId();
            if (maxPassenger != null) {
                if (maxPassenger.startsWith("PAS")) {
                    passengerCounter = Integer.parseInt(maxPassenger.substring(3)) + 1;
                }
            }

            com.crimsonlogic.arilinemanangmentsystem.mapper.FlightMapper flightMapper = session.getMapper(com.crimsonlogic.arilinemanangmentsystem.mapper.FlightMapper.class);
            String maxFlight = flightMapper.selectMaxFlightId();
            if (maxFlight != null) {
                if (maxFlight.startsWith("FLT")) {
                    flightCounter = Integer.parseInt(maxFlight.substring(3)) + 1;
                } else if (maxFlight.startsWith("FL")) {
                    flightCounter = Integer.parseInt(maxFlight.substring(2)) + 1;
                }
            }

            com.crimsonlogic.arilinemanangmentsystem.mapper.BookingMapper bookingMapper = session.getMapper(com.crimsonlogic.arilinemanangmentsystem.mapper.BookingMapper.class);
            String maxBooking = bookingMapper.selectMaxBookingId();
            if (maxBooking != null) {
                if (maxBooking.startsWith("BKG")) {
                    bookingCounter = Integer.parseInt(maxBooking.substring(3)) + 1;
                } else if (maxBooking.startsWith("BK")) {
                    bookingCounter = Integer.parseInt(maxBooking.substring(2)) + 1;
                }
            }

            com.crimsonlogic.arilinemanangmentsystem.mapper.PaymentMapper paymentMapper = session.getMapper(com.crimsonlogic.arilinemanangmentsystem.mapper.PaymentMapper.class);
            String maxPayment = paymentMapper.selectMaxPaymentId();
            if (maxPayment != null) {
                if (maxPayment.startsWith("PAY")) {
                    paymentCounter = Integer.parseInt(maxPayment.substring(3)) + 1;
                }
            }

            String maxTicket = bookingMapper.selectMaxTicketId();
            if (maxTicket != null) {
                if (maxTicket.startsWith("TKT")) {
                    ticketCounter = Integer.parseInt(maxTicket.substring(3)) + 1;
                }
            }
        } catch (Exception e) {
            System.err.println("Could not initialize ID generator counters from database: " + e.getMessage() + ". Using defaults.");
        }
    }
}
package com.crimsonlogic.arilinemanangmentsystem.service;

import com.crimsonlogic.arilinemanangmentsystem.exception.AuthentictionException;
import com.crimsonlogic.arilinemanangmentsystem.exception.FlightException;
import com.crimsonlogic.arilinemanangmentsystem.exception.RecordNotFoundException;
import com.crimsonlogic.arilinemanangmentsystem.model.*;
import com.crimsonlogic.arilinemanangmentsystem.utility.IdGenerator;
import com.crimsonlogic.arilinemanangmentsystem.utility.InputUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class BookingService {

    final static ArrayList<Booking> bookingList = new ArrayList<>();
    final HashMap<String ,Booking> bookingHashMap = new HashMap<>();
    AirportAndAircraftService airportAndAircraftService ;
    FlightService flightService;
    PassengerService passengerService ;
    InputUtil input = new InputUtil();

    PaymentService paymentService = new PaymentService();

    public BookingService(AirportAndAircraftService airportAndAircraftService
            , FlightService flightService
            , PassengerService passengerService) {
        this.airportAndAircraftService = airportAndAircraftService;
        this.flightService = flightService;
        this.passengerService=passengerService;
    }

    private boolean authenticatePassenger(Passenger passenger) {

        while (true) {

            String password =
                    input.getString("Enter Password (0 to Cancel): ");

            if (password.equals("0")) {
                return false;
            }

            if (passenger.verifyPassword(password)) {
                return true;
            }

            System.out.println("Incorrect Password.");
        }
    }
    /**
     * Books a flight.
     */
    public void bookFlight() {

        try {

            flightService.displayAllFlights();

            Flight flight = null;

            while (true) {
                try {


                     flight = flightService.readFlight();

                    if (flight == null) {
                        return;
                    }

                    if ((!flight.getStatus().equalsIgnoreCase(Flight.STATUS_SCHEDULED) &&
                            (!flight.getStatus().equalsIgnoreCase(Flight.STATUS_DELAYED)))) {

                       throw  new FlightException("This Flight ("+flight.getFlightId()+
                               ") is not current Scheduled, it current status is "+flight.getStatus()+
                               " \n Booking is allowed only for Scheduled flights and delayed");

                    }

                    break;

                } catch (Exception e) {

                    System.out.println(e.getMessage());
                }
            }

            flight.displayInfo();

            flight.hasAvailableSeatbyType();

            Passenger passenger = null;

            while (true) {

                try {
                     passenger =PassengerService.readPassenger();
                    if (!authenticatePassenger(passenger))
                        return;
                    passenger.displayInfo();
                    boolean alreadyBooked = false;

                    for (Booking booking : flight.getBookings()) {

                        if (booking.getPassenger().getPassengerId()
                                .equalsIgnoreCase(passenger.getPassengerId())) {

                            alreadyBooked = true;
                            break;
                        }
                    }

                    if (alreadyBooked) {

                        System.out.println("you("+passenger.getName() +") has already booked this flight("+flight.getFlightId()+").");
                        continue;
                    }

                    break;

                } catch (Exception e) {

                    System.out.println(e.getMessage());
                }
            }

            System.out.println("\n========== SEAT FARE ==========");
            System.out.printf("A Class : %.2f%n", flight.getBaseFare() * 1.50);
            System.out.printf("B Class : %.2f%n", flight.getBaseFare() * 1.20);
            System.out.printf("C Class : %.2f%n", flight.getBaseFare());

            String seatType;
            double amount = 0;
            String status ;
            while (true) {

                seatType = input.getString(
                        "Enter Seat Type (A/B/C) : ").toUpperCase();

                switch (seatType) {

                    case "A":

                        amount = flight.getBaseFare() * 1.50;

                        if (flight.getAvailableASeats() > 0) {
                            status=Booking.STATUS_CONFIRMED_BN;
                            System.out.println("\nA Class Seat Available.");
                            System.out.println("Your booking will be CONFIRMED after ticket generation.");

                        } else {
                            status=Booking.STATUS_WAITLIST;
                            System.out.println("\nA Class Full.");
                            System.out.println("You will be placed in the WAITLIST.");
                        }

                        break;

                    case "B":

                        amount = flight.getBaseFare() * 1.20;

                        if (flight.getAvailableBSeats() > 0) {
                            status=Booking.STATUS_CONFIRMED;
                            System.out.println("\nB Class Seat Available.");
                            System.out.println("Your booking will be CONFIRMED after ticket generation.");

                        } else {
                            status=Booking.STATUS_WAITLIST;
                            System.out.println("\nB Class Full.");
                            System.out.println("You will be placed in the WAITLIST.");
                        }

                        break;

                    case "C":

                        amount = flight.getBaseFare();

                        if (flight.getAvailableCSeats() > 0) {
                            status=Booking.STATUS_CONFIRMED;
                            System.out.println("\nC Class Seat Available.");
                            System.out.println("Your booking will be CONFIRMED after ticket generation.");

                        } else {
                            status=Booking.STATUS_WAITLIST;
                            System.out.println("\nC Class Full.");
                            System.out.println("You will be placed in the WAITLIST.");
                        }

                        break;

                    default:

                        System.out.println("Invalid Seat Type.");
                        continue;
                }

                break;
            }

            while (true) {

                System.out.println("\n========== PAYMENT ==========");
                System.out.println("Amount : " + amount);
                System.out.println("1. Pay");
                System.out.println("2. Cancel");

                int choice = input.getInt("Enter Choice : ");

                if (choice == 2) {
                    return;
                }

                if (choice != 1) {

                    System.out.println("Invalid Choice.");
                    continue;
                }

                Payment payment = new Payment(
                        amount,
                        true);

                Booking booking = new Booking();
                booking.setBookingId(IdGenerator.generateBookingId());
                booking.setPassenger(passenger);
                booking.setFlightBooked(flight);
                booking.setSeatType(seatType);
                booking.setAmount(amount);
                booking.setBookingstatus(status);
                booking.setPayment(payment);
                booking.setBookingDateTime(LocalDateTime.now());


                payment.setBooking(booking);

                bookingList.add(booking);
                bookingHashMap.putIfAbsent(booking.getBookingId(), booking);

                flight.addBookings(booking);
                flight.addWaitList(booking);

                passenger.getLoyalty().update(seatType,true);

                System.out.println("\nBooking Successful.");

                booking.displayInfo();


                payment.displayInfo();

                return;
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }





    /**
     * Calculates seat fare based on seat type.
     */


    private Booking getBookingById(String bookingId) throws RecordNotFoundException {

        if(bookingHashMap.containsKey(bookingId)){
            return bookingHashMap.get(bookingId);
        }
        else {
            throw new RecordNotFoundException("Booking with this id is not found");
        }

    }

    /**
     * Displays all bookings sorted by Flight ID.
     */
    public void displayAllBookings() {

        if (bookingList.isEmpty()) {

            System.out.println("\nNo Bookings Found.");
            return;
        }

        System.out.println("\n============================ ALL BOOKINGS ============================");

        System.out.printf(
                "%-10s %-10s %-10s %-8s %-10s %-15s %-20s %-80s%n",
                "Book ID",
                "Pass ID",
                "Flight",
                "Seat",
                "Amount",
                "Status",
                "Booking Time",
                "Check In");


        System.out.println("----------------------------------------------------------------------");

        bookingList.stream()
                .sorted((b1, b2) ->
                        b1.getFlightBooked().getFlightId()
                                .compareToIgnoreCase(
                                        b2.getFlightBooked().getFlightId()))
                .forEach(System.out::println);
    }
    /**
     * Displays all bookings of a flight sorted by Booking ID.
     */
    public void displayBookingsByFlight() {

        try {

            flightService.displayAllFlights();

            String flightId = input.getString("Enter Flight ID : ");

            Flight flight = flightService.findFlightById(flightId);

            if (flight.getBookings().isEmpty()) {

                throw new RecordNotFoundException(
                        "No bookings found for this flight.");
            }

            System.out.println("\n================ FLIGHT BOOKINGS ================");

            System.out.printf("%-10s %-10s %-10s %-8s %-10s %-15s %-20s%n",
                    "Book ID",
                    "Pass ID",
                    "Flight",
                    "Seat",
                    "Amount",
                    "Status",
                    "Booking Time");

            System.out.println("--------------------------------------------------------------------------");

            flight.getBookings()
                    .stream()
                    .sorted((b1, b2) ->
                            b1.getBookingId()
                                    .compareToIgnoreCase(b2.getBookingId()))
                    .forEach(System.out::println);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void displayBookingById() {

//        displayAllBookings();

        while (true) {

            try {

                String bookingId =
                        input.getString("Enter Booking ID (0 to Cancel) : ");

                if (bookingId.equals("0")) {
                    return;
                }

                Booking booking = getBookingById(bookingId);

                System.out.println("\n========== BOOKING DETAILS ==========");
                booking.displayInfo();

                return;

            } catch (Exception e) {

                System.out.println(e.getMessage());
                System.out.println("Please enter a valid Booking ID.");
            }
        }
    }

    public void gernateOnboardingPass(){
        Booking booking;
        while (true) {

            String bookingId =
                    input.getString("Enter Booking ID (0 to Cancel) : ");

            if (bookingId.equals("0")) {
                return;
            }

            try {

                booking = getBookingById(bookingId);
                Passenger passenger = booking.getPassenger();

                if (!authenticatePassenger(passenger))
                    return;

                break;

            } catch (Exception e) {

                System.out.println(e.getMessage());
            }
        }
        System.out.println("==============Booking =======================");
        booking.displayInfo();
        booking.getFlightBooked().displayInfo();

        if (booking.getBookingstatus().equals(Booking.STATUS_WAITLIST)){
            System.out.println("Sorry!!  your are in WaitList so we can't provide you onBoarding pass");
            return;
        }
        //create good look pass
        System.out.println("Seat no :"+booking.getTicket().getSeat().getSeatNo());
    }


    /**
     * Cancels a booking.
     */
    public void cancelBooking() {

        try {

            Booking booking = readBooking();

            Passenger passenger = booking.getPassenger();

            while(true){
                String password = input.getString(
                        "Enter Passenger Password (0 to Cancel): ");
                if (password.equals("0")) {
                    return;
                }
                if(passenger.verifyPassword(password)){
                    System.out.println("login!!!!!!");
                    break;
                }
                throw new AuthentictionException("password is wrong!!");

            }

            if (booking == null) {
                return;
            }

            Flight flight = booking.getFlightBooked();

            if (flight.getStatus().equalsIgnoreCase(Flight.STATUS_FLEW)) {

                System.out.println("Flight already Flew.");
                return;
            }

            if (flight.getStatus().equalsIgnoreCase(Flight.STATUS_CANCELLED)) {

                System.out.println("Flight is Cancelled.");
                return;
            }

            if (booking.getBookingstatus().equalsIgnoreCase("WaitList")) {

                cancelWaitingBooking(booking);

            }
            if (booking.getBookingstatus().equalsIgnoreCase(Booking.STATUS_CONFIRMED_BN)) {

                cancelWaitingBooking(booking);

            }

            else {

                cancelConfirmedBooking(booking);
            }
            Refund.refundArrayList.add(new Refund(booking.getAmount(),booking));


        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }




    /**
     * Reads a valid booking.
     */
    private Booking readBooking() {

        displayAllBookings();

        while (true) {

            String bookingId =
                    input.getString("Enter Booking ID (0 to Cancel) : ");

            if (bookingId.equals("0")) {
                return null;
            }

            try {

                return getBookingById(bookingId);

            } catch (Exception e) {

                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * Cancels waiting booking.
     */
    private void cancelWaitingBooking(Booking booking) {

        Flight flight = booking.getFlightBooked();

        flight.removeBooking(booking);

        booking.getPassenger()
                .getLoyalty()
                .update(booking.getSeatType(), false);
        booking.setBookingstatus(Booking.STATUS_CANCELLED);

        System.out.println("Booking Cancelled Successfully.");
    }
    /**
     * Cancels confirmed booking.
     */
    private void cancelConfirmedBooking(Booking booking) throws RecordNotFoundException {

        Flight flight = booking.getFlightBooked();

        Ticket oldTicket = findTicket(booking);

        flight.removeBooking(booking);

        booking.getPassenger()
                .getLoyalty()
                .update(booking.getSeatType(), false);

        Booking nextBooking =
                flight.getNextWaitingPassenger();

        if (nextBooking == null) {

            flight.removeTicket(oldTicket);

            System.out.println("Booking Cancelled.");
            System.out.println("Seat is now Empty.");

            return;
        }

        nextBooking.setBookingstatus("Confirmed");

        Ticket newTicket =
                new Ticket(
                        nextBooking.getAmount(),
                        oldTicket.getSeat());

        flight.removeTicket(oldTicket);

        flight.addTickets(newTicket);

        System.out.println("Ticket transferred successfully.");

        nextBooking.displayInfo();
    }

    /**
     * Finds ticket of a confirmed booking.
     */
    private Ticket findTicket(Booking booking)
            throws RecordNotFoundException {

        Flight flight = booking.getFlightBooked();

        for (Ticket ticket : flight.getTickets()) {

            if (ticket.getSeat().getSeatNo()
                    == booking.getTicket().getSeat().getSeatNo()) {

                return ticket;
            }
        }

        throw new RecordNotFoundException("Ticket not found.");
    }

    /**
     * Inserts demo bookings.
     */
    public void initializeDemoBookings() {

        try {

            Flight fl001 = flightService.findFlightById("FL001");
            Flight fl002 = flightService.findFlightById("FL002");
            Flight fl003 = flightService.findFlightById("FL003");
            Flight fl004 = flightService.findFlightById("FL004");
            Flight fl005 = flightService.findFlightById("FL005");
            Flight fl006 = flightService.findFlightById("FL006");
            Flight fl007 = flightService.findFlightById("FL007");
            Flight fl008 = flightService.findFlightById("FL008");
            Flight fl009 = flightService.findFlightById("FL009");
            Flight fl010 = flightService.findFlightById("FL010");

            Passenger p1 = passengerService.getPassengerById("PAS1001");
            Passenger p2 = passengerService.getPassengerById("PAS1002");
            Passenger p3 = passengerService.getPassengerById("PAS1003");
            Passenger p4 = passengerService.getPassengerById("PAS1004");
            Passenger p5 = passengerService.getPassengerById("PAS1005");
            Passenger p6 = passengerService.getPassengerById("PAS1006");
            Passenger p7 = passengerService.getPassengerById("PAS1007");
            Passenger p8 = passengerService.getPassengerById("PAS1008");
            Passenger p9 = passengerService.getPassengerById("PAS1009");
            Passenger p10 = passengerService.getPassengerById("PAS1010");

            // FL001
            addDemoBooking(fl001, p1, "A");
            addDemoBooking(fl001, p2, "B");
            addDemoBooking(fl001, p3, "C");

            // FL002
            addDemoBooking(fl002, p4, "A");
            addDemoBooking(fl002, p5, "B");
            addDemoBooking(fl002, p6, "C");

            // FL003
            addDemoBooking(fl003, p7, "A");
            addDemoBooking(fl003, p8, "B");
            addDemoBooking(fl003, p9, "C");

            // FL004
            addDemoBooking(fl004, p10, "A");
            addDemoBooking(fl004, p1, "B");
            addDemoBooking(fl004, p2, "C");

            // FL005
            addDemoBooking(fl005, p3, "A");
            addDemoBooking(fl005, p4, "B");
            addDemoBooking(fl005, p5, "C");

            // FL006
            addDemoBooking(fl006, p6, "A");
            addDemoBooking(fl006, p7, "B");
            addDemoBooking(fl006, p8, "C");

            // FL007
            addDemoBooking(fl007, p9, "A");
            addDemoBooking(fl007, p10, "B");
            addDemoBooking(fl007, p1, "C");

            // FL008
            addDemoBooking(fl008, p2, "A");
            addDemoBooking(fl008, p3, "B");
            addDemoBooking(fl008, p4, "C");

            // FL009
            addDemoBooking(fl009, p5, "A");
            addDemoBooking(fl009, p6, "B");
            addDemoBooking(fl009, p7, "C");

            // FL010
            addDemoBooking(fl010, p8, "A");
            addDemoBooking(fl010, p9, "B");
            addDemoBooking(fl010, p10, "C");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates and stores a demo booking.
     */
    private void addDemoBooking(
            Flight flight,
            Passenger passenger,
            String seatType) {

        double amount;

        switch (seatType.toUpperCase()) {

            case "A":
                amount = flight.getBaseFare() * 1.50;
                break;

            case "B":
                amount = flight.getBaseFare() * 1.20;
                break;

            default:
                amount = flight.getBaseFare();
        }

        Payment payment = new Payment(
                amount,
                true);

        Booking booking = new Booking();
        booking.setBookingId(IdGenerator.generateBookingId());
        booking.setPassenger(passenger);
        booking.setFlightBooked(flight);
        booking.setSeatType(seatType);
        booking.setAmount(amount);
        booking.setBookingstatus(Booking.STATUS_CONFIRMED_BN);
        booking.setPayment(payment);
        booking.setBookingDateTime(LocalDateTime.now());

        payment.setBooking(booking);

        bookingList.add(booking);

        bookingHashMap.put(
                booking.getBookingId(),
                booking);

        flight.addBookings(booking);

        flight.addWaitList(booking);

        passenger.getLoyalty().update(
                seatType,
                true);
    }


    public void checkIn(){
        while (true) {

            String bookingId =
                    input.getString("Enter Booking ID (0 to Cancel) : ");

            if (bookingId.equals("0")) {
                return ;
            }

            try {
                Booking booking = getBookingById(bookingId);
                Passenger passenger =booking.getPassenger();

                while(true){
                    String password = input.getString(
                            "Enter Passenger Password  (0 to Cancel): ");
                    if (password.equals("0")) {
                        return;
                    }
                    if(passenger.verifyPassword(password)){
                        System.out.println("login!!!!!!");
                        break;
                    }
                    throw new AuthentictionException("password is wrong!!");

                }

                if(booking.getBookingstatus().equals(Booking.STATUS_CONFIRMED)) {

                    booking.passengerCheckIn = true;
                    System.out.println("Passenger with id" + booking.getBookingId() + " is check in");
                }
                else {
                    System.out.println("Passenger with id" + booking.getBookingId() + "can't is check in because ticket is not "+Booking.STATUS_CONFIRMED);
                }
            } catch (Exception e) {

                System.out.println(e.getMessage());
            }
        }
    }
}



package com.crimsonlogic.arilinemanangmentsystem.service;

import com.crimsonlogic.arilinemanangmentsystem.Functionality.RegisterFunctionality;
import com.crimsonlogic.arilinemanangmentsystem.model.Passenger;
import com.crimsonlogic.arilinemanangmentsystem.utility.InputUtil;
import com.crimsonlogic.arilinemanangmentsystem.utility.ValidatorUtil;

import java.util.HashMap;

public class PassengerServices {

    HashMap<String,Passenger> passengersDB = new HashMap<>();
    InputUtil inputer = new InputUtil();
    RegisterFunctionality register = new RegisterFunctionality();
    static Passenger login = null;

    public  void addPassenger(){
        Passenger passenger = register.registerPassenger();

        passenger.displayInfo();

       if( passengersDB.containsKey(passenger.getEmail())){
           System.out.println("passenger with this email id already exist");
           //try again
       }
       // else add to db
        passengersDB.put(passenger.getEmail(),passenger);
    }

    public void Login() throws Exception {


          String email = inputer.getString("Enter the Email :");
          ValidatorUtil.isValidEmail(email);
          String password = inputer.getString("Enter the Password :");

          if(!passengersDB.containsKey(email)){
              throw new Exception("email is not registered");
          }
          Passenger passenger = passengersDB.get(email);
          if(!passenger.getPassengerId().equals(password)){
               throw new Exception("invalid email or password");
           }
        System.out.println("login in successfully \n " +
                "welcome"+passenger.getFirstName() +" "+passenger.getLastName()+
                "\n Passenger Id "+passenger.getPassengerId());

          login = passenger;

    }

//   public void editProfile(){
//
//   }
//
//   public void DeleteAcount(){
//
//   }
//
//   public void getflightBooked(){
//
//   }
//
//   public  void gettBooked


}

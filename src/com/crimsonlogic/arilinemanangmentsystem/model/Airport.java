package com.crimsonlogic.arilinemanangmentsystem.model;

    public class Airport {

        private String airportId;
        private String airportName;
        private String city;
        private String country;

        public Airport(String airportId, String airportCode, String city, String country) {
            this.airportId = airportId;
            this.airportName = airportCode;
            this.city = city;
            this.country = country;
        }

        public String getAirportId() {
            return airportId;
        }


        public String getAirportCode() {
            return airportName;
        }

        public void setAirportCode(String airportCode) {
            this.airportName = airportCode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }



        @Override
        public String toString() {
            return String.format("%-10s %-15s %-15s %-15s",
                    airportId,
                    airportName,
                    city,
                    country);
        }

        public void displayInfo() {
            System.out.println("\n===== Airport Information =====");
            System.out.println("Airport ID   : " + airportId);
            System.out.println("Airport Code : " + airportName);
            System.out.println("City         : " + city);
            System.out.println("Country      : " + country);
            System.out.println("===============================");
        }
    }




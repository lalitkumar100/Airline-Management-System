package com.crimsonlogic.arilinemanangmentsystem.entity;

    public class Airport {

        private int airportId;
        private String airportCode;
        private String city;
        private String country;

        public Airport(int airportId, String airportCode, String city, String country) {
            this.airportId = airportId;
            this.airportCode = airportCode;
            this.city = city;
            this.country = country;
        }

        public int getAirportId() {
            return airportId;
        }

        public void setAirportId(int airportId) {
            this.airportId = airportId;
        }

        public String getAirportCode() {
            return airportCode;
        }

        public void setAirportCode(String airportCode) {
            this.airportCode = airportCode;
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

        public void displayInfo() {
            System.out.println("\n===== Airport Information =====");
            System.out.println("Airport ID   : " + airportId);
            System.out.println("Airport Code : " + airportCode);
            System.out.println("City         : " + city);
            System.out.println("Country      : " + country);
            System.out.println("===============================");
        }
    }

}


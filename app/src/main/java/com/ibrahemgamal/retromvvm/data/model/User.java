package com.ibrahemgamal.retromvvm.data.model;

public class User {
    private int id;

    private String name;

    private String username;

    private String email;

    private Address address;

    private String phone;

    private String website;

    private Company company;

    public User(int id, String name, String username, String email, Address address, String phone, String website, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Company getCompany() {
        return company;
    }

    public class Address {
        private String street;

        private String suite;

        private String city;

        private String zipcode;

        private Geo geo;

        private String totalAddress;

        public Address(String street, String suite, String city, String zipcode, Geo geo) {
            this.street = street;
            this.suite = suite;
            this.city = city;
            this.zipcode = zipcode;
            this.geo = geo;
        }

        public String getStreet() {
            return street;
        }

        public String getSuite() {
            return suite;
        }

        public String getCity() {
            return city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public Geo getGeo() {
            return geo;
        }

        public String getTotalAddress(){
            return city +" - "+street+" - "+suite+"\n- Zipcode: "+zipcode;
        }
    }

    public class Geo {
        private String lat;

        private String lng;

        public Geo(String lat, String lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }
    }

    private class Company {
        private String name;

        private String catchPhrase;

        private String bs;

        public Company(String name, String catchPhrase, String bs) {
            this.name = name;
            this.catchPhrase = catchPhrase;
            this.bs = bs;
        }

        public String getName() {
            return name;
        }

        public String getCatchPhrase() {
            return catchPhrase;
        }

        public String getBs() {
            return bs;
        }
    }
}





package utilities.jsonHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.dateTimeHelper.DateTimeHelper;

public class DataHelper {


    public static class User {

        @JsonProperty("firstName")
        String firstName;
        @JsonProperty("lastName")
        String lastName;
        @JsonProperty("email")
        String email;
        @JsonProperty("password")
        String password;
        @JsonProperty("dob")
        String dob;
        @JsonProperty("gender")
        String gender;
        @JsonProperty("order")
        ArrayList<Order> order = new ArrayList<>();
        @JsonProperty("address")
        ArrayList<Address> address = new ArrayList<>();
        @JsonProperty("company")
        String company;

        public String getFullName(){
            return this.firstName + " " + this.lastName;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }


        public ArrayList<Address> getAddress() {
            return address;
        }

        public void setAddress(ArrayList<Address> address) {
            this.address = address;
        }

        public ArrayList<Order> getOrder() {
            return order;
        }

        public void setOrder(ArrayList<Order> order) {
            this.order = order;
        }

        public static class Order {
            @JsonProperty("orderNumber")
            String orderNumber;

            @JsonProperty("orderDate")
            String orderDate;

            public String getOrderDate() {
                return orderDate;
            }

            public void setOrderDate() {
                this.orderDate = DateTimeHelper.getCurrentDateTimeByNopcommerceFormat();
            }

            public String getOrderNumber() {
                return orderNumber;
            }

            public void setOrderNumber(String orderNumber) {
                this.orderNumber = orderNumber;
            }
        }

        public static class Address{
            @JsonProperty("country")
            String country;
            @JsonProperty("region")
            String region;
            @JsonProperty("state")
            String state;
            @JsonProperty("city")
            String city;
            @JsonProperty("address1")
            String address1;
            @JsonProperty("address2")
            String address2;
            @JsonProperty("zip")
            String zip;
            @JsonProperty("phoneNo")
            String phoneNo;
            @JsonProperty("FaxNo")
            String faxNo;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getAddress1() {
                return address1;
            }

            public void setAddress1(String address1) {
                this.address1 = address1;
            }

            public String getZip() {
                return zip;
            }

            public void setZip(String zip) {
                this.zip = zip;
            }

            public String getPhoneNo() {
                return phoneNo;
            }

            public void setPhoneNo(String phoneNo) {
                this.phoneNo = phoneNo;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getAddress2() {
                return address2;
            }

            public void setAddress2(String address2) {
                this.address2 = address2;
            }

            public String getFaxNo() {
                return faxNo;
            }

            public void setFaxNo(String faxNo) {
                this.faxNo = faxNo;
            }
        }


        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }

    public static DataHelper.User getRandomUserInfo(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            List<User> userList = mapper.readValue(new File(filename), new TypeReference<List<User>>() {
            });
            return userList.get(new Random().nextInt(userList.size()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeUserInfoToFile(String filename, DataHelper.User user) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filename), user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void overWriteAddressInfoToUserFile(String filename, User user, String country, String region, String state, String city, String address1, String address2, String zip, String phoneNo, String faxNo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            DataHelper.User.Address address = new User.Address();
            address.setCountry(country);
            address.setRegion(region);
            address.setState(state);
            address.setCity(city);
            address.setAddress1(address1);
            address.setAddress2(address2);
            address.setZip(zip);
            address.setPhoneNo(phoneNo);
            address.setFaxNo(faxNo);
            user.getAddress().add(address);
            mapper.writeValue(new File(filename), user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void overWriteCurrentAddressInfoToUserFile(String filename, User user, String country, String region, String state, String city, String address1, String address2, String zip, String phoneNo, String faxNo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            user.getAddress().get(0).setCountry(country);
            user.getAddress().get(0).setRegion(region);
            user.getAddress().get(0).setState(state);
            user.getAddress().get(0).setCity(city);
            user.getAddress().get(0).setAddress1(address1);
            user.getAddress().get(0).setAddress2(address2);
            user.getAddress().get(0).setZip(zip);
            user.getAddress().get(0).setPhoneNo(phoneNo);
            user.getAddress().get(0).setFaxNo(faxNo);
            mapper.writeValue(new File(filename), user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void overWriteOrderInfoToUserFile(String filename, DataHelper.User user, String orderNumber){
        ObjectMapper mapper = new ObjectMapper();
        try {
            User.Order order = new User.Order();
            order.setOrderNumber(orderNumber);
            order.setOrderDate();
            user.getOrder().add(order);
            mapper.writeValue(new File(filename), user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void overWriteUserInfoToFile(String filename, User user, String firstName, String lastName, String email, String dob, String gender, String company) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setDob(dob);
            user.setGender(gender);
            user.setCompany(company);
            mapper.writeValue(new File(filename), user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void overWriteCompanyInfoToFile(String filename, User user, String company) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            user.setCompany(company);
            mapper.writeValue(new File(filename), user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

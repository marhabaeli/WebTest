package org.naic.mfl.se.challenge.Utility;

import fabricator.*;

public class UserInfo {
    private String id_gender;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String days;
    private String months;
    private String years;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postcode;
    private String other;
    private String phone;
    private String phone_mobile;
    private String alias;


    public String getId_gender() {
        return id_gender;
    }

    public void setId_gender(String id_gender) {
        this.id_gender = id_gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {        return email;    }

    public void setEmail(String email) {         this.email = email;    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone_mobile() {
        return phone_mobile;
    }

    public void setPhone_mobile(String phone_mobile) {
        this.phone_mobile = phone_mobile;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public UserInfo(){}

    public UserInfo(String useremail){
        fabricateUserInfo(useremail);
    }



    private void fabricateUserInfo(String useremail){
        Contact contact=Fabricator.contact();
        Alphanumeric randonNum=Fabricator.alphaNumeric();
        Calendar calendar=Fabricator.calendar();
        Words words=Fabricator.words();

        this.setId_gender("Mr.");
        this.setFirstname(contact.firstName());
        this.setLastname(contact.lastName());
        this.setEmail(useremail);
        this.setPassword(randonNum.botify("???###"));

        this.setDays(calendar.day());
        this.setMonths(calendar.month(true));
        this.setYears(calendar.year());

        this.setCompany(contact.company());
        this.setAddress1(contact.address());
        this.setAddress2("");
        this.setCity(contact.city());
        this.setState(contact.state());
        this.setPostcode(contact.postcode().substring(0,5));
        this.setOther(words.sentence(5)) ;
        this.setPhone(randonNum.numerify("##########"));
        this.setPhone_mobile(randonNum.numerify("##########"));
        this.setAlias(words.words(1).toString());

      //  return user;
    }

}

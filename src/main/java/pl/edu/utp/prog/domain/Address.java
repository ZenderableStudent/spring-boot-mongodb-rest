package pl.edu.utp.prog.domain;
public class Address {
private String street;
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
private String zipCode;
public String getZipCode() {
	return zipCode;
}
public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}
private String city;
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
private String country;
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public Address(String street, String zipCode, String city, String country) {
this.street = street;
this.zipCode = zipCode;
this.city = city;
this.country = country;
}
}
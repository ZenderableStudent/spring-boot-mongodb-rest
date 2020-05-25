package pl.edu.utp.prog.domain;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Clients")
public class Client {
@Id
String id;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
private String firstName;
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
private String lastName;
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
private List<Address> addresses;
public List<Address> getAddresses() {
	return addresses;
}
public void setAddresses(List<Address> addresses) {
	this.addresses = addresses;
}
public Client() {
this.addresses = new ArrayList<Address>();
}
public Client(String firstName, String lastName, List<Address> addresses) {
this.firstName = firstName;
this.lastName = lastName;
this.addresses = addresses;
}

}
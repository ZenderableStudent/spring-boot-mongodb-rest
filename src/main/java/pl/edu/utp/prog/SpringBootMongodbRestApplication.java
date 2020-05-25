package pl.edu.utp.prog;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.utp.prog.domain.Address;
import pl.edu.utp.prog.domain.Client;
import pl.edu.utp.prog.repository.ClientRepository;

@SpringBootApplication
public class SpringBootMongodbRestApplication implements CommandLineRunner{

	
@Autowired
ClientRepository repo; 
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongodbRestApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		repo.save(new Client(
				"John",
				"Smith",
				Arrays.asList(
					new Address("Polna 2", "10-100","Bydgoszcz", "Poland"),	
					new Address("Polna 2", "10-100","Bydgoszcz", "Poland")
				)));
		
		
	List<Client> clients=repo.findAll();
	for(Client c:clients) {
		System.out.println(c.getFirstName()+" "+c.getLastName());
		for (Address a:c.getAddresses()) {
			System.out.println(a.getStreet()+","+a.getZipCode()+" "+a.getCity()+","+a.getCountry());
		}
	}
}
	
}

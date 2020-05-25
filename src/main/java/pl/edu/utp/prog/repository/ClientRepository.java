package pl.edu.utp.prog.repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.utp.prog.domain.Client;

public interface ClientRepository extends MongoRepository<Client, String> {

	List<Client> findByLastName(String lastName);
	List<Client> findByFirstNameAndLastName(String firstName, String lastName);
	List<Client> findByAddresses_ZipCode(String zipCode);
	List<Client> findByAddresses_StreetIgnoreCaseContaining(String street);
	List<Client> findFirst2ByAddresses_Country(String country);
	List<Client> findByAddresses_CountryOrderByLastNameAsc(String country);
	
	
	@Query("{'$and': [ { 'firstName': ?0 }, { 'lastName': ?1} ]}")
	List<Client>findMe(String firstName,String lastName);
}

package pl.edu.utp.prog.controller;

//wykonane dodatkowo na podstawie internetu

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.utp.prog.domain.Client;
import pl.edu.utp.prog.repository.ClientRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ClientController {
	@Autowired
	ClientRepository repo;

	 @GetMapping
	 public List<Client> getClients() { 
	   return repo.findAll();
	 }

	 @GetMapping("/{id}")
	 public Client getClient(@PathVariable("id") String id) {
	   Optional<Client> c= repo.findById(id);
	   return c.orElseThrow(
	        ()->new ResourceNotFoundException("Client not found with id="+id)
	   );
	 }
	
	@GetMapping("/clients")
	public ResponseEntity<List<Client>> getAllClients() {
		try {
			List<Client> clients = new ArrayList<Client>();

			repo.findAll().forEach(clients::add);

			if (clients.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(clients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") String id) {
		Optional<Client> clientData = repo.findById(id);
		
		try {
			if (clientData.isPresent()) {
				return new ResponseEntity<>(clientData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/clients")
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		try {
			Client _client = repo.save(client);
			return new ResponseEntity<>(_client, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/clients/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable("id") String id, @RequestBody Client client) {
		Optional<Client> clientData = repo.findById(id);

		if (clientData.isPresent()) {
			Client _client = clientData.get();
			_client.setFirstName(client.getFirstName());
			_client.setLastName(client.getLastName());
			_client.setAddresses(client.getAddresses());
			return new ResponseEntity<>(repo.save(_client), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/clients/{id}")
	public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") String id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
}
package pl.edu.utp.prog.controller; 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.utp.prog.domain.Client;
import pl.edu.utp.prog.repository.ClientRepository;

@RestController
@RequestMapping("/clients") 
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

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseEntity<?> create(@RequestBody @Validated Client clt, BindingResult rst) { 
    if(rst.hasErrors()){
//      List<Errors> errors=rst.getFieldErrors().stream()
//      .map(e -> new Errors(e.getField(),e.getDefaultMessage())) 
//      .collect(Collectors.toList());
//      return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
      }
      Client savedClient = repo.save(clt);
      HttpHeaders responseHeaders = new HttpHeaders(); 
      responseHeaders.set("MyResponseHeader", "MyValue");
      return new ResponseEntity<>(savedClient, responseHeaders, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public void update(@PathVariable("id") String id, @RequestBody Client client) { 
    client.setId(id);
    repo.save(client);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") String id) { 
    repo.deleteById(id);
  }

}
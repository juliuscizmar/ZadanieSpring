package com.example.springboot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.springboot.Models.Objednavka;
import com.example.springboot.Models.Produkt;
import com.example.springboot.Models.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
	private static Map<String, User> userRepo = new HashMap<>(1000);
	private static Map<Integer, Produkt> produktRepo = new HashMap<>(1000);
	private static ArrayList<Objednavka> objednavkaRepo = new ArrayList<Objednavka>(1000);
	private static Integer pId = 2;
   	static {
		//userRepo initialization
		User u1 = new User("login0", "email0", "heslo0");
		userRepo.put(u1.getLogin(), u1);
		User u2 = new User("login1", "email1", "heslo1");
		userRepo.put(u2.getLogin(), u2);

		//produktRepo initialization
		Produkt p1 = new Produkt("nazov", "popis", 5);
		produktRepo.put(produktRepo.size(), p1);
		Produkt p2 = new Produkt("nazov1", "popis1", 10);
		produktRepo.put(produktRepo.size(), p2);

		//objednavkaRepo initialization
		ArrayList<Produkt> produkty = new ArrayList<Produkt>(100);
		produkty.add(p1);
		Objednavka o1 = new Objednavka("nazov0", "popis0", 101, "login0", produkty);
		objednavkaRepo.add(o1);
	}

	//index page
	@RequestMapping(value = "/")
	public String index() {
		return "REST web service made in spring boot";
	}

	///////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////FUNCTIONS FOR USER CONTROLLER////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////	

	//get all users as objects
	@RequestMapping(value = "/users")
	public ResponseEntity<Object> getUsers() {
		return new ResponseEntity<>(userRepo.values(), HttpStatus.OK);
	}
	
	//get one user by meno
	@RequestMapping(value = "/users/{login}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUser(@PathVariable("login") String usersLogin) {
		User res = userRepo.get(usersLogin);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	//create new user
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		userRepo.put(user.getEmail(), user);
		return new ResponseEntity<>("User was successfully created", HttpStatus.CREATED);
	}

	//update one user by login
	@RequestMapping(value = "/users/{login}", method = RequestMethod.PUT)
	public ResponseEntity<Object> replaceUser(@RequestBody User newUser, @PathVariable("login") String usersLogin) {
		User res = userRepo.replace(usersLogin, newUser);
		if (res != null) {
			return new ResponseEntity<>("User was successfully updated", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User could not be updated", HttpStatus.NOT_MODIFIED);
		}
	}
  
	//delete user by login
	@RequestMapping(value = "/users/{login}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable("login") String usersLogin) {
		User res = userRepo.remove(usersLogin);
		if (res != null) {
			return new ResponseEntity<>("User was successfully deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User could not be deleted", HttpStatus.NOT_MODIFIED);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////FUNCTIONS FOR PRODUKT CONTROLLER/////////////////////
	///////////////////////////////////////////////////////////////////////////////////////		

	//get all products as objects
	@RequestMapping(value = "/products")
	public ResponseEntity<Object> getProdukts() {
		return new ResponseEntity<>(produktRepo.values(), HttpStatus.OK);
	}

	//get one produkt by id
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getProdukt(@PathVariable("id") Integer id) {
		Produkt res = produktRepo.get(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	//create new produkt by user
	@RequestMapping(value = "/users/{login}/produkt", method = RequestMethod.POST)
	public ResponseEntity<Object> createProdukt(@RequestBody Produkt produkt, @PathVariable("login") String usersLogin) {
		produktRepo.put(pId++, produkt);
		return new ResponseEntity<>("Produkt was successfully created by " + usersLogin, HttpStatus.CREATED);
	}

	//update one produkt by id
	@RequestMapping(value = "/users/{login}/produkt/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> replaceProdukt(@RequestBody Produkt newProdukt, @PathVariable("login") String usersLogin, @PathVariable("id") Integer id) {
		Produkt res = produktRepo.replace(id, newProdukt);
		if (res != null) {
			return new ResponseEntity<>("Produkt was successfully updated by " + usersLogin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Produkt could not be updated", HttpStatus.NOT_MODIFIED);
		}
	}

	//delete one produkt by id
	@RequestMapping(value = "/users/{login}/produkt/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProdukt(@PathVariable("login") String usersLogin, @PathVariable("id") Integer id) {
		Produkt res = produktRepo.remove(id);
		if (res != null) {
			return new ResponseEntity<>("Produkt was successfully deleted by " + usersLogin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Produkt could not be deleted", HttpStatus.NOT_MODIFIED);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////FUNCTIONS FOR OBJEDNAVKA CONTROLLER//////////////////
	///////////////////////////////////////////////////////////////////////////////////////	

	//get all objednavky
	@RequestMapping(value = "/objednavky")
	public ResponseEntity<Object> getObjednavky() {
		return new ResponseEntity<>(objednavkaRepo, HttpStatus.OK);
	}

	//create new objednavka by user
	@RequestMapping(value = "/users/{login}/objednavka", method = RequestMethod.POST)
	public ResponseEntity<Object> createProdukt(@RequestBody Objednavka objednavka, @PathVariable("login") String usersLogin) {
		objednavkaRepo.add(objednavka);
		return new ResponseEntity<>("Objednavka was successfully created by " + usersLogin, HttpStatus.CREATED);
	}
}

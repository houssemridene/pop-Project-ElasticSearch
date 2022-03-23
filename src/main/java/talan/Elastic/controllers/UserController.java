package talan.Elastic.controllers;

import java.io.File;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import talan.Elastic.entities.User;

import talan.Elastic.services.UserService;





@CrossOrigin(origins = "*")
@RestController

public class UserController {
	
	@Autowired
	private UserService userSer ;
	

	
	
	@PostMapping("/adduser")
	public User addUser( @RequestBody   User user) {
		return userSer.addUser(user);
	}
	@GetMapping("/users")
	
	public List<User> getAllUser() {
		return userSer.getAllUser();
	}
	@DeleteMapping("/deleteuser/{id}")
	public void deleteUser(@PathVariable int id) {
		userSer.deleteUser(id);
	}
	@GetMapping("users/{id}")
	public User getUser(@PathVariable int id) {
		return userSer.getUser(id);
	}
	@PostMapping("users/fscrawler")
	public void indexFile(@RequestParam("file") MultipartFile file) throws IllegalStateException {
		userSer.indexFile(file);
	}
	@PostMapping("users/fscrawler1")
	public void indexFile1(@RequestParam("file") MultipartFile file) throws IllegalStateException {
		userSer.indexFile1(file);
	}
	@GetMapping("users/search")
	public ResponseEntity<String> SearchElastic( @RequestParam("q") String wordToSearch) {
		return userSer.SearchElastic(wordToSearch);
	}
	
	

}

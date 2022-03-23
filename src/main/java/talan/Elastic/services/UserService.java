package talan.Elastic.services;

import java.io.File;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import talan.Elastic.entities.User;




public interface UserService {
	
	 User getUser(int id ) ;
	 List<User> getAllUser();
	 User addUser(User user);
	 void deleteUser(int id);
	 List<User> getUsersLot();
	 void indexFile(MultipartFile file) ;
	 void indexFile1(MultipartFile file) ;
	 ResponseEntity<String> SearchElastic(String wordToSearch);
	 
	 
	 

}

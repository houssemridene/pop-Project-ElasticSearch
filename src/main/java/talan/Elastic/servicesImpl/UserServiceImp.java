package talan.Elastic.servicesImpl;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.io.stream.InputStreamStreamInput;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import talan.Elastic.entities.User;
import talan.Elastic.entities.Vehicle;
import talan.Elastic.entities.filee;
import talan.Elastic.repositories.UserRepository;
import talan.Elastic.services.UserService;


import java.io.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;






@Service
public class UserServiceImp implements UserService {
	
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Autowired
	private UserRepository userRepo ;

	@Override
	public User getUser(int id) {
		
		return userRepo.findById(id).get();
	}

	

	@Override
	public User addUser(User user) {
		
		return userRepo.save(user);
		
	}

	@Override
	public void deleteUser(int id) {
		userRepo.deleteById(id);
		
	}



	@Override
	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		Iterator<User> myIterator = userRepo.findAll().iterator();
		while (myIterator.hasNext())
		    users.add(myIterator.next());
		
		return users ;
	}



	@Override
	public List<User> getUsersLot() {
		
		return null;
	}



	@Override
	public void indexFile(MultipartFile file)  {
		try {
		RestTemplate restTemplate = new RestTemplate();
		/*HttpEntity<MultipartFile> requestBody = new HttpEntity<>(file);
		ResponseEntity<filee> result 
	     = r.postForObject("http://localhost:8080/fscrawler/_upload", requestBody, filee.class);*/
		
		Path tempFile = Files.createTempFile(null, null);
		  
		Files.write(tempFile, file.getBytes());
		File fileToSend = tempFile.toFile();

		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
		      
		parameters.add("file", new FileSystemResource(fileToSend));

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "multipart/form-data");

		HttpEntity httpEntity = new HttpEntity<>(parameters, headers);

		
		  try {
		  ResponseEntity<filee> result 
		     = restTemplate.postForEntity("http://localhost:8080/fscrawler/_upload", httpEntity, filee.class);
		  System.out.println(result.getBody().getUrl());
		  } finally   {
			  fileToSend.delete();
			  }}   catch (IOException e) {
		            e.printStackTrace();
		        }
		
		
	}



	@Override
	public void indexFile1(MultipartFile file) {
		
		
		RestTemplate restTemplate = new RestTemplate();
		/*HttpEntity<MultipartFile> requestBody = new HttpEntity<>(file);
		ResponseEntity<filee> result 
		 = r.postForObject("http://localhost:8080/fscrawler/_upload", requestBody, filee.class);*/
		
		
		
		/*String fileNameWithOutExt = FilenameUtils.removeExtension(file.getOriginalFilename());
		System.out.println(fileNameWithOutExt);
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		Path tempFile = Files.createTempFile(fileNameWithOutExt, "."+extension);
		System.out.println(tempFile.getFileName());*/
		 
		
		  
		/*Files.write(tempFile, file.getBytes());
		File fileToSend = tempFile.toFile();
		boolean success = fileToSend.renameTo(new File(fileNameWithOutExt));
		System.out.println(fileToSend.getName());*/
		
		
		File fileConverted = convert(file);
		
		
		

		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
		
		      
		parameters.add("file", new FileSystemResource(fileConverted));
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "multipart/form-data");

		HttpEntity httpEntity = new HttpEntity<>(parameters, headers);

		
		  try {
		  ResponseEntity<filee> result 
		     = restTemplate.postForEntity("http://localhost:8080/fscrawler/_upload", httpEntity, filee.class);
		  System.out.println(result.getBody().getUrl());
		  } finally   {
			  fileConverted.delete();
			  }
			
			
		}



	@Override
	public ResponseEntity<String> SearchElastic(String wordToSearch) {
		//public String SearchElastic(String wordToSearch)  {
		/*RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String,String>();
		parameters.add("q", wordToSearch);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/html");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
		
		ResponseEntity<Object> result 
	     = restTemplate.getForEntity("http://127.0.0.1:9200/papiers/_doc/_search", request, Object.class);
		
		System.out.println(result);*/
		HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        final String url = "http://127.0.0.1:9200/papiers/_doc/_search?q={wordToSearch}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("wordToSearch", wordToSearch);
        HttpEntity<?> httpEntity  = new HttpEntity<>(httpHeaders); 
        RestTemplate restTemplate  = new RestTemplate();
        ResponseEntity<String> result =restTemplate.exchange(url, HttpMethod.GET, httpEntity,String.class, params);
        
        
		 
		return result;
		
		
		
		
	}
	
 
		
		
	
		
	public InputStream convert(String word) 
			  throws IOException {
			    
			    InputStream targetStream = IOUtils.toInputStream(word);
			    return targetStream;
			}
	
		
		
	


 public File convert(MultipartFile filee) {
	 String fileNameWithOutExt = FilenameUtils.removeExtension(filee.getOriginalFilename());
	 String extension = FilenameUtils.getExtension(filee.getOriginalFilename());
	 File file = new File("C:/Users/Houssem/Desktop/TALAN PFE 2022/tache principale a faire/files a supprimer apres indexation/"+fileNameWithOutExt+"."+extension);

	 try {
		filee.transferTo(file);
	} catch (IllegalStateException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	 return file;

}
}	
	

		
	



	



	



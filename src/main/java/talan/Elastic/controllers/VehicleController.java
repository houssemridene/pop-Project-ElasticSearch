package talan.Elastic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import talan.Elastic.configuration.SyncPipe;
import talan.Elastic.entities.Document;
import talan.Elastic.entities.Vehicle;
import talan.Elastic.entities.vehicle1;
import talan.Elastic.search.SearchRequestDTO;
import talan.Elastic.services.VehicleService;

@CrossOrigin(origins = "*")
@RestController
public class VehicleController {
	
	
	private final VehicleService vehicleSer;
	
	@Autowired
	public VehicleController (VehicleService vehicleSer) {
		this.vehicleSer =vehicleSer;
	}
	
	
	@PostMapping("/vehicles")
	public void index(@RequestBody final Vehicle vehicle) {
			vehicleSer.index(vehicle);
	}
	
	
	@GetMapping("/vehicles/{id}")
	public Vehicle getByI(@PathVariable String id) {
		return vehicleSer.getById(id);
	}
	 @PostMapping("/search")
	    /*public List<vehicle1> search(@RequestBody final SearchRequestDTO dto) {
	        return vehicleSer.search(dto);*/
	 public List<Document> search(@RequestBody final SearchRequestDTO dto) {
		 return vehicleSer.search(dto);
	    }
	 @GetMapping("/test")   
	 public Document test() {
		  Document v = new Document();
		  return v;
	 }
	 @GetMapping("/search/{date}")
	    public List<Document> getAllVehiclesCreatedSince(
	            @PathVariable
	            @DateTimeFormat(pattern = "yyyy-MM-dd")
	            final Date date) {
	        return vehicleSer.getAllDocumentsCreatedSince(date);
	    }
	 
	 @GetMapping("cmd")
	 public  void test1() throws IOException {
	 /*ProcessBuilder builder = new ProcessBuilder(
	            "cmd.exe", "/c", "cd \"C:\\fscrawler-es7-2.9\\bin\" &dir ");
	        builder.redirectErrorStream(true);
	        Process p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        while (true) {
	            line = r.readLine();
	            if (line == null) { break; }
	            System.out.println(line);
	        }
	        
	        ProcessBuilder builder1 = new ProcessBuilder(
		            "cmd.exe", "/c", "fscrawler reals --loop 0 --rest &dir ");
		        builder.redirectErrorStream(true);
		        Process p1 = builder.start();
		        BufferedReader r1 = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        String line1;
		        while (true) {
		            line1 = r.readLine();
		            if (line1 == null) { break; }
		            System.out.println(line1);
		        } */
			String[] command =
			    {
			        "cmd",
			    };
			    Process p;
				try {
					p = Runtime.getRuntime().exec(command);
				        new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			              new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
				                PrintWriter stdin = new PrintWriter(p.getOutputStream());
				                stdin.println("cd C:\\fscrawler-es7-2.9\\bin");
				                
				                stdin.println("fscrawler reals --loop 0 --rest");
				                stdin.close();
				                p.waitFor();
			    	} catch (Exception e) {
			 		e.printStackTrace();
				}
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 

}
}
package talan.Elastic;



import java.io.PrintWriter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import talan.Elastic.configuration.SyncPipe;

@SpringBootApplication

public class ElasticApplication {

	public static void main(String[] args)  {
		SpringApplication.run(ElasticApplication.class, args);
		
		
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
	/*@Bean
	public void runServers() {
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
		
		
		
		
		
		
		
		
	}*/
	
}	
	
	

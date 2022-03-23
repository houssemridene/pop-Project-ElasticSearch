package talan.Elastic.services;


import com.fasterxml.jackson.databind.ObjectMapper;


import talan.Elastic.entities.Document;
import talan.Elastic.entities.FileDetails;
import talan.Elastic.entities.Meta;
import talan.Elastic.entities.Vehicle;
import talan.Elastic.entities.vehicle1;
import talan.Elastic.entities.Path;
import talan.Elastic.helper.Indices;
import talan.Elastic.search.SearchRequestDTO;
import talan.Elastic.search.util.SearchUtil;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.reindex.ScrollableHitSource.Hit;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;




@Service
public class VehicleService {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(VehicleService.class);
	private static final String JSON = null;

    private final RestHighLevelClient client;

    @Autowired
    public VehicleService(RestHighLevelClient client) {
        this.client = client;
    }
    
    //public List<vehicle1> search(final SearchRequestDTO dto) {
    	public List<Document> search(final SearchRequestDTO dto) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                Indices.REALS_INDEX,
                dto
        );
        
        return searchInternal(request);
    }
    	
    	
    	
    	
    public List<Document> getAllDocumentsCreatedSince(final Date date) {
    	
    			//conversion du date se fait ici 
    	        final SearchRequest request = SearchUtil.buildSearchRequest(
    	                Indices.PAPIERS_INDEX,
    	                "file.indexing_date",
    	                date
    	        );

    	        return searchInternal(request);
    	    }
    	
    	
    	
    	
    	
    	
    	private List<Document> searchInternal(final SearchRequest request) {
    		if (request == null) {
                LOG.error("Failed to build search request");
                return Collections.emptyList();
            }

            try {
                final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
                final SearchHit[] searchHits = response.getHits().getHits();
                
                
                	
                
               //final List<Vehicle> vehicles = new ArrayList<>(searchHits.length);
               //final List<vehicle1> vehicles1 = new ArrayList<>(searchHits.length);
                final List<Document> documents = new ArrayList<>(searchHits.length);
                
                
                for (SearchHit hit : searchHits) {
                	 
                	
                	/*Vehicle v = MAPPER.readValue(hit.getSourceAsString(), Vehicle.class);
                	hit.getSourceAsString().
                	vehicle1 v1 = new vehicle1();
                	v1.setId(hit.getId());
                	v1.setSource(v);
                	
                    vehicles1.add(v1
                    		
                           
                    		
                    		
                    );*/
                	Document document = new Document();
                	
                	document.setId(hit.getId());
                	Map<String,Object> map = hit.getSourceAsMap();
                	/*Iterator iterator = map.entrySet().iterator();
                	
                	while (iterator.hasNext()) {
                		
                		Map.Entry mapentry = (Map.Entry) iterator.next();
                		document.setId(hit.getId());
                		document.setFile(null);*/
                	//final List<Object> sources = new ArrayList<>();
                	for (Map.Entry<String, Object> pair : map.entrySet()) {
                		
                	    //System.out.println(String.format("Key (name) is: %s, Value (age) is : %s", pair.getKey(), pair.getValue()));
                		/*if(pair.getKey() == "path") {
                			Path path = MAPPER.convertValue(pair.getValue(), Path.class);
                			document.setPath(path);
                			 
                		}*/
                		switch(pair.getKey()){
                		   
                	       case "path": 
                	    	   Path path = MAPPER.convertValue(pair.getValue(), Path.class);
                	    	   document.setPath(path);
                	    	   break;
                	       case "file":
                	    	   FileDetails file = MAPPER.convertValue(pair.getValue(), FileDetails.class);
                	    	   document.setFile(file);
                	    	   break;
                	       case "meta":
                	    	   Meta meta = new Meta();
                	    	   if (pair.getValue() != null) {
                	    		   
                	    	   
                	    		  meta = MAPPER.convertValue(pair.getValue(), Meta.class);
                	    	   }
                	    	   
                	    	   document.setMeta(meta);
                	    	   break;
                	       case "content":
                	    	   document.setContent(String.valueOf(pair.getValue()));
                	    	   break;	
                	       
                	   }
                		
                	};
                	documents.add(document);
                	/*document.setId(hit.getId());
                	document.setPath(MAPPER.readValue(sources.get(0)),Path.class);*/
                	
                
                };

                return documents;
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
               return Collections.emptyList();
            }
    		
    		
    		
    	
    	}

    

    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    public Boolean index(final Vehicle vehicle) {
        try {
            final String vehicleAsString = MAPPER.writeValueAsString(vehicle);

            final IndexRequest request = new IndexRequest(Indices.VEHICLE_INDEX);
            request.id(vehicle.getId());
            request.source(vehicleAsString, XContentType.JSON);

            final IndexResponse response = client.index(request, RequestOptions.DEFAULT);

            return response != null && response.status().equals(RestStatus.OK);
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    public Vehicle getById(final String vehicleId) {
        try {
            final GetResponse documentFields = client.get(
                    new GetRequest(Indices.VEHICLE_INDEX, vehicleId),
                    RequestOptions.DEFAULT
            );
            if (documentFields == null || documentFields.isSourceEmpty()) {
                return null;
            }

            return MAPPER.readValue(documentFields.getSourceAsString(), Vehicle.class);
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}

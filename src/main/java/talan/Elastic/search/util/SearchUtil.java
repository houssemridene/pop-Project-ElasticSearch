package talan.Elastic.search.util;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.CollectionUtils;

import talan.Elastic.search.SearchRequestDTO;

import java.util.Date;
import java.util.List;

public final class SearchUtil {

    private SearchUtil() {}
    
    
    
    	//1
		public static SearchRequest buildSearchRequest(final String indexName,final SearchRequestDTO dto ) {
		            
			try {    
				
				final int page = dto.getPage();
	            final int size = dto.getSize();
	            final int from = page <= 0 ? 0 : page * size;
				

				 SearchSourceBuilder builder = new SearchSourceBuilder().from(from).size(size).postFilter(getQueryBuilder(dto));
				 
			if (dto.getSortBy() != null) {
		                builder = builder.sort(
		                        dto.getSortBy(),
		                        dto.getOrder() != null ? dto.getOrder() : SortOrder.ASC
		                );
		        }
							
		
		final SearchRequest request = new SearchRequest(indexName);
		request.source(builder);
		
		return request;} catch (final Exception e) {
				e.printStackTrace();
				return null;
		}
		}
		
		
		
		//2
		private static QueryBuilder getQueryBuilder(final SearchRequestDTO dto) {
	        if (dto == null) {
	            return null;
	        }

	        final List<String> fields = dto.getFields();
	        if (CollectionUtils.isEmpty(fields)) {
	            return null;
	        }

	        if (fields.size() > 1) {
	            final MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(dto.getSearchTerm())
	                    .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
	                    .operator(Operator.OR);

	            fields.forEach(queryBuilder::field);

	            return queryBuilder;
	        }

	        return fields.stream()
	                .findFirst()
	                .map(field ->
	                        QueryBuilders.matchQuery(field, dto.getSearchTerm())
	                                .operator(Operator.OR))
	                .orElse(null);
	    }
		
		
		
		/////////////////////////////////////////////// 1 et 2 travaillent ensemble
		
		
		
		
		//3
		 public static SearchRequest buildSearchRequest(final String indexName,
                 final String field,
                 final Date date) {
			 	try {
			 		final SearchSourceBuilder builder = new SearchSourceBuilder()
			 				.postFilter(getQueryBuilder(field, date));

			 		final SearchRequest request = new SearchRequest(indexName);
			 		request.source(builder);

					return request;
					} catch (final Exception e) {
					e.printStackTrace();
					return null;
					}
				}
		 
		 
		 
		 
		 //4
		 private static QueryBuilder getQueryBuilder(final String field, final Date date) {
		        return QueryBuilders.rangeQuery(field).gte(date);
		    }
		 
		 //////////////////// 3 et 4  travaillent ensemble
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 

    

    

}  
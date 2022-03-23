package talan.Elastic.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import talan.Elastic.entities.User;



public interface UserRepository extends ElasticsearchRepository<User, Integer> {
	

}

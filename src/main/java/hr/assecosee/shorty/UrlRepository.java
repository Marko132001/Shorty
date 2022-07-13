package hr.assecosee.shorty;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UrlRepository extends CrudRepository<UrlKeyValue, String>{
	
	UrlKeyValue findByoriginalUrlAndUserName(String originalUrl, String UserName);
	
	UrlKeyValue findByshortUrlAndUserName(String shortUrl, String UserName);
	
	@Modifying
	@Transactional
	@Query("update url u set u.numberOfRedirects = :number WHERE originalUrl = :originalUrl AND userName = :userName")
	void updateNumberOfRedirects(@Param("originalUrl") String originalUrl, @Param("userName") String UserName, @Param("number") int number);
	
	List<UrlKeyValue> findAllByUserName(String UserName);
	

}

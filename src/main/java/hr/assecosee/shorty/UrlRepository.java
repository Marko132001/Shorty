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
	
	@Query("SELECT a FROM url a WHERE originalUrl = :originalUrl AND UserName = :UserName")
	UrlKeyValue findByoriginalUrlAndUserName(@Param("originalUrl") String originalUrl, @Param("UserName") String UserName);
	
	@Query("SELECT a FROM url a WHERE shortUrl = :shortUrl AND UserName = :UserName")
	UrlKeyValue findByshortUrlAndUserName(@Param("shortUrl") String shortUrl, @Param("UserName") String UserName);
	
	@Modifying
	@Transactional
	@Query("update url u set u.numberOfRedirects = :number WHERE originalUrl = :originalUrl AND UserName = :UserName")
	void updateNumberOfRedirects(@Param("originalUrl") String originalUrl, @Param("UserName") String UserName, @Param("number") int number);
	
	@Query("SELECT a FROM url a WHERE UserName = :UserName")
	List<UrlKeyValue> findAllByUserName(@Param("UserName") String UserName);
	

}

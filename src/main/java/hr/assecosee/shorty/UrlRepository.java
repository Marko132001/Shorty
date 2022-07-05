package hr.assecosee.shorty;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends CrudRepository<UrlKeyValue, String>{
	
	@Query("SELECT a FROM url a WHERE originalUrl = :originalUrl AND UserName = :UserName")
	UrlKeyValue findByoriginalUrlAndUserName(@Param("originalUrl") String originalUrl, @Param("UserName") String UserName);
	
	@Query("SELECT a FROM url a WHERE shortUrl = :shortUrl AND UserName = :UserName")
	UrlKeyValue findByshortUrlAndUserName(@Param("shortUrl") String shortUrl, @Param("UserName") String UserName);
	

}

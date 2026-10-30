package world.sohail.short_url.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.sohail.short_url.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, String> {}

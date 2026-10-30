package world.sohail.short_url.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import world.sohail.short_url.dto.UrlRequest;
import world.sohail.short_url.entity.Url;
import world.sohail.short_url.entity.User;
import world.sohail.short_url.repository.UrlRepository;
import world.sohail.short_url.repository.UserRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final UserRepository userRepository;

    public UrlService(UrlRepository urlRepository, UserRepository userRepository){
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
    }

    private void validateLongUrl(String longUrl) {
        try {
            URI uri = new URI(longUrl.trim());
            String scheme = uri.getScheme();

            if (!uri.isAbsolute()
                    || uri.getHost() == null
                    || scheme == null
                    || !(scheme.equalsIgnoreCase("http")
                    || scheme.equalsIgnoreCase("https"))) {
                throw new IllegalArgumentException("Invalid HTTP/HTTPS URL");
            }

        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Malformed URL", ex);
        }
    }

    public String createUrl(UrlRequest request){
        UUID userId = request.getUserId();
        String longUrl = request.getLongUrl();

        //validate longUrl:
        validateLongUrl(longUrl);

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user not found"));

        // generate the short code for the url:
        String shortCode = "0123456";

        //create a url entity:
        Url urlEntity = new Url(shortCode, longUrl, user);

        urlRepository.save(urlEntity);

        return shortCode;
    }

    public Url getUrlByShortCode(String shortCode){
        return urlRepository.findById(shortCode).orElseThrow(() -> new EntityNotFoundException("short url not found"));
    }
}

package world.sohail.short_url.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import world.sohail.short_url.dto.UrlRequest;
import world.sohail.short_url.entity.Url;
import world.sohail.short_url.service.UrlService;

import java.net.URI;

@Controller
public class UrlController {
    private final UrlService urlService;

    @Value("${app.base-url}")
    private String baseUrl;

    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<String> createUrl(@Valid @RequestBody UrlRequest request){
        String shortCode = urlService.createUrl(request);
        String shortUrl = baseUrl + "/" + shortCode;

        return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode){
        Url url = urlService.getUrlByShortCode(shortCode);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url.getLongUrl())).body(null);
    }
}

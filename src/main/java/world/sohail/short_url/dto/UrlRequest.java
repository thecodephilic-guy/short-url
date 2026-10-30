package world.sohail.short_url.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record UrlRequest(
        @NotEmpty(message = "userId must be provided")
        UUID userId,

        @NotBlank(message = "longUrl must be provided")
        String longUrl
) {
    public UUID getUserId() { return userId; }
    public String getLongUrl() { return longUrl; }
}

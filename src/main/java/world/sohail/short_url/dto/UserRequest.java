package world.sohail.short_url.dto;

public record UserRequest(
        String name,
        String email
) {
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
}

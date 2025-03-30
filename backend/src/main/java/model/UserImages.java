package model;

public class UserImages {
    private String username;
    private String image;

    public UserImages() {}

    public UserImages(String username, String image) {
        this.username = username;
        this.image = image;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    @Override
    public String toString() {
        return "UserImages{" +
                "username='" + username + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
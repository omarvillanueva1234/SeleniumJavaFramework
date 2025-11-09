package BaseFolder.Models.DataMapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserTokenDTO {

    @JsonProperty("sproutHrDomain")
    private String sproutHrDomain; // Internal Use only

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;


    public UserTokenDTO(String sproutHrDomain, String username, String password) {
        this.sproutHrDomain = sproutHrDomain;
        this.username = username;
        this.password = password;
    }


    public void setSproutHrDomain(String sproutHrDomain) {
        this.sproutHrDomain = sproutHrDomain;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

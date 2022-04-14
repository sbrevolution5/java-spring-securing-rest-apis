package io.jzheaux.springsecurity.resolutions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.UUID;

@Entity(name="authorities")
public class UserAuthority {
    UserAuthority(){}
    public UserAuthority(User user, String authority){
        this.id = UUID.randomUUID();
        this.user = user;
        this.authority = authority;
    }
    @Id
    private UUID id;

    @Column
    private String authority;

    @JoinColumn(name="username",referencedColumnName = "username")
    private User user;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

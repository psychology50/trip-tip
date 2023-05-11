package yu.softwareDesign.TripTip.global.config.security;

import org.springframework.security.core.GrantedAuthority;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.Collection;

public class UserContext extends org.springframework.security.core.userdetails.User {
    private final User user;

    public UserContext(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }

    User getUser() { return user; }
}

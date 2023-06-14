package yu.softwareDesign.TripTip.global.config.security.Authentication;

import org.springframework.security.core.GrantedAuthority;
import yu.softwareDesign.TripTip.domain.user.domain.CustomUser;

import java.util.Collection;

public class UserContext extends org.springframework.security.core.userdetails.User {
    private final CustomUser user;

    public UserContext(CustomUser user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }

    CustomUser getUser() { return user; }
}

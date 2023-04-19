package yu.softwareDesign.TripTip.global.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import yu.softwareDesign.TripTip.domain.subscriber.domain.Subscriber;

import java.util.Collection;

public class UserContext extends User {
    private final Subscriber subscriber;

    public UserContext(Subscriber subscriber, Collection<? extends GrantedAuthority> authorities) {
        super(subscriber.getUsername(), subscriber.getPassword(), authorities);
        this.subscriber = subscriber;
    }

    Subscriber getUser() { return subscriber; }
}

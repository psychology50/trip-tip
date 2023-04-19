package yu.softwareDesign.TripTip.global.config.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yu.softwareDesign.TripTip.domain.subscriber.dao.SubscriberRepo;
import yu.softwareDesign.TripTip.domain.subscriber.domain.Subscriber;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final SubscriberRepo subscriberRepo;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Subscriber subscriber = subscriberRepo.findByNickname(nickname).orElseThrow(() ->
                new UsernameNotFoundException("해당 닉네임을 찾을 수 없습니다."));

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(subscriber.getRole().toString()));
        return new UserContext(subscriber, roles);
    }
}

package yu.softwareDesign.TripTip.global.config.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import yu.softwareDesign.TripTip.global.config.security.Authentication.CustomAccessDeniedHandler;
import yu.softwareDesign.TripTip.global.config.security.Authentication.CustomAuthenticationFailureHandler;
import yu.softwareDesign.TripTip.global.config.security.Authentication.CustomAuthenticationProvider;
import yu.softwareDesign.TripTip.global.config.security.Permission.MethodSecurityConfig;

@Configuration
@EnableWebSecurity
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Import(MethodSecurityConfig.class)
//@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();}

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/img/**", "/icon/**", "/font/**");
    }

    @Bean
    AuthenticationProvider authenticationProvider() { return new CustomAuthenticationProvider(); }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    private AccessDeniedHandler accessDeniedHandler() {
//        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
//        accessDeniedHandler.setErrorPage("/denied");
        return new CustomAccessDeniedHandler();
    }

//    private providerAdminServer(AdminServerProperties adminServerProperties) {
//        return
//    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable(); // REST API 에서는 제거
        http.authorizeHttpRequests()
                    .requestMatchers("/", "/api", "/api/users/signup", "/api/users/signin").permitAll()
                    .requestMatchers("/h2-console/**", "favicon.ico").permitAll() // 과제 제출용
                    .requestMatchers("/api/admin/*").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .usernameParameter("nickname")
                    .loginPage("/api/users/signin")
                    .failureHandler(authenticationFailureHandler())
                    .loginProcessingUrl("/api/users/signin")
                    .defaultSuccessUrl("/api")
                    .permitAll()
                .and()
                    .headers().frameOptions().sameOrigin()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("api/users/logout"))
                    .logoutSuccessUrl("/api/users/signin")
                    .invalidateHttpSession(true)
                    .permitAll()
                .and()
                    .httpBasic();


        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        return http.build();
    }
}

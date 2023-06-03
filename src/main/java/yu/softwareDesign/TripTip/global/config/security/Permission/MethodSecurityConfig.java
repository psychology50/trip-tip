package yu.softwareDesign.TripTip.global.config.security.Permission;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class MethodSecurityConfig {
    private final ApplicationContext applicationContext;

    @Bean
    protected MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        CustomSecurityExpressionHandler expressionHandler = new CustomSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new CustomPermissionExpression());
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }
}

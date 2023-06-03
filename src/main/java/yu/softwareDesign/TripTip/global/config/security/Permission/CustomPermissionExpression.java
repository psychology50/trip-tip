package yu.softwareDesign.TripTip.global.config.security.Permission;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Log4j2
public class CustomPermissionExpression implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || targetDomainObject == null || !(permission instanceof String))
            return false;
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();
        log.info("targetType : {}", targetType);
        return hasPrivilege(authentication, targetType, permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if (authentication == null || targetType == null || !(permission instanceof String))
            return false;
        log.info("targetType : {}", targetType);
        return hasPrivilege(authentication, targetType.toUpperCase(), permission.toString().toUpperCase());
    }

    /**
     * 권한이 있는지 확인하는 메소드<br/>
     * {@code @PostAuthorize("hasAuthority('FOO_READ_PRIVILEGE')")}
     * @param authentication
     * @param targetType
     * @param permission
     * @return 권한이 있으면 true, 없으면 false
     */
    private boolean hasPrivilege(Authentication authentication, String targetType, String permission) {
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            if (grantedAuthority.getAuthority().startsWith(targetType) &&
                    grantedAuthority.getAuthority().contains(permission)) {
                return true;
            }
        }
        return false;
    }
}

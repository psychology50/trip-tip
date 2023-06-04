package yu.softwareDesign.TripTip.global.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import yu.softwareDesign.TripTip.domain.user.application.UserSearchService;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.logging.Logger;

@Service("securityService")
@RequiredArgsConstructor
@Log4j2
public class SecurityService {

    private final UserSearchService userSearchService;
    private final Logger logger = Logger.getLogger(SecurityService.class.getName());
    Authentication authentication;
    ServletRequestAttributes attributes;

    public boolean isLeader(Long group_id) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userSearchService.findUserById(((User) authentication.getPrincipal()).getUser_id()).orElseThrow(()
            -> new RuntimeException("해당 유저를 찾을 수 없습니다."));
        log.info("isLeader = {}", user);

        boolean is_leader = user.getIs_leader().stream().anyMatch(group -> group.getGroup_id().equals(group_id));

        if (!is_leader)
            exception_handling();
        return is_leader;
    }

    public boolean isMember(Long group_id) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userSearchService.findUserById(((User) authentication.getPrincipal()).getUser_id()).orElseThrow(()
                -> new RuntimeException("해당 유저를 찾을 수 없습니다."));
        log.info("isMember = {}", user);

        boolean is_member = user.getMembers().stream().anyMatch(member -> member.getGroup().getGroup_id().equals(group_id));

        if (!is_member)
            exception_handling();
        return is_member;
    }

    public boolean isPayer(Long receipt_id) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userSearchService.findUserById(((User) authentication.getPrincipal()).getUser_id()).orElseThrow(()
                -> new RuntimeException("해당 유저를 찾을 수 없습니다."));
        log.info("isPayer = {}", user);

        boolean is_payer = user.getPay_receipts().stream().anyMatch(receipt -> receipt.getReceipt_id().equals(receipt_id));

        if (!is_payer)
            exception_handling();
        return is_payer;
    }

    private void exception_handling() {
        attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
//                String currentUrl = attributes.getRequest().getRequestURI();
//                String currentUrl = attributes.getRequest().getRequestURL().toString();
            // XXX: 신뢰할 수 없는 url. 좋지 않다.
            String currentUrl = attributes.getRequest().getHeader("referer");
            attributes.getRequest().getSession().setAttribute("accessDeniedUrl", currentUrl);
        }
        log.info("attributes = {}", attributes);

        throw new AccessDeniedException("You do not have permission to access this resource.");
    }
}

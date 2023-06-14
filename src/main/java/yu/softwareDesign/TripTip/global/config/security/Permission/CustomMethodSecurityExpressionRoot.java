package yu.softwareDesign.TripTip.global.config.security.Permission;


import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import yu.softwareDesign.TripTip.domain.user.domain.CustomUser;

@Log4j2
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot
                                                implements MethodSecurityExpressionOperations {
    private Object filterObject;
    private Object returnObject;
    private Object target;

    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean isLeader(Long group_id) {
        CustomUser user = (CustomUser) this.getPrincipal();
        log.info("isLeader = {}", user);
        return user.getIs_leader().stream().anyMatch(group -> group.getGroup_id().equals(group_id));
    }

    public boolean isMember(Long group_id) {
        CustomUser user = (CustomUser) this.getPrincipal();
        log.info("isMember = {}", user);
        return user.getMembers().stream().anyMatch(member -> member.getGroup().getGroup_id().equals(group_id));
    }

    public boolean isPayer(Long receipt_id) {
        CustomUser user = (CustomUser) this.getPrincipal();
        log.info("isPayer = {}", user);
        return user.getPay_receipts().stream().anyMatch(receipt -> receipt.getReceipt_id().equals(receipt_id));
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    void setThis(Object target) {
        this.target = target;
    }

    @Override
    public Object getThis() {
        return target;
    }
}

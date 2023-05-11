package yu.softwareDesign.TripTip.domain.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yu.softwareDesign.TripTip.domain.member.domain.Member;

public interface MemberRepo extends JpaRepository<Member, Long> {
}

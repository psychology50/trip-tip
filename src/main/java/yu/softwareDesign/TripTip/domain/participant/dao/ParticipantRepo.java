package yu.softwareDesign.TripTip.domain.participant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;

public interface ParticipantRepo extends JpaRepository<Participant, Long> {
}

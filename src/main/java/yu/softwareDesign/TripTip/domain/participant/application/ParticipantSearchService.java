package yu.softwareDesign.TripTip.domain.participant.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.participant.dao.ParticipantRepo;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;

@Service
@RequiredArgsConstructor
@Log4j2
public class ParticipantSearchService {
    private final ParticipantRepo participantRepo;


}

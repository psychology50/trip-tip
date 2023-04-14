package yu.softwareDesign.TripTip.domain.subscriber.domain;

import jakarta.persistence.Column;
import lombok.NonNull;

public class Phone {
    @Column(name = "local") @NonNull
    private String local;
    @Column(name = "local_prefix") @NonNull
    private String local_prefix;
    @Column(name = "local_suffix") @NonNull
    private String local_suffix;
}

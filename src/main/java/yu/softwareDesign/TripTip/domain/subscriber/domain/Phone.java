package yu.softwareDesign.TripTip.domain.subscriber.domain;

import jakarta.persistence.Column;
import lombok.NonNull;

public class Phone {
    @Column(name = "local_num") @NonNull
    private String local;
    @Column(name = "local_prefix_num") @NonNull
    private String local_prefix;
    @Column(name = "local_suffix_num") @NonNull
    private String local_suffix;
}

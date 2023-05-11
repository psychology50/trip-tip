package yu.softwareDesign.TripTip.domain.subscriber.domain;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.NonNull;

public class Phone {
    @Column(name = "local") @NonNull
    private String local;
    @Column(name = "local_prefix") @NonNull
    private String local_prefix;
    @Column(name = "local_suffix") @NonNull
    private String local_suffix;

    @Builder
    public Phone(@NonNull String local, @NonNull String local_prefix, @NonNull String local_suffix) {
        this.local = local;
        this.local_prefix = local_prefix;
        this.local_suffix = local_suffix;
    }
}

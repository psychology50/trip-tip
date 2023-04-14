package yu.softwareDesign.TripTip.domain.subscriber.domain;

import jakarta.persistence.Column;
import lombok.NonNull;

public class Address {
    @Column(name = "county") @NonNull
    private String county;

    @Column(name = "state") @NonNull
    private String state;

    @Column(name = "city") @NonNull
    private String city;

    @Column(name = "zip_code") @NonNull
    private String zipCode;
}

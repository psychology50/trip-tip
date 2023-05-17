package yu.softwareDesign.TripTip.domain.user.domain;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class Address {
    @Column(name = "country") @NonNull
    private String country;

    @Column(name = "state") @NonNull
    private String state;

    @Column(name = "city") @NonNull
    private String city;

    @Column(name = "zip_code") @NonNull
    private String zipCode;

    @Builder
    public Address(@NonNull String country, @NonNull String state, @NonNull String city, @NonNull String zipCode) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
    }
}

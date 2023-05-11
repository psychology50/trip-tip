package yu.softwareDesign.TripTip.domain.subscriber.domain;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

public class Bank {
    @Column(name="bank_name") @NonNull
    private String bank_name;
    @Column(name="bank_account") @NonNull
    private String bank_account;

    @Builder
    public Bank(@NonNull String bank_name, @NonNull String bank_account) {
        this.bank_name = bank_name;
        this.bank_account = bank_account;
    }
}

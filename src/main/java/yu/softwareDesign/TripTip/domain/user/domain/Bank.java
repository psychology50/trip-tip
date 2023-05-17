package yu.softwareDesign.TripTip.domain.user.domain;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
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

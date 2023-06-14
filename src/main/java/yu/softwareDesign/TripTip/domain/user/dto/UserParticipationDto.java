package yu.softwareDesign.TripTip.domain.user.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@ToString(of = {"username", "nickname", "cost"})
public class UserParticipationDto {
    private Long user_id;
    private String username;
    private String nickname;
    private Double cost;
    private Boolean selected;

    @Builder
    public UserParticipationDto(Long user_id, String username, String nickname, Double cost, Boolean selected) {
        this.user_id = user_id;
        this.username = username;
        this.nickname = nickname;
        this.cost = cost;
        this.selected = selected;
    }
}

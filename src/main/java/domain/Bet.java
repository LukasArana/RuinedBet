package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bet {
    private fee fee;
    private Float stake;
    private String username;

    @Id
    private Question betEvent;

    public Bet(fee fee, Float stake, String username, Question betEvent) {
        this.fee = fee;
        this.stake = stake;
        this.username = username;
        this.betEvent = betEvent;
    }
}

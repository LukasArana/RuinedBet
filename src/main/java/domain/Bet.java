package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bet {

    private fee fee;
    @Id
    private Float stake;
    //private String username;

    //@Id
    //private Question betEvent;

    public Bet(domain.fee fee, Float stake) {
        this.fee = fee;
        this.stake = stake;
    }


    /**
    public Bet(fee fee, Float stake, String username, Question betEvent) {
        this.fee = fee;
        this.stake = stake;
        this.username = username;
        this.betEvent = betEvent;
    }
     */
}

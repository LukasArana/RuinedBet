package domain;

public class Bet {
    private fee fee;
    private Float stake;

    public Bet(fee fee, Float stake) {
        this.fee = fee;
        this.stake = stake;
    }
}

package domain;

import java.time.LocalDate;
import java.util.Date;

public class Movement {
    private LocalDate date;
    private String event;
    private String balance;

    public Movement(LocalDate date, String event, String balance) {
        this.date = date;
        this.event = event;
        this.balance = balance;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "date=" + date +
                ", event='" + event + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}

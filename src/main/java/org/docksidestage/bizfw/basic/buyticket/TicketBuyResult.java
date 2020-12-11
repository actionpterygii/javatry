package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {

    private final Ticket ticket;
    private final int change;

    public TicketBuyResult(Ticket returnTicket, int change) {
        this.ticket = returnTicket;
        this.change = change;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public int getChange() {
        return change;
    }

}

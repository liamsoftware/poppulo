package com.poppulo.lotteryservice;

public interface RulePolicy {

    Ticket computeResult(Ticket ticket);

}

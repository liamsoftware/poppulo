package com.poppulo.lotteryservice;

import java.util.List;

public class TicketFormatHelper {

    public static boolean isValidFormat(Ticket ticket) {
        List<Integer[]> lines = ticket.getLines();
        for (Integer[] aLine : lines) {
            if (aLine.length != 3) {
                return false;
            }
            for (int i = 0; i < 3; i++) {
                if (aLine[i] < 0 || aLine[i] > 2) {
                    return false;
                }
            }
        }
        return true;
    }
}

package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TrainTicketDTO {
    private String selectedSeats;
    private List<TicketItem> ticketList;
    // 列车ID
    private String trainId;
}

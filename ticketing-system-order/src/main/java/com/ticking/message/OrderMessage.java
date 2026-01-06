package com.ticking.message;

import com.ticking.entity.TrainTicketDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单消息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMessage {
    private String id;
    private TrainTicketDTO trainTicketDTO;
    private String ts;
}

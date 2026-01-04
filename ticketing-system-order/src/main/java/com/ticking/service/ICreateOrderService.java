package com.ticking.service;

import com.ticking.entity.TrainTicketDTO;

public interface ICreateOrderService {
    boolean createOrder(TrainTicketDTO trainTicketDTO);
}

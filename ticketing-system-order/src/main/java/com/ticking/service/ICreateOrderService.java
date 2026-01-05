package com.ticking.service;

import com.ticking.entity.TrainTicketDTO;
import com.ticking.entity.UserEntity;

public interface ICreateOrderService {
    boolean createOrder(TrainTicketDTO trainTicketDTO,Long userId);
}

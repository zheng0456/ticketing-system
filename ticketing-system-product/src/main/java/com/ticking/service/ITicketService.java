package com.ticking.service;

import java.util.List;
import java.util.Map;

public interface ITicketService {
    List<Map<String, Object>> selectAllTickets(Map<String, Object> tickets);
}

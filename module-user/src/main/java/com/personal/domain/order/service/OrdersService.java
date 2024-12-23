package com.personal.domain.order.service;

import com.personal.domain.order.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

}

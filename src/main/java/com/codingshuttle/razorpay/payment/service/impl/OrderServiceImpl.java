package com.codingshuttle.razorpay.payment.service.impl;

import com.codingshuttle.razorpay.common.eums.OrderStatus;
import com.codingshuttle.razorpay.common.exception.BusinessRuleViolationException;
import com.codingshuttle.razorpay.common.exception.DuplicateResourceException;
import com.codingshuttle.razorpay.common.exception.ResourceNotFoundException;
import com.codingshuttle.razorpay.payment.dto.request.CreateOrderRequest;
import com.codingshuttle.razorpay.payment.dto.response.OrderResponse;
import com.codingshuttle.razorpay.payment.dto.response.PaymentResponse;
import com.codingshuttle.razorpay.payment.entity.OrderRecord;
import com.codingshuttle.razorpay.payment.entity.Payment;
import com.codingshuttle.razorpay.payment.mapper.OrderMapper;
import com.codingshuttle.razorpay.payment.mapper.PaymentMapper;
import com.codingshuttle.razorpay.payment.repository.OrderRepository;
import com.codingshuttle.razorpay.payment.repository.PaymentRepository;
import com.codingshuttle.razorpay.payment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private  final OrderRepository orderRepository;
    private  final PaymentRepository paymentRepository;
    private  final PaymentMapper paymentMapper;
    private final OrderMapper orderMapper;

    @Value("${payment.order.default-order-expiry-minutes:30}")
    private  int defaultOrderExpiryMinute;

    @Override
    @Transactional
    public OrderResponse createOrder(UUID merchantId, CreateOrderRequest request) {
        if(request.receipt()!=null && orderRepository.existsByMerchantIdAndReceipt(merchantId, request.receipt()))
        {
            throw new DuplicateResourceException("Order_RECEIPT_DUPLICATE", "Order with receipt already exist "+request.receipt());
        }

        OrderRecord orderRecord= OrderRecord.builder()
                .receipt(request.receipt())
                .amount(request.amount())
                .notes(request.notes())
                .merchantId(merchantId)
                .orderStatus(OrderStatus.CREATED)
                .expiresAt(request.expiredAt()!=null?request.expiredAt():
                        LocalDateTime.now().plusMinutes(defaultOrderExpiryMinute))
                .build();

        orderRecord=orderRepository.save(orderRecord);

//        return new OrderResponse(orderRecord.getId(),
//                orderRecord.getMerchantId(),
//                orderRecord.getReceipt(),
//                orderRecord.getAmount(),
//                orderRecord.getOrderStatus(),
//                orderRecord.getAttempts(),
//                orderRecord.getNotes(),
//                orderRecord.getExpiresAt(),
//                null);

        return orderMapper.toResponse(orderRecord);

    }

    @Override
    public OrderResponse getById(UUID orderId, UUID merchantId) {

        OrderRecord orderRecord= orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

//        return new OrderResponse(orderRecord.getId(),
//                orderRecord.getMerchantId(),
//                orderRecord.getReceipt(),
//                orderRecord.getAmount(),
//                orderRecord.getOrderStatus(),
//                orderRecord.getAttempts(),
//                orderRecord.getNotes(),
//                orderRecord.getExpiresAt(),
//                null);
        return orderMapper.toResponse(orderRecord);
    }

    @Override
    @Transactional
    public OrderResponse cancelOrder(UUID orderId, UUID merchantId) {

        OrderRecord orderRecord= orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        if(orderRecord.getOrderStatus()==OrderStatus.CANCEL || orderRecord.getOrderStatus() == OrderStatus.PAID)
            throw new BusinessRuleViolationException("ORDER_CANNOT_CANCEL", "Order cannot cancel with status "+orderRecord.getOrderStatus());

        orderRecord.setOrderStatus(OrderStatus.CANCEL);
        orderRepository.save(orderRecord);

//        return new OrderResponse(orderRecord.getId(),
//                orderRecord.getMerchantId(),
//                orderRecord.getReceipt(),
//                orderRecord.getAmount(),
//                orderRecord.getOrderStatus(),
//                orderRecord.getAttempts(),
//                orderRecord.getNotes(),
//                orderRecord.getExpiresAt(),
//                null);
        return orderMapper.toResponse(orderRecord);

    }

    @Override
    public List<PaymentResponse> paymentList(UUID orderId, UUID merchantId) {
        OrderRecord orderRecord= orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        List<Payment> payment=paymentRepository.findByOrder_Id(orderRecord);

        return paymentMapper.toResponseList(payment);
    }


}

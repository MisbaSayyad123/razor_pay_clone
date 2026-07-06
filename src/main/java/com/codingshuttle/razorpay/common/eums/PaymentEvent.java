package com.codingshuttle.razorpay.common.eums;

public enum PaymentEvent {
    AUTHORIZED_ATTEMPT,
    AUTHORIZED_SUCCESS,
    AUTHORIZED_FAIL,
    CAPTURE_REQUEST,
    CAPTURE_SUCCESS,
    CAPTURE_FAIL,
    REFUND_INIT,
    REFUND_COMPLETED,
    SETTLE,
    CANCEL,
    CAPTURE_TIMEOUT

}

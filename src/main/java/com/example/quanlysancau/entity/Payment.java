package com.example.quanlysancau.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = Payment.PaymentConstant.TABLE_NAME, schema = Payment.PaymentConstant.SCHEMA)
public class Payment {
    public static class PaymentConstant {
        public static final String TABLE_NAME = "payments";
        public static final String SCHEMA = "badminton";

        public static final String ID = "id";
        public static final String BOOKING_ID = "booking_id";
        public static final String AMOUNT = "amount";
        public static final String PAYMENT_METHOD = "payment_method";
        public static final String TRANSACTION_ID = "transaction_id";
        public static final String STATUS = "status";
        public static final String CREATED_AT = "created_at";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PaymentConstant.ID)
    Integer id;

    @Column(name = PaymentConstant.BOOKING_ID, nullable = false)
    Integer bookingId;

    @Column(name = PaymentConstant.AMOUNT, nullable = false)
    Double amount;

    @Column(name = PaymentConstant.PAYMENT_METHOD, nullable = false)
    String paymentMethod;

    @Column(name = PaymentConstant.TRANSACTION_ID)
    String transactionId;

    @Column(name = PaymentConstant.STATUS)
    String status;

    @Column(name = PaymentConstant.CREATED_AT)
    Long createdAt;
}

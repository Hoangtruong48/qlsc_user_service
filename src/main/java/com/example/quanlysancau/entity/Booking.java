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
@Table(name = Booking.BookingConstant.TABLE_NAME, schema = Booking.BookingConstant.SCHEMA)
public class Booking {
    public static class BookingConstant {
        public static final String TABLE_NAME = "bookings";
        public static final String SCHEMA = "badminton";

        public static final String ID = "id";
        public static final String COURT_DETAIL_ID = "court_detail_id";
        public static final String TIME_SLOT_ID = "time_slot_id";
        public static final String USER_ID = "user_id";
        public static final String BOOKING_DATE = "booking_date";
        public static final String ACTUAL_START_TIME = "actual_start_time";
        public static final String ACTUAL_END_TIME = "actual_end_time";
        public static final String STATUS = "status";
        public static final String PAYMENT_STATUS = "payment_status";
        public static final String TOTAL_AMOUNT = "total_amount";
        public static final String NOTES = "notes";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = BookingConstant.ID)
    Integer id;

    @Column(name = BookingConstant.COURT_DETAIL_ID, nullable = false)
    Integer courtDetailId;

    @Column(name = BookingConstant.TIME_SLOT_ID, nullable = false)
    Integer timeSlotId;

    @Column(name = BookingConstant.USER_ID, nullable = false)
    Integer userId;

    @Column(name = BookingConstant.BOOKING_DATE, nullable = false)
    Long bookingDate;

    @Column(name = BookingConstant.ACTUAL_START_TIME)
    String actualStartTime;

    @Column(name = BookingConstant.ACTUAL_END_TIME)
    String actualEndTime;

    @Column(name = BookingConstant.STATUS)
    String status;

    @Column(name = BookingConstant.PAYMENT_STATUS)
    String paymentStatus;

    @Column(name = BookingConstant.TOTAL_AMOUNT)
    Double totalAmount;

    @Column(name = BookingConstant.NOTES)
    String notes;

    @Column(name = BookingConstant.CREATED_AT)
    Long createdAt;

    @Column(name = BookingConstant.UPDATED_AT)
    Long updatedAt;
}

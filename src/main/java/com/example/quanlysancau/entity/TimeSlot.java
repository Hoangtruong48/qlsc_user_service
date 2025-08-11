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
@Table(name = TimeSlot.TimeSlotConstant.TABLE_NAME, schema = TimeSlot.TimeSlotConstant.SCHEMA)
// bảng thông tin về giá sân theo giờ
public class TimeSlot {
    public static class TimeSlotConstant {
        public static final String TABLE_NAME = "time_slots";
        public static final String SCHEMA = "badminton";

        public static final String ID = "id";
        public static final String SLOT_NUMBER = "slot_number";
        public static final String NAME = "name";
        public static final String START_TIME = "start_time";
        public static final String END_TIME = "end_time";
        public static final String PRICE_VIP = "price_vip";
        public static final String PRICE_STANDARD = "price_standard";
        public static final String CREATED_AT = "created_at";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TimeSlotConstant.ID)
    Integer id;

    @Column(name = TimeSlotConstant.SLOT_NUMBER, nullable = false)
    Integer slotNumber;

    @Column(name = TimeSlotConstant.NAME, nullable = false)
    String name;

    @Column(name = TimeSlotConstant.START_TIME, nullable = false)
    String startTime;

    @Column(name = TimeSlotConstant.END_TIME, nullable = false)
    String endTime;

    @Column(name = TimeSlotConstant.PRICE_VIP)
    Double priceVip;

    @Column(name = TimeSlotConstant.PRICE_STANDARD)
    Double priceStandard;

    @Column(name = TimeSlotConstant.CREATED_AT)
    Long createdAt;
}

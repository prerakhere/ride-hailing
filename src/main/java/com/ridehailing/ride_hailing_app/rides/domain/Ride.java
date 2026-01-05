package com.ridehailing.ride_hailing_app.rides.domain;

import com.ridehailing.ride_hailing_app.users.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;
import java.math.BigDecimal;

@Entity
@Table(name = "rides")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Ride {

    @Id
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RideStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rider_id", nullable = false)
    private User rider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private User driver;

    @Column(name = "pickup_lat")
    private BigDecimal pickupLat;

    @Column(name = "pickup_lng")
    private BigDecimal pickupLng;

    @Column(name = "dropoff_lat")
    private BigDecimal dropoffLat;

    @Column(name = "dropoff_lng")
    private BigDecimal dropoffLng;

    @Column(name = "distance_km")
    private BigDecimal distanceKm;

    @Column(name = "duration_min")
    private Integer durationMin;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) this.status = RideStatus.REQUESTED;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

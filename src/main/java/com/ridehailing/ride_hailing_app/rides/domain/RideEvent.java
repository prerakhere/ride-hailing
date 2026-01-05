package com.ridehailing.ride_hailing_app.rides.domain;

import com.ridehailing.ride_hailing_app.users.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ride_events")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RideEvent {

    @Id
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User actor;

    @Column(name = "user_role", nullable = false)
    private String userRole; // consider renaming column to actor_role later

    @Column(columnDefinition = "jsonb")
    private String details;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

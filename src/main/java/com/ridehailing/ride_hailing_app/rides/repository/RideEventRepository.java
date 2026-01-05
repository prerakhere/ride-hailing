package com.ridehailing.ride_hailing_app.rides.repository;

import com.ridehailing.ride_hailing_app.rides.domain.RideEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RideEventRepository extends JpaRepository<RideEvent, UUID> {
    List<RideEvent> findByRideIdOrderByCreatedAtAsc(UUID rideId);
}

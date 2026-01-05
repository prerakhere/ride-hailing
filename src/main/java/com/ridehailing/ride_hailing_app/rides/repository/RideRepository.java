package com.ridehailing.ride_hailing_app.rides.repository;

import com.ridehailing.ride_hailing_app.rides.domain.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RideRepository extends JpaRepository<Ride, UUID> {
}
package com.ridehailing.ride_hailing_app.drivers.repository;

import com.ridehailing.ride_hailing_app.drivers.domain.DriverLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DriverLocationRepository extends JpaRepository<DriverLocation, UUID> {
    Optional<DriverLocation> findByDriverId(UUID driverId);
}

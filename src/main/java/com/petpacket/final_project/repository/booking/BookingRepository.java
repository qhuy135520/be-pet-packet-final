package com.petpacket.final_project.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}

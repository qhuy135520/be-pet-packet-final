package com.petpacket.final_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.booking.Booking;
import com.petpacket.final_project.entities.service.Service;
import com.petpacket.final_project.entities.user.User;

import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	List<Booking> findByService(Service service);

	List<Booking> findByStatus(String status);

	Booking findBookingByUserAndService(User user, Service service);

	List<Booking> findByUser(User user);

	List<Booking> findByStartDate(LocalDate startDate);

	@Query("SELECT b FROM Booking b INNER JOIN b.service s WHERE s.user.id = :userId")
	List<Booking> findBookingsByUserId(@Param("userId") Integer userId);
}

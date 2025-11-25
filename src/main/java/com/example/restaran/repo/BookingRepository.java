package com.example.restaran.repo;

import com.example.restaran.model.Booking;
import com.example.restaran.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findBySessionId(String sessionId);

    List<Booking> findByUser(User user);
}

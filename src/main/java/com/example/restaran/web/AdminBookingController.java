package com.example.restaran.web;

import com.example.restaran.dto.*;
import com.example.restaran.model.Booking;
import com.example.restaran.model.BookingItem;
import com.example.restaran.repo.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/bookings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminBookingController {

    private final BookingRepository bookingRepo;

    // список заявок
    @GetMapping
    public List<BookingRowDto> getBookings() {
        return bookingRepo.findAll().stream()
                .map(this::toRowDto)
                .toList();
    }

    // детали по id
    @GetMapping("/{id}")
    public BookingDetailsDto getBooking(@PathVariable Long id) {
        Booking b = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Бронь не найдена"));

        return toDetailsDto(b);
    }

    private BookingRowDto toRowDto(Booking b) {
        return new BookingRowDto(
                b.getId(),
                b.getLastName(),
                b.getFirstName(),
                b.getDate(),
                b.getTimeStart(),
                b.getTimeEnd(),
                b.getGuests(),
                b.getPhone()
        );
    }

    private BookingDetailsDto toDetailsDto(Booking b) {
        return new BookingDetailsDto(
                b.getId(),
                b.getLastName(),
                b.getFirstName(),
                b.getDate(),
                b.getTimeStart(),
                b.getTimeEnd(),
                b.getGuests(),
                b.getPhone(),
                b.getTotal(),
                b.getItems().stream()
                        .map(i -> new BookingItemDto(
                                i.getDish().getId(),
                                i.getDish().getTitle(),
                                i.getDish().getPrice(),
                                i.getQuantity()
                        ))
                        .toList()
        );
    }

    private BookingItemDto toItemDto(BookingItem i) {
        return new BookingItemDto(
                i.getDish().getId(),
                i.getDish().getTitle(),
                i.getQuantity(),
                i.getDish().getPrice()
        );
    }
}

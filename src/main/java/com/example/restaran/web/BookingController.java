package com.example.restaran.web;

import com.example.restaran.dto.BookingCreateDto;
import com.example.restaran.dto.BookingDetailsDto;
import com.example.restaran.dto.BookingItemDto;
import com.example.restaran.model.*;
import com.example.restaran.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingRepository bookingRepo;
    private final CartItemRepository cartRepo;
    private final UserRepository userRepo;

    // Создание брони из корзины
    @PostMapping("/bookings")
    @Transactional
    public BookingDetailsDto createBooking(
            @RequestParam String sessionId,
            @RequestBody BookingCreateDto dto
    ) {
        // 1. Берём товары из корзины
        List<CartItem> cartItems = cartRepo.findBySessionId(sessionId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Корзина пуста");
        }

        // 2. Заполняем Booking основными данными
        Booking booking = new Booking();
        booking.setSessionId(sessionId);
        booking.setLastName(dto.lastName());
        booking.setFirstName(dto.firstName());
        booking.setPhone(dto.phone());

        LocalDate date = LocalDate.parse(dto.date());
        LocalTime timeStart = LocalTime.parse(dto.time());
        int guests = Integer.parseInt(dto.guests());
        int durationHours = Integer.parseInt(dto.duration());

        booking.setDate(date);
        booking.setTimeStart(timeStart);
        booking.setTimeEnd(timeStart.plusHours(durationHours));
        booking.setGuests(guests);

        // 3. Привяжем пользователя, если такой телефон есть в базе
        userRepo.findByUsername(dto.phone())
                .ifPresent(booking::setUser);

        // 4. Переносим позиции из корзины
        int total = 0;
        for (CartItem ci : cartItems) {
            BookingItem bi = new BookingItem();
            bi.setBooking(booking);
            bi.setDish(ci.getDish());
            bi.setQuantity(ci.getQuantity());
            bi.setPrice(ci.getDish().getPrice());

            booking.getItems().add(bi);

            total += ci.getQuantity() * ci.getDish().getPrice();
        }
        booking.setTotal(total);

        // 5. Сохраняем бронь (items сохранятся каскадно)
        Booking saved = bookingRepo.save(booking);

        // 6. Очищаем корзину
        cartRepo.deleteBySessionId(sessionId);

        // 7. Возвращаем dto (если тебе нужно показать подтверждение)
        return toDetailsDto(saved);
    }

    // вспомогательный метод
    private BookingDetailsDto toDetailsDto(Booking b) {
        List<BookingItemDto> items = b.getItems().stream()
                .map(i -> new BookingItemDto(
                        i.getDish().getId(),
                        i.getDish().getTitle(),
                        i.getQuantity(),
                        i.getPrice()
                ))
                .toList();

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
                items
        );
    }
}

package com.springapps.booking.B_service;

import com.springapps.booking.C_repository.RoomRepository;
import com.springapps.booking.D_model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    RoomRepository roomRepository;
    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAvailableRoomsBy(LocalDate checkIn, LocalDate checkOut, Integer numberOfPersons){
        // de vizualizat a Booking java 8
        List<Room> foundRooms = roomRepository.findAllByGuestNumber(numberOfPersons);
        return foundRooms.stream()
                .filter(room -> isAvailable(room, checkIn, checkOut))
                .collect(Collectors.toList());
    }

    public boolean isAvailable(Room room, LocalDate checkIn , LocalDate checkOut){
        return room.getRoomReservationsList().stream()
                .map(roomReservation -> roomReservation.getReservation())
                .noneMatch(reservation ->
                reservation.getCheckIn().isBefore(checkIn) && reservation.getCheckOut().isAfter(checkOut)
                        || reservation.getCheckOut().isAfter(checkIn) && reservation.getCheckIn().isBefore(checkOut)
                        || reservation.getCheckIn().isEqual(checkIn) || reservation.getCheckOut().isEqual(checkOut)
        );
    }
}

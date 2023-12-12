package com.springapps.booking.A_controller;

import com.springapps.booking.B_service.HotelService;
import com.springapps.booking.D_model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    HotelService hotelService;
    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/{name}")
    public ResponseEntity<Hotel>addHotel(@PathVariable String name){
        return ResponseEntity.ok(hotelService.createHotel(name));
    }
}

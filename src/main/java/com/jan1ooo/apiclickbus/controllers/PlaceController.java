package com.jan1ooo.apiclickbus.controllers;

import com.jan1ooo.apiclickbus.domain.dto.PlaceDTO;
import com.jan1ooo.apiclickbus.domain.entities.Place;
import com.jan1ooo.apiclickbus.domain.service.PlaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/place")
public class PlaceController {

    private final PlaceService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PlaceDTO> findAll(){
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceDTO create(@Valid @RequestBody PlaceDTO place){
        return service.create(place);
    }
}

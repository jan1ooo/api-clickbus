package com.jan1ooo.apiclickbus.controllers;

import com.jan1ooo.apiclickbus.domain.dto.PlaceDTO;
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

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public PlaceDTO findByName(@RequestParam String name){
        return service.findByName(name);
    }

    @PutMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public PlaceDTO create(@Valid @RequestParam String name,@Valid @RequestBody PlaceDTO place){
        return service.update(name, place);
    }
}

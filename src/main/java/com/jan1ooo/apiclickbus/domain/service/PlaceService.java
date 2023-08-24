package com.jan1ooo.apiclickbus.domain.service;

import com.jan1ooo.apiclickbus.domain.entities.Place;
import com.jan1ooo.apiclickbus.domain.repositories.PlaceRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository repository;

    public List<Place> findAll(){
        return repository.findAll();
    }

    public Place create(@Valid Place place){
        return repository.save(place);
    }
}

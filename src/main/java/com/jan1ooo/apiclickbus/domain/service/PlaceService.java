package com.jan1ooo.apiclickbus.domain.service;

import com.jan1ooo.apiclickbus.domain.dto.PlaceDTO;
import com.jan1ooo.apiclickbus.domain.dto.PlaceMapper;
import com.jan1ooo.apiclickbus.domain.entities.Place;
import com.jan1ooo.apiclickbus.domain.repositories.PlaceRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository repository;
    private final PlaceMapper mapper;

    public List<PlaceDTO> findAll(){
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public PlaceDTO create(@Valid PlaceDTO place){
        return mapper.toDTO(repository.save(mapper.toEntity(place)));
    }
}

package com.jan1ooo.apiclickbus.domain.service;

import com.jan1ooo.apiclickbus.domain.dto.PlaceDTO;
import com.jan1ooo.apiclickbus.domain.dto.mapper.PlaceMapper;
import com.jan1ooo.apiclickbus.domain.entities.Place;
import com.jan1ooo.apiclickbus.domain.exception.BodyBadRequestException;
import com.jan1ooo.apiclickbus.domain.exception.RecordNotFoundException;
import com.jan1ooo.apiclickbus.domain.repositories.PlaceRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository repository;
    private final PlaceMapper mapper;

    public List<PlaceDTO> findAll(){
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public PlaceDTO create(@Valid PlaceDTO place){
        try {
            return mapper.toDTO(repository.save(mapper.toEntity(place)));
        }
        catch (TransactionSystemException e){
            throw new BodyBadRequestException("Your JSON is missing information");
        }
    }

    public PlaceDTO findBySpecificName(String name){
        return mapper.toDTO(repository.findByName(name).orElseThrow(() -> new RecordNotFoundException(name)));
    }

    public PlaceDTO update(String name, PlaceDTO placeDTO){
        return repository.findByName(name)
                .map(recordFound -> {
                    Place place = mapper.toEntity(placeDTO);
                    recordFound.setName(place.getName());
                    recordFound.setCity(place.getCity());
                    recordFound.setState(place.getState());
                    recordFound.setSlug(place.getSlug());
                    recordFound.setUpdated(LocalDateTime.now());
                    return mapper.toDTO(repository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(name));
    }

    public List<PlaceDTO> findByName(String name){
        return repository.findByNameContainingIgnoreCase(name).stream().map(mapper::toDTO).toList();
    }
}

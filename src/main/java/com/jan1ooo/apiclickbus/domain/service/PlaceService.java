package com.jan1ooo.apiclickbus.domain.service;

import com.jan1ooo.apiclickbus.domain.dto.PlaceDTO;
import com.jan1ooo.apiclickbus.domain.dto.mapper.PlaceMapper;
import com.jan1ooo.apiclickbus.domain.entities.Place;
import com.jan1ooo.apiclickbus.domain.exception.BodyBadRequestException;
import com.jan1ooo.apiclickbus.domain.exception.RecordNotFoundException;
import com.jan1ooo.apiclickbus.domain.repositories.PlaceRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository repository;
    private final PlaceMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(PlaceService.class);

    public List<PlaceDTO> findAll(){
        logger.info("Listing all places in request: GET /api/place");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public PlaceDTO create(@Valid PlaceDTO place){
        try {
            logger.warn("Create new Place with ID: " + place.getId_place() + ", in request: POST /api/place");
            return mapper.toDTO(repository.save(mapper.toEntity(place)));
        }
        catch (TransactionSystemException e){
            logger.error("Invalid JSON in request: POST /api/place");
            throw new BodyBadRequestException("Your JSON is missing information");
        }
    }

    public PlaceDTO findBySpecificName(String name){
        try{
            logger.info("Listing specific place by name: " + name + ", in request: GET /api/place/specific/search");
            return mapper.toDTO(repository.findByName(name).get());
        }
        catch (NoSuchElementException e){
            logger.error("No place with this name: " + name + ", in request: GET /api/place/specific/search");
            throw new RecordNotFoundException(name);
        }
    }

    public PlaceDTO update(String name, PlaceDTO placeDTO){
        try {
            logger.warn("Updating place with name: " + name + ", in request: PUT /api/place/search");
            return repository.findByName(name)
                    .map(recordFound -> {
                        Place place = mapper.toEntity(placeDTO);
                        recordFound.setName(place.getName());
                        recordFound.setCity(place.getCity());
                        recordFound.setState(place.getState());
                        recordFound.setSlug(place.getSlug());
                        recordFound.setUpdated(LocalDateTime.now());
                        return mapper.toDTO(repository.save(recordFound));
                    }).get();
        }
        catch (NoSuchElementException e){
            logger.error("Not found with name: " + name + ", in request: PUT /api/place/search");
            throw new RecordNotFoundException(name);
        }
    }

    public List<PlaceDTO> findByName(String name){
        logger.info("Searching for places with the name: "+ name +", in request: GET /api/place");
        return repository.findByNameContainingIgnoreCase(name).stream().map(mapper::toDTO).toList();
    }
}

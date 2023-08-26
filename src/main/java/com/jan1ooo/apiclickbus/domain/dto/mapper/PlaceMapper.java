package com.jan1ooo.apiclickbus.domain.dto.mapper;

import com.jan1ooo.apiclickbus.domain.dto.PlaceDTO;
import com.jan1ooo.apiclickbus.domain.entities.Place;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper {

    public PlaceDTO toDTO(Place place){
        if(place == null){
            return null;
        }

        return new PlaceDTO(place.getId_place(), place.getName(), place.getSlug(), place.getCity(), place.getState(), place.getCreated(), place.getUpdated());
    }

    public Place toEntity(PlaceDTO placeDTO){
        if(placeDTO == null){
            return null;
        }

        Place place = new Place();
        if(placeDTO.getId_place() != null){
            place.setId_place(placeDTO.getId_place());
        }
        place.setName(placeDTO.getName());
        place.setState(placeDTO.getState());
        place.setSlug(placeDTO.getSlug());
        place.setCity(placeDTO.getCity());
        place.setCreated(placeDTO.getCreated());
        place.setUpdated(placeDTO.getUpdated());

        return place;
    }
}

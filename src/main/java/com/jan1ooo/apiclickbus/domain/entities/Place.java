package com.jan1ooo.apiclickbus.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id_place")
public class Place {

    @Id
    private UUID id_place = UUID.randomUUID();

    @NotBlank
    private String name;
    @NotBlank
    private String slug;
    @NotBlank
    private String city;
    @NotBlank
    private String state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created = LocalDateTime.now();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;

    public Place(String name, String slug, String city, String state) {
    }
}

package com.example.backend.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CategoryInputDto {
    private Long id;
    private String name;
    private Long parent_id;
}

package org.example.forrest.DTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CatDTO {
    String name;
    int weight;
    int age;
}

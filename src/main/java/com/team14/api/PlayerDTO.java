package com.team14.api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    private Integer playerID;
    private String firstName;
    private String lastName;
    private String codeName;
}

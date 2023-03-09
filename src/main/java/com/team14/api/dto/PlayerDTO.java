package com.team14.api.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    @NotEmpty(message = "Code name is a required field")
    private String codeName;
    @NotNull(message = "Id is a required field")
    private Integer id;
}

package com.team14.api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.boot.SpringApplication;


import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "player", schema = "playerData")
public class Player {
    @Id
    private Integer playerID;
    private String firstName;
    private String lastName;
    private String codeName;

}

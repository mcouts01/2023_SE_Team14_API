package com.team14.api.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.boot.SpringApplication;


import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "player", schema = "public")
public class Player implements Persistable<Integer> {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String codeName;

    @Transient
    @Override
    public boolean isNew() {
        return true;
    }
}

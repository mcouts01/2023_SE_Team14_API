package com.team14.api.repository;
import com.team14.api.entity.Player;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    @Query("SELECT * FROM public.player WHERE code_name = :codeName")
    Optional<Player> findByCodeName(@Param("codeName") String codeName);

    @Query("SELECT * FROM public.player WHERE id = :id")
    Optional<Player> findByID(@Param("id") Integer id);
}

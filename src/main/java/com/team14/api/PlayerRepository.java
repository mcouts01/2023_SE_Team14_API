package com.team14.api;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    @Query("SELECT * FROM 2023SEteam14.player WHERE id = :playerid")
    Optional<Player> findByPlayerID(@Param("playerid") Integer playerID);

}

package com.team14.api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public Player savePlayer(PlayerDTO playerRequest) {
        //log.info("Saving User with name " + player.getFirstName());
        if (this.playerRepository.findByPlayerID(playerRequest.getPlayerID()).isEmpty()) {
            Player player = Player.builder()
                    .playerID(playerRequest.getPlayerID())
                    .firstName(playerRequest.getFirstName())
                    .lastName(playerRequest.getLastName())
                    .codeName(playerRequest.getCodeName())
                    .build();
            return playerRepository.save(player);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
    }
}
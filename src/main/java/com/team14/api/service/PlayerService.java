package com.team14.api.service;
import com.team14.api.dto.PlayerDTO;
import com.team14.api.entity.Player;
import com.team14.api.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public Player savePlayer(PlayerDTO playerRequest) {
        log.info("Saving player with code name " + playerRequest.getCodeName());
        if (this.playerRepository.findByCodeName(playerRequest.getCodeName()).isEmpty()) {
            if(this.playerRepository.findByID(playerRequest.getId()).isEmpty()) {
                Player player = Player.builder()
                        .firstName("") // Professor mentioned that we don't need to worry about first and last name
                        .lastName("")
                        .id(playerRequest.getId())
                        .codeName(playerRequest.getCodeName())
                        .build();
                return playerRepository.save(player);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists with id " + playerRequest.getId());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists with code name " + playerRequest.getCodeName());
        }
    }

    public Player getPlayerByCodeName(String codeName) {
        log.info("Finding player with code name " + codeName);
        var player = this.playerRepository.findByCodeName(codeName);
        if(player.isPresent())
            return player.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with code name " + codeName);
    }

    public Player getPlayerByID(Integer id) {
        log.info("Finding player with id " + id);
        var player = this.playerRepository.findByID(id);
        if(player.isPresent())
            return player.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id);
    }
}
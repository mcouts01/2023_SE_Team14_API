package com.team14.api;
import com.team14.api.PlayerDTO;
import com.team14.api.Player;
import com.team14.api.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    PlayerService playerService;

    // Defining an endpoint guide

    @PostMapping("/")
    public ResponseEntity<String> savePlayer(PlayerDTO player) {
        try {
            playerService.savePlayer(player);
            return new ResponseEntity<>("Sucessfully added player", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not add player", HttpStatus.BAD_REQUEST);
        }
    }
}
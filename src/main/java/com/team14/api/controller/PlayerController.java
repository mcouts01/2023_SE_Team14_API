package com.team14.api.controller;
import com.team14.api.dto.PlayerDTO;
import com.team14.api.entity.Player;
import com.team14.api.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;
import java.util.Arrays;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<Player> savePlayer(@Valid @RequestBody PlayerDTO playerRequest) {
        try {
            var player = playerService.savePlayer(playerRequest);
            return ResponseEntity.ok(player);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @GetMapping("/codeName={codeName}")
    public ResponseEntity<Player> findPlayerByCodeName(@PathVariable String codeName) {
        try {
            var player = playerService.getPlayerByCodeName(codeName);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @GetMapping("/id={id}")
    public ResponseEntity<Player> findPlayerByID(@PathVariable Integer id) {
        try {
            var player = playerService.getPlayerByID(id);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
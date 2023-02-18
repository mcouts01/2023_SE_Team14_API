package com.team14.api.controller;
import com.team14.api.dto.PlayerDTO;
import com.team14.api.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<String> savePlayer(@RequestBody PlayerDTO player) {
        try {
            playerService.savePlayer(player);
            return new ResponseEntity<>("Sucessfully added player", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>("Could not add player", HttpStatus.BAD_REQUEST);
        }
    }
}
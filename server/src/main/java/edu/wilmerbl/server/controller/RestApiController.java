package edu.wilmerbl.server.controller;

import edu.wilmerbl.logic.Action;
import edu.wilmerbl.logic.State;
import edu.wilmerbl.server.service.BrickGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestApiController {

    @Autowired
    BrickGameService service;

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/state")
    public State getState() {
        return service.getState();
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/state/isRunning")
    public boolean getStatus() {
        return !service.isRunning();
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/actions")
    public void postActions(@RequestBody Action action) {
        service.userInput(action);
    }
}
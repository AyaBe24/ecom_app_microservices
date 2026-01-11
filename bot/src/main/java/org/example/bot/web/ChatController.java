package org.example.bot.web;

import org.example.bot.agents.AiAgent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@CrossOrigin("*")
public class ChatController {
    private AiAgent agent;

    public ChatController(AiAgent agent) {
        this.agent = agent;
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(String query) {
        return agent.askAgent(query);
    }
}
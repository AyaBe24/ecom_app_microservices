package org.example.bot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AiAgent {
    private final ChatClient chatClient;

    public AiAgent(ChatClient.Builder builder, ChatMemory memory, List<FunctionCallback> toolCallbacks) {
        this.chatClient = builder
                .defaultSystem("""
                        Vous êtes un assistant qui se charge de répondre aux questions de l'utilisateur
                        en fonction du contexte fourni.
                        Utilisez les outils mis à votre disposition pour répondre aux questions.
                        Si aucun contexte n'est fourni, répond avec JE ME SAIS PAS.
                        """)
                .defaultAdvisors(new MessageChatMemoryAdvisor(memory))
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultFunctions(toolCallbacks.toArray(new FunctionCallback[0]))
                .build();

    }

    public String askAgent(String query) {
        return chatClient.prompt()
                .user(query)
                .call().content();
    }
}
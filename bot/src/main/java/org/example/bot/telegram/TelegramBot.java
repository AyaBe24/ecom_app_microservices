package org.example.bot.telegram;

import org.example.bot.agents.AiAgent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Component
@ConditionalOnProperty(name = "telegram.enabled", havingValue = "true", matchIfMissing = true)
public class TelegramBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    private final AiAgent aiAgent;
    private final TelegramClient telegramClient;
    private final String telegramBotToken;

    public TelegramBot(AiAgent aiAgent, @Value("${telegram.api.key}") String telegramBotToken) {
        this.aiAgent = aiAgent;
        this.telegramBotToken = telegramBotToken;
        this.telegramClient = new OkHttpTelegramClient(telegramBotToken);
    }

    @Override
    public String getBotToken() {
        return telegramBotToken;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            try {
                sendTypingAction(chatId);
                String answer = aiAgent.askAgent(messageText);
                sendTextMessage(chatId, answer);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendTextMessage(long chatId, String text) throws TelegramApiException {
        SendMessage message = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(text)
                .build();
        telegramClient.execute(message);
    }

    private void sendTypingAction(long chatId) throws TelegramApiException {
        SendChatAction sendChatAction = SendChatAction.builder()
                .chatId(String.valueOf(chatId))
                .action(ActionType.TYPING.toString())
                .build();
        telegramClient.execute(sendChatAction);
    }
}
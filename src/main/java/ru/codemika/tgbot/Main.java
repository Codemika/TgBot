package ru.codemika.tgbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

/**
 * Самый главный класс.
 */
public class Main {
    /**
     * Точка входа в программу.
     */
    public static void main(String[] args) {
        // Инициализация контекста API для бота.
        ApiContextInitializer.init();

        // Соаздаём экземпляр API для бота.
        TelegramBotsApi botsApi = new TelegramBotsApi();

        BotSettings settings;
        // Получаем настройки бота.
        try {
            settings = new BotSettings();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Регистрируем бота в созданном API.
        try {
            botsApi.registerBot(
                    new CodemikaDemoBot(
                            settings.getBotUsername(),
                            settings.getBotToken(),
                            settings.getPromoCode()
                    )
            );
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

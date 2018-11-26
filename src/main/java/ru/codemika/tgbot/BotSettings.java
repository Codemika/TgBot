package ru.codemika.tgbot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс для получения настроек бота из файла.
 */
public class BotSettings {
    private final static String fileName = "settings.properties";
    private String botUsername;
    private String botToken;
    private String promoCode;

    /**
     * Конструктор класса.
     * @throws IOException
     */
    BotSettings() throws IOException {
        InputStream inputStream = null;
        try {
            Properties props = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream != null) {
                props.load(inputStream);
            }
            else {
                throw new FileNotFoundException(String.format("Не удалось загрузить файл %s", fileName));
            }

            botUsername = props.getProperty("botUsername");
            botToken = props.getProperty("botToken");
            promoCode = props.getProperty("promoCode");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * Получение имени бота.
     * @return имя бота.
     */
    public String getBotUsername() {
        return botUsername;
    }

    /**
     * Получение токена бота.
     * @return токен бота.
     */
    public String getBotToken() {
        return botToken;
    }

    /**
     * Получение промокода.
     * @return промокод.
     */
    public String getPromoCode() {
        return promoCode;
    }
}

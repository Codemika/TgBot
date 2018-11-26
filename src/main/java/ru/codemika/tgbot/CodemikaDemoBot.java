package ru.codemika.tgbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Демо бот Кодемики.
 */
public class CodemikaDemoBot extends TelegramLongPollingBot {

    private final String startMessage = "Используйте /start, чтобы начать работу с ботом.";
    private final String welcomeMessage = "Привет, %s!\nРад видеть тебя!\n";
    private final String helpMessage =  "Используй команду /help, чтобы увидеть это сообщение" +
            " или /quest, чтобы получить загадку!";
    private final String questMessage = "Вот моя загадка:\n" +
            "Летели два крокодила. Один - зелёный, другой - на север. Сколько лет старому ёжику?\n" +
            "Для ответа используй команду /answer";
    private final String correctAnswer = "Молодец!\nВозьми с полки промокод: ";
    private final String answer = "42";
    private final String wrongAnswer = "Неправильно!\nПодумай и попытайся ещё!";
    private final String ByeBye = "Пока!\nНадеюсь, было весело!";

    private final String username;
    private final String token;
    private final String promo;

    /**
     * Конструктор класса.
     * @param username имя бота.
     * @param token токен.
     * @param promo промокод.
     */
    CodemikaDemoBot(String username, String token, String promo) {
        this.username = username;
        this.token = token;
        this.promo = promo;
    }

    /**
     * Простой метод echo.
     * @param update ифнормация о полученном событии (сообщении).
     */
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message income = update.getMessage();

            SendMessage message = new SendMessage()
                    .setChatId(income.getChatId());

            if (income.isCommand()) {
                switch (income.getText()) {
                    case "/start":
                        message.setText(
                                String.format(
                                        welcomeMessage,
                                        update.getMessage().getFrom().getFirstName()
                                )
                                + helpMessage
                        );
                        break;
                    case "/stop":
                        message.setText(ByeBye);
                        break;
                    case "/help":
                        message.setText(helpMessage);
                        break;
                    case "/quest":
                        message.setText(questMessage);
                        break;
                    default:
                        if (income.getText().startsWith("/answer")) {
                            String[] words = income.getText().split(" ");
                            if (words.length == 2 && words[1].equals(answer)) {
                                message.setText(correctAnswer + promo);
                            }
                            else {
                                message.setText(wrongAnswer);
                            }
                        }
                        else {
                            message.setText(
                                    String.format(
                                            welcomeMessage,
                                            update.getMessage().getFrom().getFirstName()
                                    )
                            );
                        }
                        break;
                }
            }
            else {
                message.setText(startMessage);
            }

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Получение имени бота.
     * @return имя бота.
     */
    public String getBotUsername() {
        return username;
    }

    /**
     * Получение токена бота.
     * @return токен бота.
     */
    public String getBotToken() {
        return token;
    }
}

package sendMessage;

import buttonsService.ButtonServices;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Collections;

import static constants.ConstantsForAnswersMainMenuBot.*;
import static constants.ConstantsForAnswersMainMenuBot.SEND_PHONE_NUMBER;
import static java.util.Arrays.asList;

@Component
public class SendMessageMain {
    private static ButtonServices buttonService = new ButtonServices();
    private static SendMessage sendMessage = new SendMessage();


    public SendMessage sendHello(Update update) {
        sendMessage = createHello(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonsInOneRow(asList(CREATE_REQUEST)));
        service.setOneTimeKeyboard(true);
        service.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(service);
        return sendMessage;
    }

    public SendMessage sendCreateRequest(Update update) {
        sendMessage = createRequest(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonsInTwoRow(Collections.singletonList(DOOR_BETWEEN_ROOMS), Collections.singletonList(DOOR_MAIN_IN_HOUSE)));
        service.setOneTimeKeyboard(true);
        sendMessage.setReplyMarkup(service);
        service.setResizeKeyboard(true);
        return sendMessage;
    }

    public SendMessage sendEnoughAnswers(Update update) {
        sendMessage = createEnoughAnswers(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonsInTwoRow(asList(YES_CONFIRM_REQUEST_FOR_DOOR), asList(NO_MODIFY_REQUEST_FOR_DOOR)));
        service.setOneTimeKeyboard(true);
        service.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(service);
        return sendMessage;
    }

    public SendMessage sendConfirmRequest(Update update) {
        sendMessage = createConfirmRequest(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonsInTwoRow(asList(YES_CONFIRM_REQUEST_FOR_DOOR), asList(NO_MODIFY_REQUEST_FOR_DOOR)));
        service.setOneTimeKeyboard(true);
        service.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(service);
        return sendMessage;
    }

    public SendMessage sendPhoneNumber(Update update) {
        sendMessage = createPhoneNumber(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonShareNumber(asList(SEND_PHONE_NUMBER)));
        service.setOneTimeKeyboard(true);
        service.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(service);
        return sendMessage;
    }

    public SendMessage sendChangeRequest(Update update) {
        sendMessage = createChangeRequest(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonsInTwoRow(asList(SEND_REQUEST_TO_MASTER),
                asList(CREATE_NEW_REQUEST)));
        service.setOneTimeKeyboard(true);
        service.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(service);
        return sendMessage;
    }

    public SendMessage sendRequestToMaster(Update update) {
        sendMessage = createRequestSendToMaster(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonsInOneRow(asList(CREATE_REQUEST)));
        service.setOneTimeKeyboard(true);
        service.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(service);
        return sendMessage;
    }


    // Methods create text

    private SendMessage createHello(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text("<b>Привіт! </b>" + "\n" + "Я тут для того, щоб допомогти Вам в створенні заявки на встановлення дверей!" + "\n" + "\n"
                        + "Ви можете натиснути клавішу <b>Створити заявку</b> та відправити Вашу заявку спеціалісту" + "\n" + "\n" +
                        "<b>Почнемо?</b>")
                .parseMode("HTML")
                .build();

    }

    private SendMessage createRequestSendToMaster(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text("<b>Дякуємо! </b>" + "\n" + "Вашу заявку зафіксовано! Але, повідомляю, що в майстра можуть бути додаткові питання!" + "\n" + "\n"
                        + "<b>Чекайте дзвінка від майстра!</b>")
                .parseMode("HTML")
                .build();
    }

    private SendMessage createRequest(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .parseMode("HTML")
                .text("<b> Давайте, я Вам допоможу в створенні заявки! </b>" + "\n" + "\n"
                        + "<b>Будь-ласка, оберіть вид дверей, які потрібно встановити</b> ")
                .build();
    }

    private SendMessage createPhoneNumber(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .parseMode("HTML")
                .text("<b>Будь-ласка, натисніть клавішу " + SEND_PHONE_NUMBER + " для зв'язку з Вами</b> " + "\n" + "\n"
                        + "Ми відправляємо Вашу заявку спеціалісту і він з Вами зв'яжеться для обговорення деталей та точної суми встановлення")
                .build();
    }

    private SendMessage createEnoughAnswers(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .parseMode("HTML")
                .text("<b> Дякуємо Вам за відповіді!</b>" + "\n" + "Поки що цих відповідей достатньо")
                .build();
    }

    private SendMessage createConfirmRequest(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .parseMode("HTML")
                .text("<b>Все вірно?</b>" + "\n" + "\n")
                .build();
    }

    private SendMessage createChangeRequest(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .parseMode("HTML")
                .text("<b>Якщо Ви щось не правильно вказали в заявці - Вам потрібно буде пройти опитування знову</b>" + "\n" + "\n"
                        + "Створюємо нову заявку, чи відправляємо цю заявку?" + "\n" + "\n"
                        + "P.S.: Майстер всеодно буде зв'язуватись з Вами для уточнення інформації")
                .build();
    }

}

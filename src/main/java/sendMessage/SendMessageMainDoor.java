package sendMessage;

import buttonsService.ButtonServices;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import static constants.ConstantsForAnswersMainMenuBot.*;
import static constants.ConstantsForAnswersMainMenuBot.NO_HAVE_OLD_MAIN_DOOR;
import static java.util.Arrays.asList;

@Component
public class SendMessageMainDoor {
    private static ButtonServices buttonService = new ButtonServices();
    private static SendMessage sendMessage = new SendMessage();

    public SendMessage sendNeedToBuyDoor(Update update) {
        sendMessage = createNeedToBuyDoor(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonsInTwoRow(asList(YES_ALREADY_BOUGHT_2),
                asList(NO_NEED_ADVICE_2)));
        service.setOneTimeKeyboard(true);
        service.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(service);
        return sendMessage;
    }

    public SendMessage sendNeedLiftDoor(Update update) {
        sendMessage = createNeedLiftDoor(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonsInTwoRow(asList(NEED_TO_LIFT),
                asList(NOT_NEED_TO_LIFT)));
        service.setOneTimeKeyboard(true);
        service.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(service);
        return sendMessage;
    }

    public SendMessage sendRemoveOldDoors(Update update) {
        sendMessage = createRemoveOldDoors(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonsInTwoRow(asList(YES_HAVE_OLD_MAIN_DOOR),
                asList(NO_HAVE_OLD_MAIN_DOOR)));
        service.setOneTimeKeyboard(true);
        service.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(service);
        return sendMessage;
    }


    // Create text for methods
    private SendMessage createNeedToBuyDoor(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text("<b>Дякуємо за відповідь!</b>" + "\n" + "\n"
                        + "Підкажіть, будь-ласка, Ви вже придбали двері?")
                .parseMode("HTML")
                .build();
    }

    private SendMessage createNeedLiftDoor(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text("<b>Дякуємо за відповідь!</b>" + "\n" + "\n"
                        + "Підкажіть, будь-ласка, потрібно підіймати двері на поверх?")
                .parseMode("HTML")
                .build();
    }

    private SendMessage createRemoveOldDoors(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text("<b>Дякуємо за відповідь!</b>" + "\n" + "\n"
                        + "Підкажіть, будь-ласка, потрібно виконувати демонтаж старих дверей?")
                .parseMode("HTML")
                .build();
    }
}

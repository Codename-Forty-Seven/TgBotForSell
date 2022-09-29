package sendMessage;

import buttonsService.ButtonServices;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Collections;

import static constants.ConstantsForAnswersMainMenuBot.*;
import static constants.ConstantsForAnswersMainMenuBot.ANOTHER_HINGE;
import static java.util.Arrays.asList;

@Component
public class SendMessageDoorBetweenRooms {
    private static ButtonServices buttonService = new ButtonServices();
    private static SendMessage sendMessage = new SendMessage();


    public SendMessage sendNeedToBuyDoors(Update update) {
        sendMessage = createNeedToBuyDoors(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonsInTwoRow(Collections.singletonList(YES_ALREADY_BOUGHT),
                Collections.singletonList(NO_NEED_ADVICE)));
        service.setOneTimeKeyboard(true);
        service.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(service);

        return sendMessage;
    }

    public SendMessage sendHowMuchDoors(Update update) {
        sendMessage = createHowMuchDoors(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonsInThreeRow(asList(ONE, TWO, THREE),
                asList(FOUR, FIVE), asList(MORE_THAN_FIVE)));
        service.setOneTimeKeyboard(true);
        service.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(service);

        return sendMessage;
    }

    public SendMessage sendMaterialDoors(Update update) {
        sendMessage = createMaterialDoors(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonsInThreeRow(asList(PAINT, VENEER),
                asList(FILM), asList(ANOTHER_MATERIAL)));
        service.setResizeKeyboard(true);
        service.setOneTimeKeyboard(true);
        sendMessage.setReplyMarkup(service);
        return sendMessage;
    }

    public SendMessage sendWhatHinge(Update update) {
        sendMessage = createWhatHinge(update);
        ReplyKeyboardMarkup service = buttonService.setButtons(buttonService.createButtonsInThreeRow(asList(OVERHEAD),
                asList(DEAD_HINGE, SECRET_HINGE), asList(ANOTHER_HINGE)));
        service.setOneTimeKeyboard(true);
        service.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(service);
        return sendMessage;
    }



    // Methods create text
    private SendMessage createNeedToBuyDoors(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text("<b>Дякуємо за відповідь!</b>" + "\n" + "\n"
                        + "Підкажіть, будь-ласка, Ви вже придбали двері?")
                .parseMode("HTML")
                .build();

    }

    private SendMessage createHowMuchDoors(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text("<b>Дякуємо за відповідь!</b>" + "\n" + "\n"
                        + "Підкажіть, будь-ласка, скільки штук потрібно встановити?")
                .parseMode("HTML")
                .build();

    }

    private SendMessage createMaterialDoors(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .parseMode("HTML")
                .text("<b>Дякуємо за відповідь!</b>" + "\n" + "\n"
                        + "Підкажіть, будь-ласка, який матеріал виготовлення Ваших дверей?")
                .build();
    }

    private SendMessage createWhatHinge(Update update) {
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .parseMode("HTML")
                .text("<b> Дякуємо за відповідь! </b>" + "\n" + "\n"
                        + "І останнє питання: " + "\n"
                        + "Які у Вас завіси?")
                .build();
    }
}

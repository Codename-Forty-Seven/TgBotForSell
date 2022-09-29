package answersFromClient;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static constants.ConstantsForAnswersMainMenuBot.DOOR_MAIN_IN_HOUSE;
import static constants.ConstantsWithKeysForMap.*;

@Component
public class AnswersFromClient {
    private String nameClient = "";
    private String phoneNumberClient = "";
    private String whatKindDoor = "";
    private String needToBuyDoor = "";
    private String howMuch = "";
    private String material = "";
    private String hinge = "";
    private String upToFloor = "";
    private String removeOld = "";

    private static SendMessage sendMessage;
    private static Map<String, String> answersFromCustomer = new HashMap<String, String>();

    public void whatKindDoors(Update update) {
        answersFromCustomer.clear();
        update.getMessage().getChatId();
        answersFromCustomer.put(WHAT_KIND_DOOR, update.getMessage().getText());
    }

    public void needToBuyDoor(Update update) {
        update.getMessage().getChatId();
        answersFromCustomer.put(NEED_TO_BUY, update.getMessage().getText());
    }

    public void howMuchDoors(Update update) {
        update.getMessage().getChatId();
        answersFromCustomer.put(HOW_MUCH, update.getMessage().getText());
    }

    public void whatMaterial(Update update) {
        update.getMessage().getChatId();
        answersFromCustomer.put(MATERIAL, update.getMessage().getText());
    }

    public void whatHinge(Update update) {
        update.getMessage().getChatId();
        answersFromCustomer.put(HINGE, update.getMessage().getText());
    }

    public void getUpToTheFloor(Update update) {
        update.getMessage().getChatId();
        answersFromCustomer.put(GET_UP_TO_FLOOR, update.getMessage().getText());
    }

    public void removeOldMainDoors(Update update) {
        update.getMessage().getChatId();
        answersFromCustomer.put(REMOVE_OLD_DOOR, update.getMessage().getText());
    }

    public SendMessage showRequest(Update update) {
        whatKindDoor = answersFromCustomer.get(WHAT_KIND_DOOR);
        needToBuyDoor = answersFromCustomer.get(NEED_TO_BUY);
        howMuch = answersFromCustomer.get(HOW_MUCH);
        material = answersFromCustomer.get(MATERIAL);
        hinge = answersFromCustomer.get(HINGE);
        upToFloor = answersFromCustomer.get(GET_UP_TO_FLOOR);
        removeOld = answersFromCustomer.get(REMOVE_OLD_DOOR);
        if (whatKindDoor.equals(DOOR_MAIN_IN_HOUSE)) {
            sendMessage = SendMessage.builder()
                    .chatId(String.valueOf(update.getMessage().getChatId()))
                    .text("<b>Давайте підіб'ємо підсумок по Вашому замовленню...</b>" + "\n" + "\n"
                            + WHAT_KIND_DOOR + " <b>" + whatKindDoor + "</b>" + "\n"
                            + NEED_TO_BUY + " <b>" + needToBuyDoor + "</b>" + "\n"
                            + GET_UP_TO_FLOOR + " <b>" + upToFloor + "</b>" + "\n"
                            + REMOVE_OLD_DOOR + " <b>" + removeOld + "</b>" + "\n")
                    .parseMode("HTML")
                    .build();

        } else {
            sendMessage = SendMessage.builder()
                    .chatId(String.valueOf(update.getMessage().getChatId()))
                    .text("<b>Давайте підіб'ємо підсумок по Вашому замовленню...</b>" + "\n" + "\n"
                            + WHAT_KIND_DOOR + " <b>" + whatKindDoor + "</b>" + "\n"
                            + NEED_TO_BUY + " <b>" + needToBuyDoor + "</b>" + "\n"
                            + HOW_MUCH + " <b>" + howMuch + "</b>" + "\n"
                            + MATERIAL + " <b>" + material + "</b>" + "\n"
                            + HINGE + " <b>" + hinge + "</b>" + "\n")
                    .parseMode("HTML")
                    .build();
        }

        return sendMessage;
    }


    public void writePhoneAndNameClient(String nameClient, String phoneNumberClient) throws SQLException, ClassNotFoundException {
        answersFromCustomer.put(NAME_CLIENT, nameClient);
        answersFromCustomer.put(PHONE_NUMBER_CLIENT, phoneNumberClient);
    }

    public SendMessage sendRequestToMaster(Update update) throws SQLException, ClassNotFoundException {
        nameClient = answersFromCustomer.get(NAME_CLIENT);
        phoneNumberClient = answersFromCustomer.get(PHONE_NUMBER_CLIENT);
        whatKindDoor = answersFromCustomer.get(WHAT_KIND_DOOR);
        needToBuyDoor = answersFromCustomer.get(NEED_TO_BUY);
        howMuch = answersFromCustomer.get(HOW_MUCH);
        material = answersFromCustomer.get(MATERIAL);
        hinge = answersFromCustomer.get(HINGE);
        upToFloor = answersFromCustomer.get(GET_UP_TO_FLOOR);
        removeOld = answersFromCustomer.get(REMOVE_OLD_DOOR);
        if (whatKindDoor.equals(DOOR_MAIN_IN_HOUSE)) {
            sendMessage = SendMessage.builder()
                    .chatId(String.valueOf(update.getMessage().getChatId()))
                    .text("Після того, як клієнт оформить заявку - Вам, особисто, буде відправлено повідомлення в ось такому форматі" + "\n" + "\n"
                            + "<b>Привіт! Я отримав нову заявку!" + "\n"
                            + "Переглянь її, будь-ласка...</b>" + "\n" + "\n"
                            + NAME_CLIENT + " <b>" + nameClient + "</b>" + "\n"
                            + PHONE_NUMBER_CLIENT + " <b>" + phoneNumberClient + "</b>" + "\n"
                            + WHAT_KIND_DOOR + " <b>" + whatKindDoor + "</b>" + "\n"
                            + NEED_TO_BUY + " <b>" + needToBuyDoor + "</b>" + "\n"
                            + GET_UP_TO_FLOOR + " <b>" + upToFloor + "</b>" + "\n"
                            + REMOVE_OLD_DOOR + " <b>" + removeOld + "</b>" + "\n")
                    .parseMode("HTML")
                    .build();

        } else {
            sendMessage = SendMessage.builder()
                    .chatId(String.valueOf(update.getMessage().getChatId()))
                    .text("Після того, як клієнт оформить заявку - Вам, особисто, буде відправлено повідомлення в ось такому форматі" + "\n" + "\n"
                            + "<b>Привіт! Я отримав нову заявку!" + "\n"
                            + "Переглянь її, будь-ласка...</b>" + "\n" + "\n"
                            + NAME_CLIENT + " <b>" + nameClient + "</b>" + "\n"
                            + PHONE_NUMBER_CLIENT + " <b>" + phoneNumberClient + "</b>" + "\n"
                            + WHAT_KIND_DOOR + " <b>" + whatKindDoor + "</b>" + "\n"
                            + NEED_TO_BUY + " <b>" + needToBuyDoor + "</b>" + "\n"
                            + HOW_MUCH + " <b>" + howMuch + "</b>" + "\n"
                            + MATERIAL + " <b>" + material + "</b>" + "\n"
                            + HINGE + " <b>" + hinge + "</b>" + "\n")
                    .parseMode("HTML")
                    .build();
        }
        return sendMessage;
    }
}

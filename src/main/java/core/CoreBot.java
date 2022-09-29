package core;

import answersFromClient.AnswersFromClient;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import sendMessage.SendMessageDoorBetweenRooms;
import sendMessage.SendMessageMain;
import sendMessage.SendMessageMainDoor;

import java.sql.SQLException;

import static constants.ConstantsForAnswersMainMenuBot.*;

@Component
public class CoreBot extends TelegramLongPollingBot {
    private static final String botUserName = "ForCustomerTest_bot";
    private static final String botToken = "5115437638:AAF3vCCyB-B38b7CsqfKum8b4PGm9AB9h1g";


    private static SendMessageMain sendMain = new SendMessageMain();
    private static AnswersFromClient answersFromClient = new AnswersFromClient();
    private static SendMessageDoorBetweenRooms sendMessageDoorBetweenRooms = new SendMessageDoorBetweenRooms();
    private static SendMessageMainDoor sendMessageMainDoor = new SendMessageMainDoor();


    @Override
    public void onUpdateReceived(Update update) {
        String updateFromUser = update.getMessage().getText();
        if (update.hasMessage() && update.getMessage().hasText()) {

            if (updateFromUser.equals(START)) {
                try {
                    execute(sendMain.sendHello(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }

            //
            //Here client begin to create Request
            //

            if (updateFromUser.equals(CREATE_REQUEST)) {
                try {
                    execute(sendMain.sendCreateRequest(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            //
            //Here client choose Doors Between Doors
            //
            if (updateFromUser.equals(DOOR_BETWEEN_ROOMS)) {
                try {
                    answersFromClient.whatKindDoors(update);
                    execute(sendMessageDoorBetweenRooms.sendNeedToBuyDoors(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (updateFromUser.equals(YES_ALREADY_BOUGHT) || updateFromUser.equals(NO_NEED_ADVICE)) {
                try {
                    answersFromClient.needToBuyDoor(update);
                    execute(sendMessageDoorBetweenRooms.sendHowMuchDoors(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (updateFromUser.equals(ONE) || updateFromUser.equals(TWO) ||
                    updateFromUser.equals(THREE) || updateFromUser.equals(FOUR) ||
                    updateFromUser.equals(FIVE) || updateFromUser.equals(MORE_THAN_FIVE)) {
                try {
                    answersFromClient.howMuchDoors(update);
                    execute(sendMessageDoorBetweenRooms.sendMaterialDoors(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (updateFromUser.equals(PAINT) || updateFromUser.equals(VENEER) ||
                    updateFromUser.equals(FILM) || updateFromUser.equals(ANOTHER_MATERIAL)) {
                try {
                    answersFromClient.whatMaterial(update);
                    execute(sendMessageDoorBetweenRooms.sendWhatHinge(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (updateFromUser.equals(OVERHEAD) || updateFromUser.equals(DEAD_HINGE) ||
                    updateFromUser.equals(SECRET_HINGE) || updateFromUser.equals(ANOTHER_HINGE)) {
                try {
                    answersFromClient.whatHinge(update);
                    execute(sendMain.sendEnoughAnswers(update));
                    execute(answersFromClient.showRequest(update));
                    execute(sendMain.sendConfirmRequest(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


            //
            // Here client choose Main Door in House
            //
            if (updateFromUser.equals(DOOR_MAIN_IN_HOUSE)) {
                try {
                    answersFromClient.whatKindDoors(update);
                    execute(sendMessageMainDoor.sendNeedToBuyDoor(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (updateFromUser.equals(YES_ALREADY_BOUGHT_2) || updateFromUser.equals(NO_NEED_ADVICE_2)) {
                try {
                    answersFromClient.needToBuyDoor(update);
                    execute(sendMessageMainDoor.sendNeedLiftDoor(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (updateFromUser.equals(NEED_TO_LIFT) || updateFromUser.equals(NOT_NEED_TO_LIFT)) {
                try {
                    answersFromClient.getUpToTheFloor(update);
                    execute(sendMessageMainDoor.sendRemoveOldDoors(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (updateFromUser.equals(YES_HAVE_OLD_MAIN_DOOR) || updateFromUser.equals(NO_HAVE_OLD_MAIN_DOOR)) {
                answersFromClient.removeOldMainDoors(update);
                try {
                    execute(sendMain.sendEnoughAnswers(update));
                    execute(answersFromClient.showRequest(update));
                    execute(sendMain.sendConfirmRequest(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


            //
            //Here client confirm request
            //
            if (updateFromUser.equals(YES_CONFIRM_REQUEST_FOR_DOOR)) {
                try {
                    execute(sendMain.sendPhoneNumber(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


            //
            // Here client want change request
            //
            if (updateFromUser.equals(NO_MODIFY_REQUEST_FOR_DOOR)) {
                try {
                    execute(sendMain.sendChangeRequest(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (updateFromUser.equals(CREATE_NEW_REQUEST)) {
                try {
                    execute(sendMain.sendCreateRequest(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (updateFromUser.equals(SEND_REQUEST_TO_MASTER)) {
                try {
                    execute(sendMain.sendPhoneNumber(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


        } else if (update.hasMessage() && update.getMessage().hasContact()) {
            String nameClient = update.getMessage().getContact().getFirstName();
            String phoneNumberClient = update.getMessage().getContact().getPhoneNumber();
            try {
                answersFromClient.writePhoneAndNameClient(nameClient, phoneNumberClient);
                execute(sendMain.sendRequestToMaster(update));
                execute(answersFromClient.sendRequestToMaster(update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    }

    public String getBotUsername() {
        return botUserName;
    }

    public String getBotToken() {
        return botToken;
    }

}

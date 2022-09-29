package buttonsService;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ButtonServices {
    public ReplyKeyboardMarkup setButtons(List<KeyboardRow> keyboardRowList) {
        ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
                .selective(true)
                .resizeKeyboard(true)
                .keyboard(keyboardRowList)
                .build();
        return replyKeyboardMarkup;
    }



    public List<KeyboardRow> createButtonsInOneRow(List<String> buttonsName) {
        List keyboardRows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();
        for (String buttonName : buttonsName) {
            row.add(new KeyboardButton(buttonName));
        }
        keyboardRows.add(row);
        return keyboardRows;
    }

    public List<KeyboardRow> createButtonsInTwoRow(List<String> btnOne, List<String> btnTwo) {
        ArrayList keyboardRowsFirst = new ArrayList<KeyboardRow>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        for (String btnName1 : btnOne) {
            row1.add(new KeyboardButton(btnName1));
        }
        for (String btnName2 : btnTwo) {
            row2.add(new KeyboardButton(btnName2));
        }
        keyboardRowsFirst.add(row1);
        keyboardRowsFirst.add(row2);
        return keyboardRowsFirst;
    }

    public List<KeyboardRow> createButtonsInThreeRow(List<String> btnOne, List<String> btnTwo, List<String> btnThree) {
        ArrayList keyboardRowsFirst = new ArrayList<KeyboardRow>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        for (String btnName1 : btnOne) {
            row1.add(new KeyboardButton(btnName1));
        }
        for (String btnName2 : btnTwo) {
            row2.add(new KeyboardButton(btnName2));
        }
        for (String btnName3 : btnThree) {
            row3.add(new KeyboardButton(btnName3));
        }
        keyboardRowsFirst.add(row1);
        keyboardRowsFirst.add(row2);
        keyboardRowsFirst.add(row3);
        return keyboardRowsFirst;
    }

    public List<KeyboardRow> createButtonShareNumber(List<String> buttonsName) {
        KeyboardButton keyboardButton = new KeyboardButton();
        List keyboardRows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();
        for (String buttonName : buttonsName) {
            keyboardButton.setText(buttonName);
            keyboardButton.setRequestContact(true);
            row.add(keyboardButton);
        }
        keyboardRows.add(row);
        return keyboardRows;
    }
}

package sendMessage;

import org.springframework.stereotype.Component;

@Component
public class SendMessageAdmin {
    private static final String NAME_ADMIN = "Serhii_Senkin";
    private static final String ADMIN_CHAT_ID = "663181247";


    public static String getNameAdmin() {
        return NAME_ADMIN;
    }

    public static String getAdminChatId() {
        return ADMIN_CHAT_ID;
    }
}

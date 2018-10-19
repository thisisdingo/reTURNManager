package turnmanager.michil.ru.Models;

import java.util.ArrayList;

public class RequestModel {

    private String secretKey;
    private String command;
    private ArrayList<TURNUser> users;

    public RequestModel(String secretKey, String command, ArrayList<TURNUser> users) {
        this.secretKey = secretKey;
        this.command = command;
        this.users = users;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getCommand() {
        return command;
    }

    public ArrayList<TURNUser> getUsers() {
        return users;
    }
}

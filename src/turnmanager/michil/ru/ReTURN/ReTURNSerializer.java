package turnmanager.michil.ru.ReTURN;

import turnmanager.michil.ru.Models.TURNUser;

import java.util.ArrayList;
import java.util.List;

public class ReTURNSerializer {

    ReTURNSerializer() {}


    String serializeToString(List<TURNUser> users){
        StringBuilder newUsers = new StringBuilder();

        for (TURNUser user: users) {
            String stringUser = user.getUsername() + ":"
                    + user.getPassword() + ":"
                    + user.getRealm() + ":"
                    + user.getMode() + "\n";


            newUsers.append(stringUser);
        }

        return newUsers.toString();
    }

    ArrayList<TURNUser> serializeToArrayList(String usersString){
        ArrayList<TURNUser> users = new ArrayList<TURNUser>();

        String lines[] = usersString.split("\\r?\\n");

        for (String line: lines) {

            String trimmedLine = line.trim();

            if (trimmedLine.startsWith("#") || trimmedLine.isEmpty() || !trimmedLine.contains(":")){
                continue;
            }

            System.out.println(trimmedLine);

            String sectors[] = trimmedLine.split(":");


            TURNUser user = new TURNUser(sectors[0], sectors[1], sectors[2], sectors[3]);
            users.add(user);
        }


        return users;
    }
}

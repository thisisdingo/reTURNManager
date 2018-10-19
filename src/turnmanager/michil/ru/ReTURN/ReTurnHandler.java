package turnmanager.michil.ru.ReTURN;

import turnmanager.michil.ru.Models.TURNUser;
import turnmanager.michil.ru.FileWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReTurnHandler {

    private ReTURNSerializer serializer;

    public ReTurnHandler() {
        serializer = new ReTURNSerializer();
    }


    public ArrayList<TURNUser> getUsers() throws Exception {
        return serializer.serializeToArrayList(FileWorker.getInstance().getListReTurnUsersList());
    }

    public void addUsers(ArrayList<TURNUser> newUsers) throws Exception {

        ArrayList<TURNUser> users = getUsers();
        users.addAll(newUsers);


        List<TURNUser> uniqueUsers = users.stream()
                .distinct()
                .collect(Collectors.toList());


        FileWorker.getInstance().writeToUsersDisk(serializer.serializeToString(uniqueUsers));
    }

    public void removeUser(ArrayList<TURNUser> removedUsers) throws Exception {
        ArrayList<TURNUser> newUsers = new ArrayList<TURNUser>();

        for (TURNUser user: getUsers()) {
            for (TURNUser removedUser : removedUsers){
                if (!removedUser.equals(user) && !newUsers.contains(user)){
                    newUsers.add(user);
                }
            }
        }

        FileWorker.getInstance().writeToUsersDisk(serializer.serializeToString(newUsers));
    }

    public void changeUser(ArrayList<TURNUser> changeUsers) throws Exception {

        ArrayList<TURNUser> availUsers = getUsers();

        for (int i = 0; i < availUsers.size(); i++) {
            TURNUser user = availUsers.get(i);

            for (int j = 0; j < changeUsers.size(); j++) {
                TURNUser changeUser = changeUsers.get(j);

                if (user.equals(changeUser)){
                    availUsers.get(i).setMode(changeUser.getMode());
                }

            }
        }

        FileWorker.getInstance().writeToUsersDisk(serializer.serializeToString(availUsers));
    }



}

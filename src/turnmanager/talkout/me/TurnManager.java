package turnmanager.talkout.me;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpServer;
import turnmanager.talkout.me.HTTPServer.HTTPServer;
import turnmanager.talkout.me.HTTPServer.HTTPServerInterface;
import turnmanager.talkout.me.Models.RequestModel;
import turnmanager.talkout.me.Models.ResponseModel;
import turnmanager.talkout.me.Models.TURNUser;
import turnmanager.talkout.me.ReTURN.ReTurnHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

public class TurnManager implements HTTPServerInterface {

    private ReTurnHandler handler;

    TurnManager() {
        System.out.println("Server started!");

        try {

            HttpServer server = HttpServer.create();

            server.bind(new InetSocketAddress(8765), 0);

            server.createContext("/", new HTTPServer(this));
            server.setExecutor(null);
            server.start();


            handler = new ReTurnHandler();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ResponseModel didReceiveRequest(RequestModel request) {
        Gson gson = new Gson();

        try {

            switch (request.getCommand()){
                case Commands.ADD_USER:
                    handler.addUsers(request.getUsers());
                    break;
                case Commands.CHANGE_USER:
                    handler.changeUser(request.getUsers());
                    break;
                case Commands.REMOVE_USER:
                    handler.removeUser(request.getUsers());
                    break;
                case Commands.GET_USER_LIST:
                    JsonElement usersJson = gson.toJsonTree(handler.getUsers(), new TypeToken<List<TURNUser>>() {}.getType());

                    JsonObject message = new JsonObject();
                    message.add("users", usersJson.getAsJsonArray());

                    return new ResponseModel(true, message);
            }
            return new ResponseModel(true, null);
        } catch (Exception e){
            e.printStackTrace();

            JsonObject message = new JsonObject();
            message.addProperty("error", e.toString());

            return new ResponseModel(false, message);
        }


    }
}

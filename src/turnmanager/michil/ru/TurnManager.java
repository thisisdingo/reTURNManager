package turnmanager.michil.ru;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpServer;
import turnmanager.michil.ru.Models.RequestModel;
import turnmanager.michil.ru.Models.ResponseModel;
import turnmanager.michil.ru.Models.TURNUser;
import turnmanager.michil.ru.HTTPServer.HTTPServer;
import turnmanager.michil.ru.HTTPServer.HTTPServerInterface;
import turnmanager.michil.ru.ReTURN.ReTurnHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import static turnmanager.michil.ru.Constants.DEFAULT_REST_API_PORT;

public class TurnManager implements HTTPServerInterface {

    private ReTurnHandler handler;

    TurnManager() {
        System.out.println("Server started!");

        try {

            HttpServer server = HttpServer.create();

            server.bind(new InetSocketAddress(DEFAULT_REST_API_PORT), 0);

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

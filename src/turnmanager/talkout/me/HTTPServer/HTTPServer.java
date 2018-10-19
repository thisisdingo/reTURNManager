package turnmanager.talkout.me.HTTPServer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.*;
import turnmanager.talkout.me.Exceptions.AuthException;
import turnmanager.talkout.me.Models.RequestModel;
import turnmanager.talkout.me.Models.ResponseModel;

import java.io.*;
import java.util.stream.Collectors;

import static turnmanager.talkout.me.Constants.SECRET_KEY;

public class HTTPServer implements HttpHandler {

    private HTTPServerInterface serverInterface;

    public HTTPServer(HTTPServerInterface serverInterface) {
        this.serverInterface = serverInterface;
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        try {

            String requestString = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))
                    .lines().collect(Collectors.joining("\n"));

            RequestModel request = gson.fromJson(requestString, RequestModel.class);


            if (request.getSecretKey() == null || !request.getSecretKey().equals(SECRET_KEY)){
                throw new AuthException();
            }


            ResponseModel response = this.serverInterface.didReceiveRequest(request);
            byte[] bytes = gson.toJson(response).getBytes();
            exchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();

        } catch (Exception e){
            JsonObject message = new JsonObject();
            message.addProperty("error", e.toString());

            ResponseModel response = new ResponseModel(false, message);
            byte[] bytes = gson.toJson(response).getBytes();
            exchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();

            e.printStackTrace();
        }

    }

}

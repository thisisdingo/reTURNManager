package turnmanager.michil.ru.HTTPServer;

import turnmanager.michil.ru.Models.RequestModel;
import turnmanager.michil.ru.Models.ResponseModel;

public interface HTTPServerInterface {

    ResponseModel didReceiveRequest(RequestModel request);

}

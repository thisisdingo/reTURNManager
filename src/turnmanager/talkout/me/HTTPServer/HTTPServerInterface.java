package turnmanager.talkout.me.HTTPServer;

import turnmanager.talkout.me.Models.RequestModel;
import turnmanager.talkout.me.Models.ResponseModel;

public interface HTTPServerInterface {

    ResponseModel didReceiveRequest(RequestModel request);

}

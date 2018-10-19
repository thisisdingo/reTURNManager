package turnmanager.michil.ru.Models;


public class ResponseModel {

    private boolean success;
    private Object message;

    public ResponseModel(boolean success, Object message) {
        this.success = success;
        this.message = message;
    }
}

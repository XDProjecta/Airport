package core.controllers.utils;

public class Response {

    // data es de tipo objet :OOO holyyy 
    private String message;
    private int status;
    private Object data;

    // en este caso al comienzo data no tiene na, no tiene objeto, null pues 👌👌👌
    public Response(String message, int status) {
        this.message = message;
        this.status = status;
        this.data = null;
    }

    public Response(String message, int status, double data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

}

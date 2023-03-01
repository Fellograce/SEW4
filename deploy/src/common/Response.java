package common;

import java.io.Serializable;

public class Response implements Serializable {
    private String msg;

    public Response() {
    }

    public Response(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Response{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

package common;

import java.io.Serializable;

public class Request implements Serializable {
    private String msg;

    public Request(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Request{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
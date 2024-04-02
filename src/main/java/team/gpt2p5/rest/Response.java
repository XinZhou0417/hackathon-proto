package team.gpt2p5.rest;

import java.util.HashMap;
import java.util.List;

public class Response extends HashMap<String, Object> {

    private static String CODE = "code";
    private static String MSG = "msg";
    private static String DATA = "data";
    private static String ROWS = "rows";

    private Response(int code, String msg, Object data, List<?> rows) {
        super.put(CODE, code);
        super.put(MSG, msg);
        super.put(DATA, data);
        super.put(ROWS, rows);
    }

    public static Response success(String msg, Object data, List<?> rows) {
        return new Response(HttpStatusCode.SUCCESS, msg, data, rows);
    }

    public static Response error(String msg, Object data, List<?> rows) {
        return new Response(HttpStatusCode.ERROR, msg, data, rows);
    }


}

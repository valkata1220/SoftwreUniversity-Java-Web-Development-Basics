package main.java.app.io;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse {

    private Map<String, String> headers;
    private int statusCode;
    private byte[] content;

    public HttpResponseImpl() {
        this.headers = new LinkedHashMap<>();
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        StringBuilder headers = new StringBuilder();
        headers.append(getStatusHeader()).append(System.lineSeparator());
        Map<String, String> responseHeaders = this.getHeaders();
        if(responseHeaders.get("Date") == null){
            headers.append(String.format("Date: %s%s"
                    , LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    ,System.lineSeparator()));
        }
        for (Map.Entry<String, String> header : responseHeaders.entrySet()) {
            headers.append(String.format("%s: %s%s"
                    , header.getKey()
                    , header.getValue()
                    , System.lineSeparator()
            ));
        }

        headers.append(System.lineSeparator());

        byte[] header = headers.toString().getBytes();
        byte[] content = this.getContent();
        byte[] response = new byte[header.length + content.length];

        System.arraycopy(header, 0, response, 0, header.length);
        System.arraycopy(content, 0, response, header.length, content.length);

        return response;
    }

    private String getStatusHeader() {
        switch (this.getStatusCode()) {
            case 200:
                return "HTTP/1.1 200 OK";
            case 400:
                return "HTTP/1.1 400 Bad Request";
            case 401:
                return "HTTP/1.1 401 Unauthorized";
            case 404:
                return "HTTP/1.1 404 Not Found";
            default:
                return "HTTP/1.1 500 Internal Server Error";
        }
    }


    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }


}

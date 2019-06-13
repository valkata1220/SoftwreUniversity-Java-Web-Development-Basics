package main.java.app.io;

import java.util.List;
import java.util.Map;

public interface HttpRequest {
    Map<String, String> getHeaders();

    Map<String, String> getBodyParameters();

    String getMethod();

    void setMethod(String method);

    String getRequestUrl();

    void setRequestUrl(String requestUrl);

    void addHeader(String header,String value);

    void addBodyParameter(String parameter, String value);

    boolean isResource();

    List<HttpCookie> getCookies();

    void addCookie(HttpCookie cookie);

}

package main.java.app.io;

import java.lang.reflect.Array;
import java.util.*;

public class HttpRequestImpl implements HttpRequest {

    private String method;
    private String requestUrl;
    private Map<String, String> headers;
    private Map<String, String> bodyParameters;
    private List<HttpCookie> cookies;

    public HttpRequestImpl(String request) {
        this.headers = new LinkedHashMap<>();
        this.bodyParameters = new LinkedHashMap<>();
        this.cookies = new ArrayList<>();
        this.initialize(request);
    }


    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return Collections.unmodifiableMap(this.bodyParameters);
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter, value);
    }

    @Override
    public boolean isResource() {
        return !this.requestUrl.contains(".");
    }

    @Override
    public List<HttpCookie> getCookies() {
        return Collections.unmodifiableList(this.cookies);
    }

    @Override
    public void addCookie(HttpCookie cookie) {
        this.cookies.add(cookie);
    }

    private void initialize(String request) {
        this.setMethod(request.split("\\s+")[0]);
        this.setRequestUrl(request.split("\\s+")[1]);

        String[] requestParameters = request.split(System.lineSeparator());

        Arrays.stream(requestParameters)
                .skip(1)
                .filter(headerKvp -> headerKvp.contains(": "))
                .forEach(headerKvp -> {
                    String[] header = headerKvp.split(": ");
                    this.addHeader(header[0], header[1]);
                });

        if (requestParameters[requestParameters.length - 2].isEmpty()) {
            Arrays.stream(requestParameters[requestParameters.length - 1].split("&"))
                    .forEach(bodyKvp -> {
                        String[] body = bodyKvp.split("=");
                        this.addBodyParameter(body[0], body[1]);
                    });
        }

        if(this.headers.containsKey("Cookie")){
            String[] cookies = this.headers.get("Cookie").split("; ");
            Arrays.stream(cookies)
                    .map(c -> c.split("="))
                    .forEach(kvP -> this.addCookie(new HttpCookieImpl(kvP[0],kvP[1])));
        }
    }
}

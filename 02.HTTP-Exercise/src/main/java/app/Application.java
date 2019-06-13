package main.java.app;

import main.java.app.io.HttpRequest;
import main.java.app.io.HttpRequestImpl;
import main.java.app.io.HttpResponse;
import main.java.app.io.HttpResponseImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        List<String> validUrls = getValidUrls();

        HttpRequest httpRequest = new HttpRequestImpl(getRequest());

        HttpResponse httpResponse = new HttpResponseImpl();

        if (!validUrls.contains(httpRequest.getRequestUrl())) {
            httpResponse.setStatusCode(404);
            httpResponse.setContent("The requested functionality was not found.".getBytes());
        } else if (!httpRequest.getHeaders().keySet().contains("Authorization")) {
            httpResponse.setStatusCode(401);
            httpResponse.setContent("You are not authorized to access the requested functionality.".getBytes());
        } else if (httpRequest.getMethod().equals("POST") && httpRequest.getBodyParameters().size() == 0) {
            httpResponse.setStatusCode(400);
            httpResponse.setContent("There was an error with the requested functionality due to malformed request.".getBytes());
        } else {
            httpResponse.setStatusCode(200);
            String first = "";
            String second = "";
            String third = "";
            int count = 0;
            for (Map.Entry<String, String> kvP : httpRequest.getBodyParameters().entrySet()) {
                switch (count++) {
                    case 0:
                        first = kvP.getValue();
                        break;
                    case 1:
                        second = kvP.getKey() + " - " + kvP.getValue();
                        break;
                    case 2:
                        third = kvP.getKey() + " - " + kvP.getValue();
                        break;
                }
            }
            httpResponse.setContent(String
                    .format("Greetings %s! You have successfully created %s with %s, %s."
                            , new String(Base64.getDecoder().decode(httpRequest.getHeaders().get("Authorization").split("\\s+")[1]))
                            , first, second, third)
                    .getBytes());
        }

        httpRequest.getHeaders().entrySet().stream()
                .filter(header -> header.getKey().equals("Date")
                        || header.getKey().equals("Host")
                        || header.getKey().equals("Content-Type"))
                .forEach(header -> httpResponse.addHeader(header.getKey(), header.getValue()));



        System.out.println(new String(httpResponse.getBytes(), "UTF8"));

    }

    private static List<String> getValidUrls() throws IOException {
        List<String> validUrls = new ArrayList<>(Arrays.asList(reader.readLine().split("\\s+")));

        return validUrls;
    }


    private static String getRequest() throws IOException {
        StringBuilder request = new StringBuilder();

        String line = null;

        while ((line = reader.readLine()) != null && line.length() > 0) {
            request.append(line).append(System.lineSeparator());
        }

        request.append(System.lineSeparator());
        if ((line = reader.readLine()) != null && line.length() > 0) {
            request.append(line);
        }

        return request.toString();
    }


}

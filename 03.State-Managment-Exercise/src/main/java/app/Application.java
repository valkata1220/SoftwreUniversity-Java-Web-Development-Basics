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

        HttpRequest httpRequest = new HttpRequestImpl(getRequest());

        httpRequest.getCookies().stream()
                .map(c -> c.getKey()+" <-> " + c.getValue())
                .forEach(System.out::println);
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

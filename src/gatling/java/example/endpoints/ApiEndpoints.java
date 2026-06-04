package example.endpoints;

import io.gatling.javaapi.http.HttpRequestActionBuilder;
import static io.gatling.javaapi.http.HttpDsl.http;

public class ApiEndpoints {
    public static final HttpRequestActionBuilder session = http("Session")
    .get("/session");

    public static final HttpRequestActionBuilder products = http("Products")
    .get("/products?page=0&search=");
}


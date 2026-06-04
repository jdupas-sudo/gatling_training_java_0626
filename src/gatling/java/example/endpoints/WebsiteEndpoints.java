package example.endpoints;

import io.gatling.javaapi.http.HttpRequestActionBuilder;
import static io.gatling.javaapi.http.HttpDsl.http;

public class WebsiteEndpoints {
    public static final HttpRequestActionBuilder home = http("Home")
    .get("https://ecomm.gatling.io/");
}

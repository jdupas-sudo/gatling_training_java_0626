package example.endpoints;

import io.gatling.javaapi.http.HttpRequestActionBuilder;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class WebsiteEndpoints {
    public static final HttpRequestActionBuilder home = http("Home")
    .get("https://ecomm.gatling.io/");

    public static final HttpRequestActionBuilder loginPage = http("LoginPage")
    .get("https://ecomm.gatling.io/login")
    .check(status().is(200));
}

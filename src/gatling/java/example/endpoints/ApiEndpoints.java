package example.endpoints;

import io.gatling.javaapi.http.HttpRequestActionBuilder;
import static io.gatling.javaapi.http.HttpDsl.http;

import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.http.HttpDsl.*;

public class ApiEndpoints {
    public static final HttpRequestActionBuilder session = http("Session")
    .get("/session");

    public static final HttpRequestActionBuilder products = http("Products")
    .get("/products?page=0&search=");

    public static final HttpRequestActionBuilder login = http("Login")
    .post("/login")
    .asFormUrlEncoded()
    .formParam("username", "#{username}")
    .formParam("password", "#{password}")
    .check(status().is(200))
    .check(jmesPath("accessToken").saveAs("AccessToken"));
}   


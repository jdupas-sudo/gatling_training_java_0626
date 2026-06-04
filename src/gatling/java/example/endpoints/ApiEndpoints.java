package example.endpoints;

import io.gatling.javaapi.http.HttpRequestActionBuilder;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.core.CoreDsl.ElFileBody;


import static io.gatling.javaapi.core.CoreDsl.jmesPath;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.http.HttpDsl.*;


public class ApiEndpoints {
    public static final HttpRequestActionBuilder session = http("Session")
    .get("/session");

    public static final HttpRequestActionBuilder products = http("Products")
    .get("/products")
    .queryParam("page", "#{pageNumber}")
    .queryParam("search", "#{searchKey}")
    .check(status().is(200))
    .check(jsonPath("$.products[*]").findRandom().saveAs("RandomProduct"));

    public static final HttpRequestActionBuilder login = http("Login")
    .post("/login")
    .asFormUrlEncoded()
    .formParam("username", "#{username}")
    .formParam("password", "#{password}")
    .check(status().is(200))
    .check(jmesPath("accessToken").saveAs("AccessToken"));

  // Add to cart — body template lives in resources/bodies/cart.json and reads #{SessionId} + #{CartItems}.
  public static final HttpRequestActionBuilder addToCart = http("Add to cart")
      .post("/cart")
      .asJson()
      .body(ElFileBody("bodies/cart.json"))
      .check(status().is(200));

}   


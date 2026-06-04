package example;

import io.gatling.javaapi.core.Assertion;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.javaapi.core.FeederBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static example.endpoints.WebsiteEndpoints.home;
import static example.endpoints.WebsiteEndpoints.loginPage;
import static example.endpoints.ApiEndpoints.session;
import static example.endpoints.ApiEndpoints.products;
import static example.endpoints.ApiEndpoints.login;
import static example.endpoints.ApiEndpoints.addToCart;

public class BasicSimulation extends Simulation {

  // Load VU count from system properties
  // Reference: https://docs.gatling.io/guides/passing-parameters/
  private static final int vu = Integer.getInteger("vu", 2);

  private static final FeederBuilder<Object> usersFeeder = jsonFile("data/users_dev.json").circular();

  // Define HTTP configuration
  // Reference: https://docs.gatling.io/reference/script/protocols/http/protocol/
  private static final HttpProtocolBuilder httpProtocol = http.baseUrl("https://api-ecomm.gatling.io")
      .acceptHeader("application/json")
      .userAgentHeader(
          "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36");

  // Define scenario
  // Reference: https://docs.gatling.io/reference/script/core/scenario/
  private static final ScenarioBuilder scenario = scenario("Scenario 1").exec(
    home,
    session,
    loginPage,
    feed(usersFeeder),
    login,
    // Seed query-param values for the products call.
    exec(session -> session.set("pageNumber", "0")),
    exec(session -> session.set("searchKey", "")),
    products,
    // Wrap the random product picked by the check into the JSON array that cart.json expects.
    exec(session -> session.set("CartItems", "[" + session.getString("RandomProduct") + "]")),
    addToCart);
  // Define assertions
  // Reference: https://docs.gatling.io/reference/script/core/assertions/
  private static final Assertion assertion = global().failedRequests().count().lt(1L);

  // Define injection profile and execute the test
  // Reference: https://docs.gatling.io/reference/script/core/injection/
  {
    setUp(scenario.injectOpen(atOnceUsers(vu))).assertions(assertion).protocols(httpProtocol);
  }
}

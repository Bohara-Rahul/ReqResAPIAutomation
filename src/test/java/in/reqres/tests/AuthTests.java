package in.reqres.tests;

import in.reqres.tests.models.Auth;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AuthTests {
    @Test
    public void valid_register() {
        var auth = new Auth("eve.holt@reqres.in", "pistol");

        String res = given()
                .contentType(ContentType.JSON)
                .baseUri("https://reqres.in")
                .when()
                    .body(auth)
                    .post("/api/register")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response()
                    .asString();

        assertThat(JsonPath.from(res).getString("id"), equalTo("4"));
        assertThat(JsonPath.from(res).getString("token"), equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void invalid_register() {
        var auth = new Auth("sydney@fife");
        String res = given()
                .contentType(ContentType.JSON)
                .when()
                    .body(auth)
                    .post("https://reqres.in/api/register")
                .then()
                    .assertThat()
                    .statusCode(400)
                    .extract()
                    .response()
                    .asString();

        assertThat(JsonPath.from(res).getString("error"), equalTo("Missing password"));
    }

    @Test
    public void valid_login() {
        var auth = new Auth("eve.holt@reqres.in", "cityslicka");
        String res = given()
                .contentType(ContentType.JSON)
                .when()
                    .body(auth)
                    .post("https://reqres.in/api/login")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response()
                    .asString();

        assertThat(JsonPath.from(res).getString("token"), equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void invalid_login() {
        var auth = new Auth("peter@klaven");
        String res = given()
                .contentType(ContentType.JSON)
                .when()
                    .body(auth)
                    .post("https://reqres.in/api/login")
                .then()
                    .assertThat()
                    .statusCode(400)
                    .extract()
                    .response()
                    .asString();

        assertThat(JsonPath.from(res).getString("error"), equalTo("Missing password"));
    }
}


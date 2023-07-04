package in.reqres.tests;

import in.reqres.tests.models.User;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserTests {

    @Test
    public void get_users() {
        given()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .assertThat()
                .statusCode(200)
                .body("page", equalTo(1),
                        "per_page", equalTo(6),
                        "total", equalTo(12),
                        "total_pages", equalTo(2),
                        "data.size()", equalTo(6),
                        "data.id", hasItems(1, 2, 3, 4, 5, 6),
                        "data.first_name", hasItems("George", "Janet", "Emma", "Eve", "Charles", "Tracey"),
                        "data.last_name", hasItems("Bluth", "Weaver", "Wong", "Holt", "Morris", "Ramos"),
                        "data.email", hasItems("george.bluth@reqres.in", "janet.weaver@reqres.in", "emma.wong@reqres.in", "eve.holt@reqres.in",
                                "charles.morris@reqres.in", "tracey.ramos@reqres.in"),
                        "data.avatar", hasItems("https://reqres.in/img/faces/1-image.jpg", "https://reqres.in/img/faces/2-image.jpg",
                                "https://reqres.in/img/faces/3-image.jpg", "https://reqres.in/img/faces/4-image.jpg",
                                "https://reqres.in/img/faces/5-image.jpg", "https://reqres.in/img/faces/6-image.jpg"));


    }

    @Test
    public void get_valid_user() {
        var user = new User("2",
                "Janet",
                "Weaver",
                "janet.weaver@reqres.in",
                "https://reqres.in/img/faces/2-image.jpg"
        );

        String res = given()
                .baseUri("https://reqres.in")
                .pathParam("userId", user.getId())
                .when()
                .get("/api/users/{userId}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        assertThat(JsonPath.from(res).getString("data.id"), equalTo(user.getId()));
        assertThat(JsonPath.from(res).getString("data.first_name"), equalTo(user.getFirstName()));
        assertThat(JsonPath.from(res).getString("data.last_name"), equalTo(user.getLastName()));
        assertThat(JsonPath.from(res).getString("data.email"), equalTo(user.getEmail()));
        assertThat(JsonPath.from(res).getString("data.avatar"), equalTo(user.getAvatar()));
    }

    @Test
    public void get_invalid_user_error() {
        var user = new User("15");

        given()
                .when()
                .get("https://reqres.in/api/users/" + user.getId())
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    public void create_user() {
        var user = new User("testuser", "programmer");

        given()
                .when()
                .body(user)
                .post("https://reqres.in/api/users")
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void update_user() {
        var user = new User("5");

        given()
                .when()
                .put("https://reqres.in/api/users/" + user.getId())
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void delete_user() {
        var user = new User("10");

        given()
                .when()
                .delete("https://reqres.in/api/users/" + user.getId())
                .then()
                .assertThat()
                .statusCode(204);
    }

}

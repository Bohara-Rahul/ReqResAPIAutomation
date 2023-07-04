package in.reqres.tests;

import in.reqres.tests.models.Resource;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class ResourceTests {
    @Test
    public void get_resources() {
        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .assertThat()
                .statusCode(200)
                .body("page", equalTo(1),
                        "per_page", equalTo(6),
                        "total", equalTo(12),
                        "total_pages", equalTo(2),
                        "data.size()", equalTo(6),
                        "data.id", hasItems(1, 2, 3, 4, 5, 6),
                        "data.name", hasItems("cerulean", "fuchsia rose", "true red", "aqua sky", "tigerlily", "blue turquoise"),
                        "data.color", hasItems("#98B2D1", "#C74375", "#BF1932", "#7BC4C4", "#E2583E", "#53B0AE"),
                        "data.pantone_value", hasItems("15-4020", "17-2031", "19-1664", "14-4811",
                                "17-1456", "15-5217"));
    }

    @Test
    public void get_resources_by_params() {
        HashMap<String, Integer> queryParams = new HashMap<String, Integer>();
        queryParams.put("page", 2);
        queryParams.put("per_page", 5);

        given()
                .queryParams(queryParams)
                .when()
                    .get("https://reqres.in/api/unknown")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .body("page", equalTo(2),
                        "per_page", equalTo(5),
                        "total", equalTo(12),
                        "total_pages", equalTo(3),
                        "data.id", hasItems(6, 7, 8, 9, 10),
                        "data.name", hasItems("blue turquoise", "sand dollar", "chili pepper", "blue iris", "mimosa"),
                        "data.year", hasItems(2005, 2006, 2007, 2008, 2009),
                        "data.color", hasItems("#53B0AE", "#DECDBE", "#9B1B30", "#5A5B9F", "#F0C05A"),
                        "data.pantone_value", hasItems("15-5217", "13-1106", "19-1557", "18-3943", "14-0848")
                );

    }

    @Test
    public void get_valid_resource() {
        var resource = new Resource(2,
                "fuchsia rose",
                2001,
                "#C74375",
                "17-2031"
        );

        String res = given()
                .when()
                    .get("https://reqres.in/api/unknown/"+resource.getId())
                .then()
                    .assertThat()
                        .statusCode(200)
                        .extract()
                        .response()
                        .asString();

        assertThat(JsonPath.from(res).getInt("data.id"), equalTo(resource.getId()));
        assertThat(JsonPath.from(res).getString("data.name"), equalTo(resource.getName()));
        assertThat(JsonPath.from(res).getInt("data.year"), equalTo(resource.getYear()));
        assertThat(JsonPath.from(res).getString("data.color"), equalTo(resource.getColor()));
        assertThat(JsonPath.from(res).getString("data.pantone_value"), equalTo(resource.getPantoneValue()));
    }

    @Test
    public void invalid_resource_error() {
        var resource = new Resource(15);

        given()
                .when()
                    .get("https://reqres.in/api/unknown/" + resource.getId())
                .then()
                    .assertThat()
                    .statusCode(404);
    }
}

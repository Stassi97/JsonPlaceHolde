package framework;

import io.restassured.http.ContentType;
import com.google.gson.JsonParser;
import static io.restassured.RestAssured.given;

public class BaseAdapter {
    public String get(String uri, int status) {
        PropertyReader propertyReader = new PropertyReader("config.properties");
        String string = given().
                header("Content-type", "application/json").
                get(propertyReader.getProperty("URL")+uri).
                then().
                log().all().
                statusCode(status).
                and().
                contentType(ContentType.JSON).
                extract().body().asString();
        return string;
    }

    public String post(String body, String uri, int status) {
        return  given().
                header("Content-type", "application/json").
                body(body).
                when().
                post(PropertyReader.getProperty("URL") + uri).
                then().
                log().all().
                statusCode(status).
                and().
                contentType(ContentType.JSON).
                extract().body().asString();
    }
}
package jsonPlaceHolder.adapters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import framework.BaseAdapter;
import io.qameta.allure.Step;
import jsonPlaceHolder.pojo.PostsModal;
import org.openqa.selenium.json.Json;
import framework.PropertyReader;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static com.google.gson.JsonParser.parseString;
import static framework.PropertyReader.getIntProperty;

public class PostsAdapter extends BaseAdapter {

    protected Gson gson = new Gson();
    SoftAssert softAssert = new SoftAssert();

    @Step("Get all posts")
    public void getPosts(String endUri, int status) {
        String response = get(endUri, status);
        System.out.println(response);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(response);
        System.out.println("Number of posts: " + jsonArray.size());
        for(int i = 1; i<jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject(); //из массивы вытащили Iый эл-т  и преобразовали в Json объект
            Assert.assertTrue(jsonObject.get("id").getAsInt()-i==1, "Post cannot be found 404");
        }
    }

    @Step("Post")
    public void postPost(PostsModal postsModal, String uri, int status) {
        String response = post(gson.toJson(postsModal), uri, status);
        JsonObject jsonObject = parseString(response).getAsJsonObject();
        int userId = jsonObject.get("userId").getAsInt();
        int id = jsonObject.get("id").getAsInt();
        String title = jsonObject.get("title").getAsString();
        String body = parseString(response)
                .getAsJsonObject().get("body").getAsString();
        softAssert.assertEquals(userId, postsModal.getUserId(), "");
        softAssert.assertEquals(id, postsModal.getId(), "Ids are not equal");
        softAssert.assertAll();
    }

    @Step("Get one post")
    public void getOnePost(String endUri, int status) {
        String response = get(endUri, status);
        JsonObject jsonObject = parseString(response).getAsJsonObject();
        int userId = jsonObject.getAsJsonObject().get("userId").getAsInt();
        int id = jsonObject.get("id").getAsInt();
        String title = jsonObject.get("title").getAsString();
        String body = jsonObject.get("body").getAsString();
        softAssert.assertEquals(userId, 10, "Wrong userId");
        softAssert.assertEquals(id, 99, "Wrong id");
        softAssert.assertTrue(!title.isEmpty(), "Title is empty");
        softAssert.assertTrue(!body.isEmpty(), "Body is empty");
        softAssert.assertAll();
    }

    @Step("Get wrong post")
    public void getWrongPost(String endUri, int status) {
        String response = get(endUri, status);
        JsonObject jsonObject = parseString(response).getAsJsonObject();
        int id = jsonObject.get("id").getAsInt();
        softAssert.assertEquals(id, 150, "Wrong id");
        softAssert.assertAll();
    }
}
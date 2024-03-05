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
    public void getOnePost(String endUri, int id, int status) {
        JsonParser jsonParser = new JsonParser();
        String response2 = get(endUri, status);
        JsonArray jsonArray = (JsonArray) jsonParser.parse(response2);
        int count = jsonArray.size();
        if (count >= id) {
            String response = get(endUri, id, status);
            int userId = parseString(response)
                    .getAsJsonObject().get("userId").getAsInt();
            int idOfPost = parseString(response)
                    .getAsJsonObject().get("id").getAsInt();
            String title = parseString(response)
                    .getAsJsonObject().get("title").getAsString();
            String body = parseString(response)
                    .getAsJsonObject().get("body").getAsString();
            if(userId == getIntProperty("userId")) {
                System.out.println("UserId corresponds to those pased in the request, userId = " + userId);
                if (idOfPost == getIntProperty("id")) {
                    System.out.println("Id is present in the response, Id = " + id);
                    if(!title.isEmpty()) {
                        System.out.println("Title corresponds to those passed in the request title = " + title);
                        if(!body.isEmpty()){
                            System.out.println("Body corresponds to those passed in the request title =" + body);
                        } else {
                            System.out.println("Body is empty");
                        }
                    }else {
                        System.out.println("Title is empty");
                    }
                } else {
                    System.out.println("Id is empty");
                }
            } else {
                System.out.println("UserId is empty");
            }
        }
    }
}
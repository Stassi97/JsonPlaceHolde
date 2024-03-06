package jsonPlaceHolder.adapters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import framework.BaseAdapter;
import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.core.TreeNode;
import jsonPlaceHolder.pojo.PostsModal;
import org.openqa.selenium.json.Json;
import framework.PropertyReader;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static com.google.gson.JsonParser.parseString;
import static framework.PropertyReader.getIntProperty;
public class PostsUsers extends BaseAdapter{

    protected Gson gson = new Gson();
    SoftAssert softAssert = new SoftAssert();

    @Step("Get all users")
    public void getUsers(String endUri, int status) {
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

    @Step ("Compare user info")
    public void compareUser(String endUri, int status) {
        String response2 = get(endUri, status);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(response2);
        //  JsonObject jsonObject = parseString(response2).getAsJsonObject();
        for(int i = 0; i<jsonArray.size(); i++) {
             JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            int id = jsonObject.getAsJsonObject().get("id").getAsInt();
            String name = jsonObject.get("name").getAsString();
            String userName = jsonObject.get("userName").getAsString();
            String email = jsonObject.get("email").getAsString();
            String street = jsonObject.get("street").getAsString();
            String suite = jsonObject.get("suite").getAsString();
            String city = jsonObject.get("city").getAsString();
            String zipcode = jsonObject.get("zipcode").getAsString();
            String lat = jsonObject.get("lat").getAsString();
            String lng = jsonObject.get("lng").getAsString();
            String phone = jsonObject.get("phone").getAsString();
            String website = jsonObject.get("website").getAsString();
            String companyName = jsonObject.get("companyName").getAsString();
            String catchPhrase = jsonObject.get("catchPhrase").getAsString();
            String bs = jsonObject.get("bs").getAsString();
            if (id == 5) {
                softAssert.assertEquals(id, 5, "Wrong id");
                softAssert.assertTrue(!name.contains("Chelsey Dietrich"), "Wrong name");
                softAssert.assertTrue(!userName.contains("Kamren"), "Wrong user name");
                softAssert.assertTrue(!email.contains("Lucio_Hettinger@annie.ca"), "Wrong user email");
                softAssert.assertTrue(!street.contains("Skiles Walks"), "Wrong user street");
                softAssert.assertTrue(!suite.contains("Suite 351"), "Wrong user suite");
                softAssert.assertTrue(!city.contains("Roscoeview"), "Wrong user city");
                softAssert.assertTrue(!zipcode.contains("33263"), "Wrong user zipcode");
                softAssert.assertTrue(!lat.contains("-31.8129"), "Wrong user lat");
                softAssert.assertTrue(!lng.contains("62.5342"), "Wrong user lng");
                softAssert.assertTrue(!phone.contains("(254)954-1289"), "Wrong user phone");
                softAssert.assertTrue(!website.contains("demarco.info"), "Wrong user website");
                softAssert.assertTrue(!companyName.contains("Keebler LLC"), "Wrong user company");
                softAssert.assertTrue(!catchPhrase.contains("User-centric fault-tolerant solution"), "Wrong user catchPhrase");
                softAssert.assertTrue(!bs.contains("revolutionize end-to-end systems"), "Wrong user bs");
                softAssert.assertAll();
            }
        }
    }
}
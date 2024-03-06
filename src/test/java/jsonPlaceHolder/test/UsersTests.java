package jsonPlaceHolder.test;

import com.github.javafaker.Faker;
import framework.PropertyReader;
import jsonPlaceHolder.adapters.PostsAdapter;
import jsonPlaceHolder.adapters.PostsUsers;
import jsonPlaceHolder.pojo.PostsModal;
import org.testng.annotations.Test;
public class UsersTests {

    PropertyReader propertyReader = new PropertyReader("config.properties");
    @Test
    public void getUsers() {
        PostsUsers postsUsers = new PostsUsers();
        postsUsers.getUsers(propertyReader.getProperty("END_URI_USERS"), propertyReader.getIntProperty("status200"));
    }

    @Test
    public void compareUser() {
        int status = propertyReader.getIntProperty("status200");
        String uri = propertyReader.getProperty("END_URI_USERS");
        PostsUsers postsUsers = new PostsUsers();
        String id = "5";
        postsUsers.compareUser (uri +  "/" + id, status);
    }
}

package jsonPlaceHolder.test;

import com.github.javafaker.Faker;
import framework.PropertyReader;
import jsonPlaceHolder.adapters.PostsAdapter;
import jsonPlaceHolder.pojo.PostsModal;
import org.testng.annotations.Test;


public class PostsTests {
    PropertyReader propertyReader = new PropertyReader("config.properties");
    @Test
    public void getAllPosts() {
        PostsAdapter postsAdapter = new PostsAdapter();
        postsAdapter.getPosts(propertyReader.getProperty("END_URI_POSTS"), propertyReader.getIntProperty("status200"));
    }

    @Test
    public void postPost() {
        Faker faker = new Faker();
        PostsModal postsModal = PostsModal.builder()
                .userId(1)
                .id(101)
                .title(faker.dog().name())
                .body(faker.yoda().quote())
                .build();
        PostsAdapter postsAdapter = new PostsAdapter();
        postsAdapter.postPost(postsModal, propertyReader.getProperty("END_URI_POSTS"), propertyReader.getIntProperty("status201"));
    }

    @Test
    public void getOnePost() {
        PostsAdapter postsAdapter = new PostsAdapter();
        int status = propertyReader.getIntProperty("status200");
        String uri = propertyReader.getProperty("END_URI_POSTS");
        postsAdapter.getOnePost(uri,  propertyReader.getIntProperty("99"), status);
    }
}
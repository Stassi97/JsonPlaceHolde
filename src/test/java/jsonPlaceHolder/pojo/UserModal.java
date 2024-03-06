package jsonPlaceHolder.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.*;
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserModal {
    int id;
    String name;
    @SerializedName("username")
    String userName;
    String email;
    String street;
    String suite;
    String city;
    String zipcode;
    String lat;
    String lng;
    String phone;
    String website;
    @SerializedName("name")
    String companyName;
    String catchPhrase;
    String bs;
}
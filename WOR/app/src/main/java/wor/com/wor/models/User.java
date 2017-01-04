package wor.com.wor.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {

    private String username;
    private String email;
    private String photoUrl;
    private String uppercaseName;
    private String uid;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public User(String username, String email,String photoUrl, String Uppercase, String Uid) {
        this.username = username;
        this.email = email;
        this.photoUrl = photoUrl;
        this.uid = Uid;
        this.uppercaseName = Uppercase;

    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("username", username);
        result.put("email", email);
        result.put("photoUrl", photoUrl);
        result.put("uppercaseName", uppercaseName);

        return result;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String text) {
        this.uid = text;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String text) {
        this.username = text;
    }
    public String getUppercaseName() {
        return uppercaseName;
    }

    public void setUppercaseName(String text) {
        this.uppercaseName = text;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}

package wor.com.wor.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class User implements Serializable {

    private String id;
    private String userName;
    private String email;
    private String photoUrl;
    private String mobile;
    private String rollNo;
    private String blockNo;
    private int    balance;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String userName, String email, String photoUrl, String mobile, String rollNo, String blockNo, int balance) {
        this.userName = userName;
        this.email = email;
        this.photoUrl = photoUrl;
        this.mobile = mobile;
        this.rollNo = rollNo;
        this.blockNo = blockNo;
        this.balance = balance;
    }

    public User(String id, String userName, String email, String photoUrl, String mobile, String rollNo, String blockNo, int balance) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.photoUrl = photoUrl;
        this.mobile = mobile;
        this.rollNo = rollNo;
        this.blockNo = blockNo;
        this.balance = balance;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object>  result = new HashMap<>();
        result.put("id", id);
        result.put("userName", userName);
        result.put("email", email);
        result.put("photoUrl", photoUrl);
        result.put("mobile", mobile);
        result.put("rollNo", rollNo);
        result.put("blockNo", blockNo);
        result.put("balance", balance);
        return result;
    }

    @Override
    public String toString() {
        return  id+"\n"+
                userName+"\n"+
                email+"\n"+
                photoUrl+"\n"+
                mobile+"\n"+
                rollNo+"\n"+
                blockNo+"\n"+
                String.valueOf(balance);
    }

    public static User parse(String userString) {
        String[] entities = userString.split("\n");
        return new User(entities[0], entities[1], entities[2], entities[3], entities[4], entities[5], entities[6], Integer.parseInt(entities[7]));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getBlockNo() {
        return blockNo;
    }

    public void setBlockNo(String blockNo) {
        this.blockNo = blockNo;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

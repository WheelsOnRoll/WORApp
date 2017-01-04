package wor.com.wor.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by hrishi on 4/1/17.
 */

public class Referral implements Serializable {

    private String code, to;

    public Referral(String code, String to) {
        this.code = code;
        this.to = to;
    }

    @Exclude
    public HashMap<String, Object> toMap(){
        HashMap<String, Object> result =  new HashMap<>();
        result.put("code", code);
        result.put("to", to);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

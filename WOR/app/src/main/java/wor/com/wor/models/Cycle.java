package wor.com.wor.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Santosh on 14-Jan-17.
 */

public class Cycle implements Serializable {

    private String Image, model;
    private Long quantity;
    public Cycle() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public Cycle(String Image, String model, Long quantity) {
        this.Image = Image;
        this.quantity = quantity;
        this.model = model;
    }

    @Exclude
    public HashMap<String, Object> toMap(){
        HashMap<String, Object> result =  new HashMap<>();
        result.put("Image", Image);
        result.put("model", model);
        result.put("quantity", quantity);
        return result;
    }
    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


}

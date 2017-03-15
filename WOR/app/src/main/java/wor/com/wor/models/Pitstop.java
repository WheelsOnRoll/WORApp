package wor.com.wor.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;


public class Pitstop implements Serializable {

    private String name;



    private Cycle cycle;

    public Pitstop(String name,Cycle Cycle) {
        this.name = name;
        this.cycle = Cycle;
    }


    @Exclude
    public HashMap<String, Object> toMap(){
        HashMap<String, Object> result =  new HashMap<>();
        result.put("name", name);
        result.put("cycle", cycle);
        return result;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}



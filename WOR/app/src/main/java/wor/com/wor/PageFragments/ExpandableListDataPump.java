package wor.com.wor.PageFragments;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wor.com.wor.R;

public class ExpandableListDataPump {

    private static Context context;

    public ExpandableListDataPump(Context context) {
        this.context = context;
    }

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> q1 = new ArrayList<String>();
        q1.add(context.getString(R.string.a1));

        List<String> q2 = new ArrayList<String>();
        q2.add(context.getString(R.string.a2));

        List<String> q3 = new ArrayList<String>();
        q3.add(context.getString(R.string.a3));

        List<String> q4 = new ArrayList<String>();
        q4.add(context.getString(R.string.a4));

        List<String> q5 = new ArrayList<String>();
        q5.add(context.getString(R.string.a5));

        List<String> q6 = new ArrayList<String>();
        q6.add(context.getString(R.string.a6));

        expandableListDetail.put(context.getString(R.string.q1), q1);
        expandableListDetail.put(context.getString(R.string.q2), q2);
        expandableListDetail.put(context.getString(R.string.q3), q3);
        expandableListDetail.put(context.getString(R.string.q4), q4);
        expandableListDetail.put(context.getString(R.string.q5), q5);
        expandableListDetail.put(context.getString(R.string.q6), q6);
        return expandableListDetail;
    }
}


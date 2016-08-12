package acadgild.com.explandedlistdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hitesh-PC on 8/12/2016.
 */
public class ExpandedListView {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> fuel = new ArrayList<String>();
        fuel.add("ABC");


        List<String> restaurant = new ArrayList<String>();
        restaurant.add("LMN");


        List<String> medicines = new ArrayList<String>();
        medicines.add("OPQ");

        List<String> others = new ArrayList<String>();
        medicines.add("XYZ");

        expandableListDetail.put("Fuel", fuel);
        expandableListDetail.put("Restaurants", restaurant);
        expandableListDetail.put("Medicines", medicines);
        expandableListDetail.put("Others",others);
        return expandableListDetail;
    }
}

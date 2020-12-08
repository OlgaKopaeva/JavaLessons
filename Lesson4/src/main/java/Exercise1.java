import java.util.*;

public class Exercise1 {

    public static void main(String[] args) {
        ArrayList<String> myArray = new ArrayList<>(Arrays.asList("City", "Town", "Ciudad", "Город", "Capital",
                "Столица", "City", "Town", "Capital", "City", "Город", "Столица", "City", "City", "Ciudad", "Town"));

        HashMap<String, Integer> MyMap = new HashMap<>(myArray.size());
        for (int i = 0; i < myArray.size(); i++) {
            String str = myArray.get(i);
            if (MyMap.containsKey(str)) {
                MyMap.put(str, MyMap.get(str) + 1);
            } else {
                MyMap.put(str, 1);
            }
        }
        System.out.println(MyMap);



    }

}







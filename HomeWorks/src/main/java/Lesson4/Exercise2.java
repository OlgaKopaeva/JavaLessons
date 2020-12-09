import java.util.ArrayList;
import java.util.HashMap;

public class Exercise2 {
    public static class PhoneMap {
        HashMap<String, ArrayList<String>> phoneMap = new HashMap<>();

        public PhoneMap() {
        }

        @Override
        public String toString(){
            return phoneMap.toString();
        }

        public void addPhone(String surname, String phone) {
            if (phoneMap.containsKey(surname)) {
                ArrayList<String> phones = phoneMap.get(surname);
                phones.add(phone);
            } else {
                ArrayList<String> phones = new ArrayList<String>();
                phones.add(phone);
                phoneMap.put(surname, phones);
            }
        }

        public ArrayList<String> getPhones(String surname) {
            if (phoneMap.containsKey(surname)) {
                return phoneMap.get(surname);
            } else return new ArrayList<String>();
        }
    }

    public static void main(String[] args) {
        PhoneMap myPhoneBook = new PhoneMap();
        myPhoneBook.addPhone("Ivanov", "12233435");
        myPhoneBook.addPhone("Petrov", "12345678");
        myPhoneBook.addPhone("Petrov", "87654321");
        myPhoneBook.addPhone("Petrenko", "643423425");
        myPhoneBook.addPhone("Sidorovich", "64342345");

        System.out.println(myPhoneBook);

        System.out.println(myPhoneBook.getPhones("Petrov"));
        System.out.println(myPhoneBook.getPhones("Anisimov"));

    }
}

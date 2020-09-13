package Lesson3;

import java.util.Arrays;
import java.util.Scanner;

public class WordGame {
    public static final int MASK_LENGTH = 15;
    protected static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String[] words = createWordsArray();
        String wordPuzzle = createPuzzle(words);
        char[] mask = createFirstMask();
        while(true) {
            outputTask(words);
            outputMask(mask);
            String answer = in.nextLine().toLowerCase();
            changeMask(mask, answer, wordPuzzle);
            outputMask(mask);
            if (answer.equals(wordPuzzle)) {
                System.out.println("Вы отгадали!");
                break;
            }
            System.out.println("Попробуйте ещё");
        }
    }

    public static String[] createWordsArray() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear",
                "pepper", "pineapple", "pumpkin", "potato"};
        return words;
    }

    public static void outputTask(String[] words) {
        System.out.println("Отгадайте загаданное слово из списка.");
        System.out.println(Arrays.toString(words));
    }

    public static char[] createFirstMask() {
        char[] mask = new char[MASK_LENGTH];
        Arrays.fill(mask, '*');
        return mask;
    }

    public static void outputMask(char[] mask) {
        String myString = new String(mask);
        System.out.println(mask);
    }

    public static String createPuzzle(String[] words) {
        int index = (int) (Math.random() * 24 + 1);
        String puzzle = words[index];
        return puzzle;
    }

    public static char[] changeMask(char[] mask, String answer, String wordPuzzle) {
        int lengthMin = Math.min(answer.length(), wordPuzzle.length());
        for (int i = 0; i < lengthMin; i++) {
            mask[i] = (answer.charAt(i) == wordPuzzle.charAt(i)) ? answer.charAt(i) : mask[i];
        }
        return mask;
    }
}

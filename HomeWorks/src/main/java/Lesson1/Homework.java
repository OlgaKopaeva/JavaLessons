package Lesson1;

import java.util.Objects;

public class Homework {
    protected static Object[] participants =
            {
                    new Cat("Мурзик", 2, 5),
                    new Human("Федор", 1, 6),
                    new Robot("Электроник", 3, 20)
            };
    protected static Object[] obstacles =
            {
                    new Treadmill(10),
                    new Wall(2)
            };

    protected static int getMaxLengthParticipant(Object participant) {
        if (participant instanceof Cat) {
            return ((Cat) participant).maxLength;
        } else if (participant instanceof Robot) {
            return ((Robot) participant).maxLength;
        } else if (participant instanceof Human) {
            return ((Human) participant).maxLength;
        } else return 0;
    }

    protected static int getMaxHeightParticipant(Object participant) {
        if (participant instanceof Cat) {
            return ((Cat) participant).maxHeight;
        } else if (participant instanceof Robot) {
            return ((Robot) participant).maxHeight;
        } else if (participant instanceof Human) {
            return ((Human) participant).maxHeight;
        } else return 0;
    }

    public static void main(String[] args) {

        for (int i = 0; i < participants.length; i++) {
            int maxRun = getMaxLengthParticipant(participants[i]);
            int maxJump = getMaxHeightParticipant(participants[i]);
            for (int j = 0; j < obstacles.length; j++) {
                System.out.println(obstacles[j].toString());
                if (obstacles[j] instanceof Treadmill) {
                    if (maxRun >= ((Treadmill) obstacles[j]).length)
                        System.out.println(participants[i].toString() + "  пробежал беговую дорожку!");
                    else
                        System.out.println(participants[i].toString() + "  не пробежал :(");
                } else if (obstacles[j] instanceof Wall) {
                    if (maxJump >= ((Wall) obstacles[j]).height)
                        System.out.println(participants[i].toString() + "  перепрыгнул стену!");
                    else
                        System.out.println(participants[i].toString() + "  не перепрыгнул :(");
                } else {
                    throw new RuntimeException("Unknown obstacle.type");
                }
            }
        }
    }
}

package Lesson3;

public class Array {
    public static class ArrayToChange<T> {
        private T[] obj;

        public ArrayToChange(T... оbj) {
            this.obj = оbj;
        }

        public T get(int i) {
            return obj[i];
        }

        public int length(){
            return obj.length;
        }

        public T[] changeMyArray (int index1, int index2) {
            T buffer = obj[index1];

            obj[index1] = obj[index2];
            obj[index2] = buffer;

            return obj;
        }

        public void sout(){
            for (int i = 0; i < obj.length; i++) {
                System.out.println(obj[i]);
            }
        }

    }
}

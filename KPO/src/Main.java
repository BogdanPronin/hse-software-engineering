
public class Main {

    public static void fizzBuzz() {
        for (int i = 1; i <= 100; i++) {
            int k = 0;
            if (i % 3 == 0) {
                System.out.print("Fizz");
                k++;
            }
            if (i % 5 == 0) {
                System.out.print("Buzz");
                k++;
            }
            if (k == 0) {
                System.out.print(i);
            }
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void fibonacci(int num) {
        int prev2 = 1;
        int prev1 = 0;
        for(int i = 1; i <= num; i++){
            int tmp = prev2 + prev1;
            System.out.print(tmp + " ");
            prev2 = prev1;
            prev1 = tmp;
        }
        System.out.println();
    }

    public static void factorial(int num) {
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result = result * i;
        }
        System.out.println(result);
    }

    public static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void let(String s) {
        int charCount = 0;
        String vowels = "уеыаоэёяию";
        int vowCount = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                charCount++;
                if (vowels.indexOf(c) != -1) {
                    vowCount++;
                }
            }
        }
        System.out.println("Гласных: " + vowCount + "\nСогласных: " + (charCount - vowCount));
    }

    public static void main(String[] args) {
        int[] a = {5, 4, 3, 1, 7, 9, -3, 2};
        fizzBuzz();
        fibonacci(11);
        factorial(8);
        bubbleSort(a);
        String s = "Под123счет Гласных90";
        let(s);
    }
}

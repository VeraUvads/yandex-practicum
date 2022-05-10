package practicum.sprint_1;

import java.util.Scanner;

public class WinFail {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if (checkPlayerLuck(a, b, c)) {
            System.out.println("WIN");
        } else {
            System.out.println("FAIL");
        }

        scanner.close();
    }

//    private static List<Integer> readList(BufferedReader reader) throws IOException {
//        return Arrays.asList(reader.readLine().split(" "))
//                .stream()
//                .map(elem -> Integer.parseInt(elem))
//                .collect(Collectors.toList());
//    }

//    private static boolean checkPlayerLuck(List<Integer> list) {
//        for (int i = 0; i < list.size() - 1; i++) {
//            if (checkIsEven(list.get(i)) != checkIsEven(list.get(i + 1))) {
//                return false;
//            }
//        }
//        return true;
//    }

    private static boolean checkPlayerLuck(int a, int b, int c) {
        return checkIsEven(a) == checkIsEven(b) && checkIsEven(b) == checkIsEven(c);
    }

    private static boolean checkIsEven(int number) {
        return number % 2 == 0;
    }
}

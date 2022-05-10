package practicum.sprint_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Factorization {

//    private static List<Integer> factorize(int total) {
//        List<Integer> result = new ArrayList<>();
//
//        for (int divider = 2; divider <= total; divider++) {
//            if (total % divider == 0 && total / divider == 1) {
//                result.add(divider);
//                break;
//            }
//            while (total % divider == 0) {
//                total = total / divider;
//                result.add(divider);
//            }
//        }
//        if (result.isEmpty()) {
//            result.add(total);
//        }
//        return result;
//    }

    //самое близкое
    private static List<Integer> factorize(int n) {
        List<Integer> result = new ArrayList<>();

        int total = n;

        for (int divider = 2; divider * divider <= n; divider++) {
            while (total >= divider) {
                if (total % divider == 0) {
                    total = total / divider;
                    result.add(divider);
                } else {
                    break;
                }
            }
        }
        if (total != 1) {
            result.add(total);
        }
        return result;
    }


//    private static List<Integer> factorize(int n) {
//        List<Integer> result = new ArrayList<>();
//
//        int total = n;
//
//        for (int divider = 2; divider * divider <= n; divider++) {
//            while (total >= divider) {
//                if (total % divider == 0) {
//                    total = total / divider;
//                    result.add(divider);
//                } else {
//                    break;
//                }
//            }
//        }
//
//
//
////        if (result.isEmpty()) {
////            result.add(n);
////        }
////
////        if (result.size() == 1) {
////            result.add(n / result.get(0));
////        }
//
//        return result;
//    }

    private static List<Integer> factorize2(int n) {
        List<Integer> result = new ArrayList<>();


        for (int divider = n / 2; divider >= 2; divider--) {
            if (n % divider == 0) {
                n = n / divider;
                result.add(divider);
                if (n > divider) {
                    divider = n / 2;
                }
            }
        }


//        if (result.isEmpty()) {
//            result.add(n);
//        }
//
//        if (result.size() == 1) {
//            result.add(n / result.get(0));
//        }

        return result;
    }

    // по канону
//    private static List<Integer> factorize(int n) {
//        boolean[] flags = new boolean[n + 1];
//
//        List<Integer> result = new ArrayList<>();
//        flags[0] = true;
//        flags[1] = true;
//        int total = n;
//        long start = System.currentTimeMillis();
//        for (int i = 2; i <= total; i++) {
//            if (!flags[i]) { // is prime
//                for (int j = i * i; j <= total && j > 0; j += i) {
//                    flags[j] = true;
//                }
//                while (total % i == 0) {
//                    result.add(i);
//                    total = total / i;
//                }
//                if (total == 1) break;
//            }
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
//        return result;
//    }

//    private static List<Integer> factorize(int n) {
//
//        int stepSize = 2048;
//        int maxStep = n + 1;
//
//        boolean[] flags = new boolean[stepSize+1];
//
//        List<Integer> result = new ArrayList<>();
//        flags[0] = true;
//        flags[1] = true;
//        int total = n;
//        for (int stepOffset = 0; stepOffset <= maxStep && total != 1; stepOffset += stepSize) {
//            int max = Math.min(stepOffset + stepSize, total);
//            Arrays.fill(flags, false);
//            for (int i = 2; i <= max; i++) {
//
//                if ( i < stepOffset || !flags[i - stepOffset]) { // is prime
//                    for (int j = i * i; j <= max && j > 0; j += i) {
//                        if(j > stepOffset){
//                        flags[j - stepOffset] = true;
//                        }
//                    }
//                    while (total % i == 0) {
//                        result.add(i);
//                        total = total / i;
//                    }
//                    if (total == 1) break;
//                }
//            }
//        }
//        return result;
//    }

//    private static List<Integer> factorize(int n) {
//        List<Integer> result = new ArrayList<>();
//        for (int i = 2; i <= n; i++) {
//            while (n >= i) {
//                if (n % i == 0) {
//                    result.add(i);
//                    n = n / i;
//                } else {
//                    break;
//                }
//            }
//        }
//        return result;
//    }

//    private static List<Integer> factorize(int n) {
//        List<Integer> result = new ArrayList<>();
//        List<Integer> all = new ArrayList<>();
//        List<Integer> prime = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            all.add(i);
//        }
//
//        for (int i = 2; i < all.size(); i++) {
//            if (all.get(i) != 0 && n % all.get(i) == 0) {
//                prime.add(n);
//                for (int j = i; i + j < all.size(); j = j + j) {
//                    all.set(i + j, 0);
//                }
//            }
//        }
//
//
////        int total = n;
////
////        for (int divider = 2; divider <= n; divider++) {
////            while (total % divider == 0) {
////                total = total / divider;
////                result.add(divider);
////            }
////        }
//        return prime;
//    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<Integer> factorization = factorize(n);
            for (int elem : factorization) {
                writer.write(elem + " ");
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class N2143_sub {

    static int T, N, M;
    static long ans;
    static int[] aSum, bSum;

    static List<Integer> aValue, bValue;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt(); // T(-1,000,000,000 ≤ T ≤ 1,000,000,000)

        N = sc.nextInt(); // n(1 ≤ n ≤ 1,000)
        aSum = new int[N+1];
        for (int i = 1; i <= N; i++) {
            aSum[i] = aSum[i-1] + sc.nextInt(); // 절댓값이 1,000,000을 넘지 않는 정수
        }

        M = sc.nextInt();// m(1≤m≤1,000)
        bSum = new int[M+1];
        for (int i = 1; i <= M; i++) {
            bSum[i] = bSum[i-1] + sc.nextInt(); // 절댓값이 1,000,000을 넘지 않는 정수
        }

        aValue = new ArrayList<>();
        get(aSum, aValue);
        bValue = new ArrayList<>();
        get(bSum, bValue);


        Collections.sort(bValue);

        ans = 0;
        for (Integer i : aValue) {
            int val = T - i;
            int high = upper_bound(bValue, val);
            int low = lower_bound(bValue, val);
            ans += high - low;
        }
        System.out.println(ans);
    }

    private static void get(int[] pSum, List<Integer> list) {
        for (int size = 1; size < pSum.length; size++) {

            // 시작지점을 잡아야 한다.
            int start = 0;
            for(int idx = 1; idx <= pSum.length - size; idx++){
                int sum = (pSum[idx + size - 1] - pSum[idx-1]);
                list.add(sum);
            }

        }
    }
    private static void get2(int[] data, List<Integer> list) {
        for (int size = 1; size <= data.length; size++) {
            int sum = 0;
            for (int i = 0; i < size; i++) {
                sum += data[i];
            }
            for (int i = size; i < data.length; i++) {
                list.add(sum);
                sum += data[i];
                sum -= data[i - size];
            }
            list.add(sum);
        }
    }

    // lower bound는 찾고자 하는 값 이상이 처음 나타나는 위치
    private static int lower_bound(List<Integer> list, int val) {
        int start = 0;
        int end = list.size();
        int mid;
        while (start < end) {
            mid = (start + end) >> 1;
            if (list.get(mid) >= val) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    // upper bound는 찾고자 하는 값보다 큰 값이 처음으로 나타나는 위치
    private static int upper_bound(List<Integer> list, int val) {
        int start = 0;
        int end = list.size();
        int mid;
        while (start < end) {
            mid = (start + end) >> 1;
            if (list.get(mid) <= val) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}

import java.util.Scanner;

public class BinaryStride {


    static int binaryStride(int arr[], int val) {
        if(arr.length==0)
            return -1;

        int stride = 1;
        int prev=0;
        int cur = prev+stride;
        while(cur<arr.length && arr[cur]<val) {
            stride*=2;
            prev=cur;
            cur+=stride;
        }

        return doBinarySearch(arr, prev, Math.min(cur, arr.length-1), val);
    }

    public static void main(String[] args) {
        int[] a = {2, 5};
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println(binaryStride(a,sc.nextInt()));
        }
    }

    static int doBinarySearch(int[]arr, int lo, int hi, int val) {

        while(lo<=hi) {
            int mid=(lo+hi)/2;
            if(arr[mid]==val) {
                return mid;
            }
            else if(arr[mid]>val) {
                hi=mid-1;
            }
            else {
                lo=mid+1;
            }
        }
        return hi;
    }
}

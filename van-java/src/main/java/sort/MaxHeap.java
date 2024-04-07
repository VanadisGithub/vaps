package sort;

/**
 * MaxHeap
 *
 * @author yaoyuan
 * @date 2024/2/19 17:30
 */
public class MaxHeap {

    public static void main(String[] args) {
        int[] arr = {7,9,2,1,3,5,4,8,0};
        heapSort(arr);

    }

    public static void sout(int[] arr){
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            sout(arr);
            heapify(arr, 0, size);
            sout(arr);
            System.out.println();
            swap(arr, 0, --size);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
            sout(arr);
        }
    }

    /**
     * 堆化
     */
    public static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}

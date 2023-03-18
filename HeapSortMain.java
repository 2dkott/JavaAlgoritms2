import java.util.Arrays;

public class HeapSortMain {

    public static void main(String[] args) {
        int[] array = new int[]{1,5,34,2,46,-1};
        heapSort(array);
        System.out.print(array.toString());


    }

    public static void heapify(int[] array, int lastIndex, int rootIndex) {
        int largestIndex = rootIndex;
        int leftChildIndex = 2*rootIndex+1;
        int rightChildIndex = 2*rootIndex+2;

        if(leftChildIndex < lastIndex && array[leftChildIndex] > array[largestIndex]){
            largestIndex = leftChildIndex;
        }

        if(rightChildIndex < lastIndex && array[rightChildIndex] > array[largestIndex]){
            largestIndex = rightChildIndex;
        }

        if (largestIndex != rootIndex){
            int temp = array[rootIndex];
            array[rootIndex] = array[largestIndex];
            array[largestIndex] = temp;
            heapify(array, lastIndex, largestIndex);
        }


    }

    static void heapSort(int[] array)
    {
        for (int i = array.length / 2 - 1; i >= 0; i--)
            heapify(array, array.length, i);

        for (int i = array.length - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);
        }
    }


}

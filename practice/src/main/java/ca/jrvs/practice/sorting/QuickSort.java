package ca.jrvs.practice.sorting;

public class QuickSort {

  public void quickSort(int[] arr, int start, int end) {
    if (start < end) {
      int partitionIndex = partition(arr, start, end);

      quickSort(arr, start, partitionIndex-1);
      quickSort(arr,partitionIndex+1, end);
    }
  }

  public int partition(int[] arr, int start, int end) {
    int pivot = arr[end];
    int i = start;

    for (int j = start; j < end; j++) {
      if (arr[j] < pivot) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
        i++;
      }
    }
    arr[end] = arr[i];
    arr[i] = pivot;

    return i;
  }
}

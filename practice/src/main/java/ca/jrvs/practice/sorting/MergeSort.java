package ca.jrvs.practice.sorting;

public class MergeSort {

  public static void mergeSort(int[] arr, int n) {
    if (n < 2) {
      return;
    }

    int mid = n/2;
    int[] l = new int[mid];
    int[] r = new int[n-mid];

    for (int i = 0; i < mid; i++) {
      l[i] = arr[i];
    }
    for (int j = mid; j < n; j++) {
      r[j-mid] = arr[j];
    }

    mergeSort(l, mid);
    mergeSort(r, n-mid);

    merge(arr, l, r, mid, n-mid);
  }

  public static void merge(int[] arr, int[] l, int[] r, int left, int right) {

    int i = 0, j = 0, k = 0;
    while (i < left && j < right) {
      if (l[i] <= r[j]) {
        arr[k++] = l[i++];
      } else {
        arr[k++] = r[j++];
      }
    }

    while (i < left) {
      arr[k++] = l[i++];
    }
    while (j < right) {
      arr[k++] = r[j++];
    }
  }
}

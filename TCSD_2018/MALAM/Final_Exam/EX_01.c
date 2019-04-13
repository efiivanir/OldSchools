#include<stdio.h>
#include<stdlib.h>

/**
 *  print_array
 *
 *  param: int n - array size
 *         arr[n] - array to print
 *
 *  return void
 */
void print_array(int arr[], int n) {
  int i;
  for (i = 0; i <= n; i++) {
    printf("%d ", arr[i]);
  }
  printf("\n");
}

/**
 *  swap
 *
 *  param: arr - the array that need swap
 *         int a,b - locations ar arr
 *
 *  return void
 */
void swap(int arr[], int a, int b) {
  int tmp;
  tmp = arr[a];
  arr[a] = arr[b];
  arr[b] = tmp;
}

/**
 *  place_pivot
 *
 *  move pivot so that :
 *                       Numbers lower are at left
 *                       Number highr are at right
 *
 *  param: arr 
 *         int low,hi - array low,hi sizees
 *         int direction - 1/0 sort ascending/descending   
 *
 *  return int pivot_place : new location of pivot
 */
int place_pivot(int arr[], int low, int hi, int direction) {
  int pivot_place = low;
  int i;
  for (i = low + 1; i <= hi; i++) {
    if (direction == 1) {
      if (arr[i] > arr[pivot_place]) {
        swap(arr, i, pivot_place + 1);
        swap(arr, pivot_place, pivot_place + 1);
      }
    } else if (direction == 0) {
      if (arr[i] < arr[pivot_place]) {
        swap(arr, i, pivot_place + 1);
        swap(arr, pivot_place, pivot_place + 1);
      }
    }
    pivot_place++;
  }
  return( pivot_place );
}

/**
 *  quick_sort
 *
 *  param: arr 
 *         int low,hi - array low,hi sizees
 *         int direction - 1/0 sort ascending/descending   
 *
 *  return int pivot_place : new location of pivot
 */
void quick_sort(int arr[], int low, int hi, int direction) {
  int pivot_place;
  pivot_place = place_pivot(arr, low, hi, direction);
  if (pivot_place - low > 1) {
    quick_sort(arr, low, pivot_place - 1, direction);
  }
  if (hi - pivot_place > 1) {
    quick_sort(arr, pivot_place + 1, hi, direction);
  }
}

/**
 *  main function
 *
 *  1. Read File
 *  2. Get array size
 *  3. Sort each line acording to direction
 *
 *  param: file name, file include:
 *                                 array size at the first line
 *                                 2D array at the other lines
 *    
 *  return int 0 if file open succed, else -1
 */
int main(int argc, char *argv[]) {
  FILE *fp;
  int *array;
  int n;
  int line_length;  /* number of value at each line */
  int line_reminder;
  int line_high;
  int direction;
  int line_number = 0;
  int num_count = 0;
  if (argc != 2) {
    printf("Usage : %s <file-name>\n", argv[0]);
    return -1;
  }

  fp = fopen(argv[1], "r");
  if (fp == NULL) {
        printf("Error opening file\n");
        return -1;
  }
  while (fscanf(fp, "%d", &n) != EOF) {
    if (num_count == 0) {
      // Get array size
      printf("Size is %d\n", n);
      line_length = n;
      line_high = line_length - 1;
      array = (int*) malloc(n *sizeof(int));
    } else {
      line_reminder = num_count % line_length;
      if (line_reminder == 0) {
        direction = line_number % 2;
        array[line_high] = n;
        printf("Direction %d Before:\n", direction);
        print_array(array, line_high);
        quick_sort(array, 0, line_high, direction);
        printf("Direction %d After:\n", direction);
        print_array(array, line_high);
        line_number++;
      } else {
        array[line_reminder - 1] = n;
      }
    }
    num_count++;
  }
  fclose(fp);
  free(array);
  return 0;
}



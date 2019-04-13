#include <stdio.h>
#include <stdbool.h>


/**
 *  print_array
 *
 *  print the sudoku table
 *
 *  param: int n - array size
 *         arr[n][n] - array to print
 *
 *  return void
 */
void print_array(int n, int arr[n][n]) {
  int row;
  int col;
  printf("-------------------\n");
  for (row = 0; row < n; row++) {
    for (col = 0; col < n; col++) {
      printf("%d ", arr[row][col]);
    }
    printf("\n");
  }
}

/**
 *  check_row_valid
 *
 *  check a given row if it valid and not include two or more same numbers
 *
 *  param: int n - array size
 *         arr[n][n] - current sudoku array
 *         row - row number
 *
 *  return bool - row is valid or not
 */
bool check_row_valid(int n, int arr[n][n], int row) {
  int col;
  int num;
  bool row_check[n + 1];
  for (col = 0; col <= n; col++) {
    row_check[col] = false;
  }
  for (col = 0; col < n; col++) {
    num = arr[row][col];
    if (row_check[num] == false) {
      row_check[num] = true;
    } else if ((num != 0) && (row_check[num] == true)) {
      return false;
    } else {
;
    }
  }
  return true;
}

/**
 *  check_col_valid
 *
 *  check a given column if it valid and not include two or more same numbers
 *
 *  param: int n - array size
 *         arr[n][n] - current sudoku array
 *         col - column number
 *
 *  return bool - column is valid or not
 */
bool check_col_valid(int n, int arr[n][n], int col) {
  int row;
  int num;
  bool col_check[n + 1];
  for (row = 0; row <= n; row++) {
    col_check[row] = false;
  }
  for (row = 0; row < n; row++) {
    num = arr[row][col];
    if (col_check[num] == false) {
      col_check[num] = true;
    } else if ((num != 0) && (col_check[num] == true)) {
      return false;
    } else {
;
    }
  }
  return true;
}

/**
 *  check_table_valid
 *
 *  Call check_col_valid & check_row_valid to check if initial table is valid
 *
 *  param: int n - array size
 *         arr[n][n] - current sudoku array
 *
 *  return bool - table is valid or not
 */
bool check_table_valid(int n, int arr[n][n]) {
  int row;
  int col;
  for (row = 0; row < n; row++) {
    if (!check_row_valid(n, arr, row)) {
      return false;
    }
  }
  for (col = 0; col < n; col++) {
    if (!check_col_valid(n, arr, col)) {
      return false;
    }
  }
  return true;
}


/**
 *  check_row
 *
 *  check if a row include the num we want to insert to solve the sudoku
 *
 *  param: int n - array size
 *         arr[n][n] - current sudoku array
 *         row - row number
 *
 *  return bool - row include the number or not
 */
bool check_row(int n, int arr[n][n], int row, int num) {
  int col;
  for (col = 0; col < n; col++) {
    if (arr[row][col] == num) {
      return true;
    }
  }
  return false;
}

/**
 *  check_col
 *
 *  check if a column include the num we want to insert to solve the sudoku
 *
 *  param: int n - array size
 *         arr[n][n] - current sudoku array
 *         col - column number
 *
 *  return bool - column include the number or not
 */
bool check_col(int n, int arr[n][n], int col, int num) {
  int row;
  for (row = 0; row < n; row++) {
    if (arr[row][col] == num) {
      return true;
    }
  }
  return false;
}

/**
 *  check_num
 *
 *  Call check_row & check_col for a given number befor assign number at a cell
 *
 *  param: int n - array size
 *         arr[n][n] - current sudoku array
 *         int row - row number
 *         int col - column number
 *         int num - number to assign
 *
 *  return bool - is column & row are valid to assiign the number
 */
bool check_num(int n, int arr[n][n], int row, int col, int num) {
  return !check_row(n, arr, row, num)
      && !check_col(n, arr, col, num);
}

/**
 *  check_zero
 *
 *  Check if cell is zero or not
 *  It update the row & col that will be checked.
 *
 *  param: int n - array size
 *         arr[n][n] - current sudoku array
 *         int row - row reference
 *         int col - column reference
 *
 *  return bool - if cell[row][col] is zero or not
 * 
 */
bool check_zero(int n, int arr[n][n], int *row, int *col) {
  for (*row = 0; *row < n; (*row)++) {
    for (*col = 0; *col < n; (*col)++) {
      if (arr[*row][*col] == 0) {
        return true;
      }
    }
  }
  return false;
}

/**
 *  sudoku_solver
 *
 *  1. Check if current cell is zero
 *  2. assign number and check if is valid
 *  3. recursive call if not assign
 *
 *  param: int n - array size
 *         arr[n][n] - current sudoku array
 *
 *  return bool - solved or not
 */
bool sudoku_solver(int n, int arr[n][n]) {	
  int row;
  int col;
  int num;
  if (!check_zero(n, arr, &row, &col)) {
    return true;
  }
  //printf("%d %d\n",row, col);
  for (num = 1; num <= n; num++) {
    if (check_num(n, arr, row, col, num)) {
      arr[row][col] = num;

      if (sudoku_solver(n, arr)) {
        return true;
      }
      arr[row][col] = 0;
    }
  }	
  return false;
}

/**
 *  main function
 *
 *  1. Read table from file
 *  2. Check if table is valid
 *  3. Solve table
 *
 *  param: file name, file include:
 *                                 table size at the first line
 *                                 table at the other lines
 *    
 *
 *  return int - 0/-1 if there is solution or not
 */
int main(int argc, char *argv[]) {
  FILE *fp;
  int n;
  int table_size;
  int row;
  int col;
  if (argc != 2) {
    printf("Usage : %s <table-file-name>\n", argv[0]);
    return -1;
  }

  // Read table from file
  fp = fopen(argv[1], "r");
  if (fp == NULL) {
        printf("Error opening file\n");
        return -1;
  }
  fscanf(fp, "%d", &n);
  table_size = n;
  int array[table_size][table_size];

  for (row = 0; row < table_size; row++) {
    for (col = 0; col < table_size; col++) {
      fscanf(fp, "%d", &n);
      array[row][col] = n;
    }
  }
  fclose(fp);

  printf("Original table:\n");
  print_array(table_size, array);
  // Check that original table is valid
  if (!check_table_valid(table_size, array)) {
    printf("No solution\n");
    return -1;
  }
  printf("Full table:\n");
  if (sudoku_solver(table_size, array)) {
    print_array(table_size, array);
    return 0;
  } else {
    printf("No solution\n");
    return -1;
  }	
}

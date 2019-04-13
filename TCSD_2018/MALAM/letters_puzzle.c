#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define N 6

void print_solution(int values[]) {
  char letters[] = {'O', 'W', 'T', 'R', 'U', 'F'};
  for (int i = 0; i < N; ++i) {
    printf("%c = %d ", letters[i], values[i]);
  }
  printf("\n");
}

bool check_value(int values[], int index, int value) {
    for (int i = 0; i < index; ++i) {
        if (values[i] == value) return false;
    }
    switch (index) {
      case 0:
        return true;
      case 1:
        return true;
      case 2:
        return ((value >= 5) &&
               ((2 * value + (2 * values[1]) / 10) % 10
                  == values[0]));
      case 3:
        return (2 * values[0]) % 10 == value;
      case 4:
        return (2 * values[1] + (2 * values[0]) / 10) % 10
                  == value;
      case 5:
        return (2 * values[2]) / 10 == value;


    }
    return false;
}

bool find_solution_aux(int values[], int index) {
    if (index == N) {
        print_solution(values);
        return true;
    }
    for (int i = 0; i <= 9; ++i) {
        if (!check_value(values, index, i))
            continue;
        values[index] = i;
        find_solution_aux(values, index + 1);
    }
    return false;
}

bool find_solution(int values[]) {
  return find_solution_aux(values, 0);
}

int main()
{
    int values[N];
    find_solution(values);
    return 0;
}

#include<stdio.h>
#include<stdlib.h>

/*
 *  function name :  power (recursive)
 *
 *  Detailed description : compute power of base acording to exponent.
 *                         For example : power(2,2) = 2^2 = 4
 *  param:
 *        base - The base number
 *        exp  - The exponent
 *
 *  return: int -  power.
 */
int power(int base, int exp) {
  if (exp == 0)
    return 1;
  return (base * power(base, exp - 1));
}

/*
 *  function name :  factorial (recursive)
 *
 *  Detailed description : n! - compute factorial of n
 *                         For example : factorial(3) = 3! = 3 * 2 * 1 = 6
 *  param:
 *        n - The base number
 *       
 *  return: int - factorial resault.
 */
int factorial(int n) {
  if (n < 2 )
    return n;
  return n * factorial(n - 1);
}

/*
 *  function name :  super_factorial (recursive)
 *
 *  Detailed description : compute product of factorials
 *    
 *  param:
 *        n - The base number
 *       
 *  return: int - super_factorial resault.
 */
int super_factorial(int n) {
  if (n == 1)
    return 1;
  return factorial(n) * super_factorial(n - 1);
}

/*
 *  function name :  super_factorial2 (recursive)
 *
 *  Detailed description : compute super factorial as product of (n - k)^(k + 1)
 *                         n * (n -1)^2 * (n - 2)^3 ... 1^n
 *  param:
 *        n - The base of super factorial.
 *        k - The exponent we use acording to the formula.
 *
 *  return: int -  complete or partial super_factorial.
 */
int super_factorial2(int n, int k) {
  if (n == 1)
    return 1;
  return power(n, k) * super_factorial2(n - 1, k + 1);
}


int main(int argc, char *argv[]) {
  if (argc != 3) {
    printf("Usage : %s <positive int> <1|2>\n", argv[0]);
    return -1;
  }
  int super_fact;
  int a = atoi(argv[1]);
  int b = atoi(argv[2]);

  if (b == 1) {
    super_fact = super_factorial(a);
  } else if (b == 2) {
    super_fact = super_factorial2(a, 1);
  } else {
    printf("Usage : %s <positive int> <1|2>\n", argv[0]);
    return -1;
  }
  printf("Superfactorial of %d option %d is %d\n", a, b, super_fact);

  
  return 0;
}

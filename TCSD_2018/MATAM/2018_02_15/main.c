#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "pair.h"

int toInt(ElementPtr e) {
    return *((int*) e);
}

// Make string from integer element
char* intLabel(ElementPtr e) {
    const int LABEL_SIZE = 20; // length of string describing integer
    char* s = (char*) malloc(LABEL_SIZE + 1);
    if (s == NULL)
        return NULL;
    sprintf(s,"%d",toInt(e));
    return s;
}

// Compare two integer elements
bool intCompare(ElementPtr e1, ElementPtr e2) {
    return toInt(e1) == toInt(e2);
}

// Clone an integer element
ElementPtr intCopy(ElementPtr e) {
    int* j = malloc(sizeof(int));
    if(j == NULL)
        return NULL;
    *j = toInt(e);
    return j;
}

// Free an integer element
void intFree(ElementPtr e) {
    free(e);
}

// Make string from integer element
char* strLabel(ElementPtr e) {
    char* e_str = (char*)e;
    char* s = (char*) malloc(strlen(e_str) + 1);
    if (s == NULL)
        return NULL;
    strcpy(s, e_str);
    return s;
}

// Compare two integer elements
bool strCompare(ElementPtr e1, ElementPtr e2) {
     return strcmp((char*)e1, (char*)e2) == 0;
}

// Clone an integer element
ElementPtr strCopy(ElementPtr e) {
    char* e_str = (char*)e;
    char* s = (char*) malloc(strlen(e_str) + 1);
    if (s == NULL)
        return NULL;
    strcpy(s, e_str);
    return s;
}

// Free an integer element
void strFree(ElementPtr e) {
    free(e);
}

int main()
{
    Pair p = PairCreate(strCompare, strCopy, strFree, intCompare, intCopy, intFree);
    int i = 9;
    char* str = "lala";
    PairSetFirst(p, str);
    PairSetSecond(p, &i);
    Pair p2 = CopyPair(p);
    if (PairEquals(p, p2)) {
        PairPrint(p2, strLabel, intLabel);
        printf(" equals to ");
        PairPrint(p, strLabel, intLabel);
        printf("\n");
    }
    ++i;
    PairSetSecond(p, &i);
    if (!PairEquals(p, p2)) {
        PairPrint(p2, strLabel, intLabel);
        printf(" not equals to ");
        PairPrint(p, strLabel, intLabel);
        printf("\n");
    } else {
        printf("I didn't crash! I just can't compare :(\n");
    }
    Pair p3 = PairCreate(intCompare, intCopy, intFree, intCompare, intCopy, intFree);
    PairSetFirst(p3, &i);
    if (!PairEquals(p3, p2)) {
        PairPrint(p2, strLabel, intLabel);
        printf(" not equals to ");
        PairPrint(p3, intLabel, intLabel);
        printf("\n");
    }


    PairDestroy(p);
    PairDestroy(p2);
    return 0;
}

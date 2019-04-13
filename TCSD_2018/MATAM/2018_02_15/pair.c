#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "pair.h"

struct pair_rec {
    CompareFunc cmp1;
    CopyFunc cpy1;
    FreeFunc free1;
    CompareFunc cmp2;
    CopyFunc cpy2;
    FreeFunc free2;
    ElementPtr e1;
    ElementPtr e2;
};

Pair PairCreate(CompareFunc cmp1, CopyFunc cpy1, FreeFunc free1,
                CompareFunc cmp2, CopyFunc cpy2, FreeFunc free2) {
    Pair p = (Pair)malloc(sizeof(struct pair_rec));
    if (p == NULL) return NULL;
    p->e1 = p->e2 = NULL;
    p->cmp1 = cmp1;
    p->cpy1 = cpy1;
    p->free1 = free1;
    p->cmp2 = cmp2;
    p->cpy2 = cpy2;
    p->free2 = free2;
    return p;
}

Pair CopyPair(Pair p) {
    Pair new_p = PairCreate(p->cmp1, p->cpy1, p->free1,
                      p->cmp2, p->cpy2, p->free2);
    new_p->e1 = p->cpy1(p->e1);
    new_p->e2 = p->cpy2(p->e2);
    return new_p;
}

void PairDestroy(Pair p) {
    p->free1(p->e1);
    p->free2(p->e2);
    free(p);
    return;
}

bool PairSetFirst(Pair p, ElementPtr e) {
    p->e1 = p->cpy1(e);
    return true;
}

bool PairSetSecond(Pair p, ElementPtr e) {
    p->e2 = p->cpy2(e);
    return true;
}

bool PairEquals(Pair p1, Pair p2) {
    if (p1->cmp1 != p2->cmp1  || p1->cmp2 != p2->cmp2)
      return false;
    if ((p1->e1 == NULL && p2->e1 != NULL) ||
        (p1->e1 != NULL && p2->e1 == NULL)  ||
        (p1->e2 == NULL && p2->e2 != NULL) ||
        (p1->e2 != NULL && p2->e2 == NULL))
            return false;
    if (((p1->e1 == NULL && p2->e1 == NULL) || p1->cmp1(p1->e1, p2->e1)) &&
         ((p1->e2 == NULL && p2->e2 == NULL) || p1->cmp2(p1->e2, p2->e2)))
      return true;
    return false;
}

void PairPrint(Pair p, LabelFunc print1, LabelFunc print2) {
    char* label1 = p->e1 ? print1(p->e1) : NULL;
    char* label2 = p->e2 ? print2(p->e2) : NULL;
    printf("[%s, %s]",
           label1 ? label1 : "UNDEF", label2 ? label2 : "UNDEF");
    free(label1);
    free(label2);
}

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#ifndef PAIR_H_INCLUDED
#define PAIR_H_INCLUDED

typedef void* ElementPtr;

typedef struct pair_rec* Pair;

typedef bool (*CompareFunc)(ElementPtr, ElementPtr);
typedef ElementPtr (*CopyFunc) (ElementPtr);
typedef void (*FreeFunc) (ElementPtr);
typedef char* (*LabelFunc) (ElementPtr);

Pair PairCreate(CompareFunc, CopyFunc, FreeFunc,
                CompareFunc, CopyFunc, FreeFunc);
Pair CopyPair(Pair);
void PairDestroy(Pair);
bool PairSetFirst(Pair, ElementPtr);
bool PairSetSecond(Pair, ElementPtr);
bool PairEquals(Pair, Pair);
void PairPrint(Pair, LabelFunc, LabelFunc);

#endif // PAIR_H_INCLUDED

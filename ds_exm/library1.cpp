#include "library1.h"
#include "Pardes.h"
#include "Statistics.h"

void* Init(int N){
  if (N<=0)
    return NULL;
  Statistics * DS = new Statistics(N);
  void* tmp = static_cast<void*>(DS);
  return tmp;
}

StatusType PlantTree(void *DS, int i, int j){
  if (!DS) return INVALID_INPUT;
  StatusType tmp = static_cast<Statistics*>(DS)->PlantTree(i, j);
  return tmp;
}

StatusType AddFruit(void *DS, int i, int j, int fruitID, int ripeRate){
  if (!DS) return INVALID_INPUT;
  StatusType tmp = static_cast<Statistics*>(DS)->AddFruit(i, j, fruitID, ripeRate);
  return tmp;
}

StatusType PickFruit(void *DS, int fruitID){
  if (!DS) return INVALID_INPUT;
  StatusType tmp = static_cast<Statistics*>(DS)->PickFruit(fruitID);
  return tmp;
}

StatusType RateFruit(void *DS, int fruitID, int ripeRate){
  if (!DS) return INVALID_INPUT;
   StatusType tmp = static_cast<Statistics*>(DS)->RateFruit(fruitID, ripeRate);
  return tmp;
}

StatusType GetBestFruit(void *DS, int i, int j, int *fruitID){
  if (!DS) return INVALID_INPUT;
  StatusType tmp = static_cast<Statistics*>(DS)->GetBestFruit(i, j, fruitID);
  return tmp;
}

StatusType GetAllFruitsByRate(void *DS, int i, int j, int **fruits, int **rate_fruits, int *numOfFruits){
  if (!DS) return INVALID_INPUT;
  StatusType tmp = static_cast<Statistics*>(DS)->GetAllFruitsByRate(i, j, fruits, rate_fruits, numOfFruits);
  return tmp;
}

StatusType UpdateRottenFruits(void *DS, int rottenBase, int rottenFactor){
  if (!DS) return INVALID_INPUT;
  StatusType tmp = static_cast<Statistics*>(DS)->UpdateRottenFruits(rottenBase, rottenFactor);
  return tmp;
  return SUCCESS;
}

void Quit(void** DS){
  delete ((Statistics*)*DS);
  *DS = NULL;
}


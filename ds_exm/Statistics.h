#ifndef STAT_H
#define STAT_H

#include "Pardes.h"
#include "library1.h"

class Statistics {
private:
  Pardes pardes;
  bool legalInput(int i, int j);
public:
  Statistics(int N);
  virtual ~Statistics();

  StatusType PlantTree(int i, int j);
  StatusType AddFruit(int i, int j, int fruitID, int ripeRate);
  StatusType PickFruit(int fruitID);
  StatusType RateFruit(int fruitID, int ripeRate);
  StatusType GetBestFruit(int i, int j, int *fruitID);
  StatusType GetAllFruitsByRate(int i, int j, int **fruits, int **rate_fruits, int *numOfFruits);
  StatusType UpdateRottenFruits(int , int );
};

#endif // STAT_H

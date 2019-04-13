#ifndef PARDES_H
#define PARDES_H

#include "AVL.h"
#include "Plant.h"

class Pardes {
  
private:
  int maxSize;
  AVL<Plant*> Plants;
  AVL<Fruit*> Fruits;
  bool legalInput(int i, int j);
  bool legalFruitID(int fruitID);
  
public:
  Pardes();
  virtual ~Pardes();
  void setMaxSize(int N);
  int getMaxSize();
  
  int setUniqKey(int i, int j);
  int get_i(int key);
  int get_j(int key);
  
  void AddPlant(int i, int j, Plant *plant);
  void AddFruit(int i, int j, Fruit *fruit);
  void RateFruit(int ID, int ripeness);
  Plant* GetPlant(int i, int j);
  Fruit* GetFruit(int fruitID);
  Fruit* GetAllFruits();
  Fruit* GetBestFruit(int i, int j, int *fruitID);
  void* GetAllFruitsByRate(int i, int j, int **fruits, int *numOfFruits);
  void RemovePlant(int i, int j);
  void RemoveFruit(int fruitID);
  
  void UpdateRottenFruits(int rottenBase, int rottenFactor);
  
  bool PlantExist(int i, int j);
  bool FruitExist(int fruitID);
};

#endif /* PARDES_H */

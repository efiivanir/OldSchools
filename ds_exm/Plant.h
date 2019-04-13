#ifndef PLANT_H
#define PLANT_H
#define FRUIT_ID_MAX 1000

#include "AVL.h"
#include "Fruit.h"

class Plant {
private:
  int i, j;

public:
  AVL<Fruit*> idSortedTree;
  AVL<Fruit*> rateSortedTree;
  Plant();
  virtual ~Plant();
  void AddFruit(Fruit* fruit);
  Fruit *GetFruit(int id);
  void RemoveFruit(int id);
  Fruit *GetBestFruit();
  Fruit **GetAllFruitsByRate();
  int GetSize();
  bool DoesExist(int fruitID);
  void setLocation(int i,int j);
  unsigned long setUniqRipeRate(unsigned long, unsigned long);
 
};

#endif /* PLANT_H */

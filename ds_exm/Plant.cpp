#include "Plant.h"
#include "PardesExp.h"

Plant::Plant() {
  i=-1;
  j=-1;
}

Plant::~Plant() {
  Fruit** fruits = idSortedTree.getSortedArray();  // O(k)
  int length = idSortedTree.GetSize();

  for (int i = 0; i < length; i++)
    delete (fruits[i]);

  delete[] (fruits);
}

void Plant::AddFruit(Fruit *fruit) {
  if (DoesExist(fruit->getID())) {
    throw Failure();
  }

  if(fruit->getID() >= FRUIT_ID_MAX) {
    throw FruitIdMaxValue();
  }
  
  idSortedTree.insert(fruit->getID(), fruit);
  
  unsigned long rateUnigVal = setUniqRipeRate(fruit->getRipeRate(),fruit->getID()); 
  rateSortedTree.insert(rateUnigVal,fruit);
  

}

Fruit* Plant::GetFruit(int id) {
  try {
    return idSortedTree.getByKey(id);
  } catch (KeyDoesNotExist& err) {
    return NULL;
  }
}

void Plant::RemoveFruit(int id) {
  try {
    Fruit* fruit = idSortedTree.getByKey(id);
    
    unsigned long rateUnigVal = setUniqRipeRate(fruit->getRipeRate(),id); 
    idSortedTree.remove(id);
    rateSortedTree.remove(rateUnigVal);
    delete (fruit);
  } catch (KeyDoesNotExist& err) {
  }
}

Fruit* Plant::GetBestFruit() {
  if (rateSortedTree.GetSize() < 1) {
    return NULL;
  }
  try {
    return rateSortedTree.getSmallest();
  } catch (KeyDoesNotExist& err) {
    return NULL;
  }
}

Fruit** Plant::GetAllFruitsByRate() {
  return rateSortedTree.getSortedArray();
}

int Plant::GetSize() {
  return rateSortedTree.GetSize();
}

bool Plant::DoesExist(int fruitID) {
  return idSortedTree.exists(fruitID);
}

void Plant::setLocation(int i, int j){
  this->i = i;
  this->j = j;
}

unsigned long Plant::setUniqRipeRate(unsigned long ripVal, unsigned long id) {
  return (ripVal * FRUIT_ID_MAX + id);
}
  



#include "Pardes.h"
#include "Plant.h"
#include "PardesExp.h"

Pardes::Pardes() {
  maxSize = 0;
}

Pardes::~Pardes() {
  Plant** plants = Plants.getSortedArray();  // O(k)
  int length = Plants.GetSize();

  for (int i = 0; i < length; i++)
    delete(plants[i]);

  delete[](plants);
}

void Pardes::setMaxSize(int N) {
  if (N >= 0)
    maxSize = N;
}

int Pardes::getMaxSize() {
  return maxSize;
}

int Pardes::setUniqKey(int i, int j) {
  int size = getMaxSize();
  int key = size * i + j;
  return key;
}

int Pardes::get_i(int key) {
  int size = getMaxSize();
  int i = key / size;
  return i;
}

int Pardes::get_j(int key) {
  int size = getMaxSize();
  int j = key % size;
  return j;
}


void Pardes::AddPlant(int i, int j, Plant* plant) {
  if (!legalInput(i, j)) {
    throw InvalidInput();
  }
  if (PlantExist(i, j)) {
    throw PlantAlreadyExist();
  } else {
    int key = setUniqKey(i,j);
    Plants.insert(key, plant);
  }
}

void Pardes::AddFruit(int i, int j, Fruit *fruit) {
  if (!fruit) {
    throw InvalidInput();
  }
  if (!legalInput(i, j)) {
    throw InvalidInput();
  }

  if (!PlantExist(i, j))
    throw PlantDoesNotExist();
  if (FruitExist(fruit->getID()))
    throw FruitAlreadyExist();

  fruit->setLocation(i,j);
  Plant* temp = GetPlant(i, j);
  
  temp->AddFruit(fruit);
  Fruits.insert(fruit->getID(), fruit);
}

Plant *Pardes::GetPlant(int i, int j) {
  if (!legalInput(i, j)) {
    throw InvalidInput();
  }
  if (!PlantExist(i, j)) {
    throw PlantDoesNotExist();
  }
  int key = setUniqKey(i,j);
  return Plants.getByKey(key);
}

Fruit *Pardes::GetFruit(int fruitID) {
  if (!legalFruitID(fruitID)) {
    throw InvalidInput();
  }
  Fruit *tmp = Fruits.getByKey(fruitID);

  return tmp;
}



Fruit* Pardes::GetBestFruit(int i, int j, int *fruitID) {
  if (!legalInput(i, j)) {
    throw InvalidInput();
  }
  Plant* plant = GetPlant(i, j);
  return plant->GetBestFruit();
}

void* Pardes::GetAllFruitsByRate(int i, int j, int **fruits, int *numOfFruits) {
  return GetPlant(i, j)->GetAllFruitsByRate();
}


void Pardes::RateFruit(int id, int rip) {
  if (id < 0 || rip <= 0) {
    throw InvalidInput();
  }
  try {
    Fruit *fruit = GetFruit(id);
#ifdef DEBUG
    cout << fruit->getID() << endl;
    cout << fruit->getRipeRate() << endl;
    
#endif
  
    Plant *plant = GetPlant(fruit->getLocation_i(),fruit->getLocation_j());
    Fruit *copy = new Fruit(*fruit);
    copy->setRipeRate(rip);
#ifdef DEBUG
    cout << copy->getID() << endl;
    cout << copy->getRipeRate() << endl;
#endif    
    plant->RemoveFruit(id);

    plant->AddFruit(copy);

    Fruits.remove(id);
    Fruits.insert(id, copy);

  } catch (KeyDoesNotExist& e) {
    throw Failure();
  }
}

void Pardes::RemoveFruit(int fruitID) {
  if (!legalFruitID(fruitID)) {
    throw InvalidInput();
  }
  try {
    Fruit* fruit = GetFruit(fruitID);
    Plant* plant = GetPlant(fruit->getLocation_i(), fruit->getLocation_j());

    plant->RemoveFruit(fruitID);
    Fruits.remove(fruitID);
  } catch (KeyDoesNotExist& e) {
    throw Failure();
  }
}

bool Pardes::legalInput(int i, int j) {
  int fieldSize = getMaxSize();
  if (i < 0 || j < 0 || i >= fieldSize || j >= fieldSize) {
    return false;
  }
  return true;
}

bool Pardes::legalFruitID(int fruitID) {
  if (fruitID <= 0) {
    return false;
  }
  return true;
}

bool Pardes::PlantExist(int i, int j) {
  int key = setUniqKey(i,j);
  return Plants.exists(key);
}

bool Pardes::FruitExist(int fruitID) {
  return Fruits.exists(fruitID);
}

void Pardes::UpdateRottenFruits(int rottenBase, int rottenFactor) {
  int id;
  int oldRate;
  int newRate;
  if (rottenBase<1 || rottenFactor<1) {
    throw InvalidInput();
  }
  
  Plant** plantsArray = Plants.getSortedArray();

  for (int i = 0; i < Plants.GetSize(); i++) {
    int Idlength = plantsArray[i]->idSortedTree.GetSize();
    if (Idlength == 0) {
      return;
    }
    Fruit **fruits = plantsArray[i]->idSortedTree.getSortedArray();
    for (int i = 0; i < Idlength; i++) {
      id = fruits[i]->getID();
      if( (id % rottenBase) == 0) {
        oldRate = fruits[i]->getRipeRate();
        newRate = oldRate * rottenFactor ;
#ifdef DEBUG
        cout << oldRate << " " << newRate << endl;
#endif
        RateFruit(id,newRate);
      }
    }
  }
  delete[](plantsArray);
  return;
}






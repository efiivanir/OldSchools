#include "Statistics.h"
#include "PardesExp.h"
#include "Fruit.h"
#include "Plant.h"
#include "Pardes.h"

Statistics::Statistics(int N) {
  pardes.setMaxSize(N);
}

Statistics::~Statistics() {}

StatusType Statistics::PlantTree(int i, int j) {
  Plant* plant = NULL;
  try {
    plant = new Plant();
    plant->setLocation(i, j);
    pardes.AddPlant(i, j, plant);
  } catch (InvalidInput& e) {
    delete plant;
    return INVALID_INPUT;
  } catch (OutOfMemory& e) {
    delete plant;
    return ALLOCATION_ERROR;
  } catch (Failure& e) {
    delete plant;
    return FAILURE;
  } catch (PlantAlreadyExist& e) {
    delete plant;
    return FAILURE;
  } catch (std::bad_alloc& err){
    return ALLOCATION_ERROR;
  }
  return SUCCESS;
}

StatusType Statistics::AddFruit(int i, int j, int fruitID, int ripeRate) {
  Fruit* fruit = NULL;

  try {
    fruit = new Fruit(fruitID, ripeRate);
    pardes.AddFruit(i, j, fruit);
  } catch (InvalidInput& e) {
    delete fruit;
    return INVALID_INPUT;
  } catch (OutOfMemory& e) {
    delete fruit;
    return ALLOCATION_ERROR;
  } catch (Failure& e) {
    delete fruit;
    return FAILURE;
  } catch (FruitAlreadyExist& e) {
    delete fruit;
    return FAILURE;
  } catch (PlantDoesNotExist& e) {
    delete fruit;
    return FAILURE;
  }

  return SUCCESS;
}

StatusType Statistics::PickFruit(int fruitID) {
  try {
    pardes.RemoveFruit(fruitID);
  } catch (InvalidInput& e) {
    return INVALID_INPUT;
  } catch (OutOfMemory& e) {
    return ALLOCATION_ERROR;
  } catch (Failure& e) {
    return FAILURE;
  } catch (std::bad_alloc& err){
    return ALLOCATION_ERROR;
  }
  return SUCCESS;
}

StatusType Statistics::RateFruit(int fruitID, int ripeRate) {
  try {
    pardes.RateFruit(fruitID, ripeRate);
  } catch (InvalidInput& e) {
    return INVALID_INPUT;
  } catch (OutOfMemory& e) {
    return ALLOCATION_ERROR;
  } catch (Failure& e) {
    return FAILURE;
  } catch (FruitDoesNotExist& e) {
    return FAILURE;
  } catch (std::bad_alloc& err){
    return ALLOCATION_ERROR;
  }
  return SUCCESS;
}

StatusType Statistics::GetBestFruit(int i, int j, int *fruitID) {
  try {
    Fruit* fruit = pardes.GetBestFruit(i, j, fruitID);
    if (fruit)
      *fruitID = fruit->getID();
    else
      *fruitID = -1;
  } catch (InvalidInput& e) {
    return INVALID_INPUT;
  } catch (OutOfMemory& e) {
    return ALLOCATION_ERROR;
  } catch (Failure& e) {
    return FAILURE;
  } catch (PlantDoesNotExist& e) {
    return FAILURE;
  } catch (std::bad_alloc& err){
    return ALLOCATION_ERROR;
  }
  return SUCCESS;
}

StatusType Statistics::GetAllFruitsByRate(int i, int j, int **fruits,int **rate_fruits,int *numOfFruits) {
  try {

    Plant *plant = pardes.GetPlant(i, j);
    int size = plant->GetSize();
    *numOfFruits = size;
    *fruits = (int*) malloc(sizeof(int) * (size));
    *rate_fruits = (int*) malloc(sizeof(int) * (size));
    
    Fruit** temp = plant->GetAllFruitsByRate();
    for (int i = 0; i < size; i++) {
      (*fruits)[i] = temp[i]->getID();
      (*rate_fruits)[i] = temp[i]->getRipeRate();
    }
    delete[](temp);
  } catch (InvalidInput& e) {
    return INVALID_INPUT;
  } catch (OutOfMemory& e) {
    return ALLOCATION_ERROR;
  } catch (Failure& e) {
    return FAILURE;
  } catch (PlantDoesNotExist& e) {
    return FAILURE;
  } catch (std::bad_alloc& err){
    return ALLOCATION_ERROR;
  }
  return SUCCESS;
}

StatusType Statistics::UpdateRottenFruits(int rottenBase, int rottenFactor) {
  try {
    
    pardes.UpdateRottenFruits(rottenBase, rottenFactor);
  } catch (InvalidInput& e) {
    return INVALID_INPUT;
  } catch (OutOfMemory& e) {
    return ALLOCATION_ERROR;
  } catch (Failure& e) {
    return FAILURE;
  } catch (std::bad_alloc& err){
    return ALLOCATION_ERROR;
  }
  return SUCCESS;
}

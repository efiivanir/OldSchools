#include "Fruit.h"
#include "PardesExp.h"

Fruit::Fruit(int fruidID, int ripeRate) {
  if (fruidID <= 0 || ripeRate <= 0) throw InvalidInput();
  i = -1;
  j = -1;
  this->id = fruidID;
  this->ripeRate = ripeRate;
}

Fruit::~Fruit() {}

int Fruit::getID() {
  return this->id;
}

int Fruit::getRipeRate() {
  return this->ripeRate;
}

void Fruit::setRipeRate(int ripeRate) {
  this->ripeRate = ripeRate;
}


void Fruit::setLocation(int i,int j) {
  this->i = i;
  this->j = j;
}

int Fruit::getLocation_i() {
  return this->i;
}

int Fruit::getLocation_j() {
  return this->j;
}

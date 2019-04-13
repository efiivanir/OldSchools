#ifndef FRUIT_H
#define FRUIT_H

class Fruit {
private:
  int id;
  int ripeRate;
  int i, j;
  int rottenBase;
  int rottenFactor;
public:
  Fruit(int fruitID, int ripeRate = 1);
  virtual ~Fruit();
  int getID();
  int getRipeRate();
  void setRipeRate(int ripeRate);
  void setLocation(int i,int j);
  int getLocation_i();
  int getLocation_j();
};

#endif  // FRUIT_H


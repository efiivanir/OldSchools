#ifndef EXP_H
#define EXP_H
#include <iostream>
#include <exception>
using namespace std;

class FruitIdMaxValue:public exception {
 public:
  const char* what() {
    return "FruitIdMaxValue\n";
  }
};

class KeyAlreadyExist:public exception {
 public:
  const char* what() {
    return "KeyAlreadyExist\n";
  }
};

class KeyDoesNotExist:public exception {
 public:
  const char* what() {
    return "KeyDoesNotExist\n";
  }
};

class IllegalInput:public exception {
 public:
  const char* what() {
    return "IllegalInput \n";
  }
};

class OutOfMemory:public exception {
 public:
  const char* what() {
    return "OutOfMemory\n";
  }
};

class InvalidInput:public exception {
 public:
  const char* what() {
    return "InvalidInput\n";
  }
};

class Failure:public exception {
 public:
  const char* what() {
    return "Failure\n";
  }
};

class PlantDoesNotExist:public exception {
 public:
  const char* what() {
    return "PlantDoesNotExist\n";
  }
};

class PlantAlreadyExist:public exception {
 public:
  const char* what() {
    return "PlantAlreadyExist\n";
  }
};

class FruitDoesNotExist:public exception {
 public:
  const char* what() {
    return "FruitDoesNotExist\n";
  }
};

class FruitAlreadyExist:public exception {
 public:
  const char* what() {
    return "FruitAlreadyExist\n";
  }
};
#endif //EXP_H_

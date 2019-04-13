CC=g++
RM=rm
MV=mv

D=NULL
CFLAGS= -ansi -pedantic -Wall -g -lm -std=c++14 -D$(D)

SRC =  Pardes.cpp Plant.cpp Fruit.cpp Statistics.cpp library1.cpp main.cpp
OBJS = $(SRC:.cpp=.o)
TARGET=pardes

.PHONY: depend clean

all:    $(TARGET)
	@echo  Create Pardes program

$(TARGET): $(OBJS) 
	$(CC) $(CFLAGS)  -o $(TARGET) $(OBJS)
	$(MV) $(TARGET) bin/
	$(MV) $(OBJS) bin/

.cpp.o:
	$(CC) $(CFLAGS) -c $<  -o $@

.PHONY: clean

clean:
	$(RM) -f $(TARGET) *.o
	$(RM) -f bin/$(TARGET) bin/*.o


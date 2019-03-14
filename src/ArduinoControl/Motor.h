#ifndef MOTOR_H
#define MOTOR_H

#include <Arduino.h>

class Motor{
  private:
    int A1A;
    int A1B;

  public:
    Motor(int,int);
    void setA1A(int);
    void setA1B(int);
    int getA1A();
    int getA1B();
    void avanzar();
    void retroceder();  
    void detener();
};

#endif

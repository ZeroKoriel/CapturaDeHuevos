#ifndef DIRECCION_H
#define DIRECCION_H

#include <Arduino.h>
#include "Motor.h"

class Direccion {
  private:
    Motor *motorDerecho;
    Motor *motorIzquierdo;
  public:
    Direccion(int=0,int=0,int=0,int=0);
    virtual void moverse();
    virtual void detener();
    Motor* getMotorDerecho();
    Motor* getMotorIzquierdo();
};

#endif

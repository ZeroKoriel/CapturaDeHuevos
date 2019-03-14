#ifndef IZQUIERDA_H
#define IZQUIERDA_H

#include "Direccion.h"

class Izquierda : public Direccion {
  private:
    Motor *motorDerecho;
    Motor *motorIzquierdo;
  public:
    Izquierda(int,int,int,int);
    void moverse();
    void detener();
};

#endif

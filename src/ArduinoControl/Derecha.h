#ifndef DERECHA_H
#define DERECHA_H

#include "Direccion.h"

class Derecha : public Direccion {
  private:
    Motor *motorDerecho;
    Motor *motorIzquierdo;
  public:
    Derecha(int,int,int,int);
    void moverse();
    void detener();
};

#endif

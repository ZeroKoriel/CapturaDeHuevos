#ifndef ATRAS_H
#define ATRAS_H

#include "Direccion.h"

class Atras : public Direccion {
  private:
    Motor *motorDerecho;
    Motor *motorIzquierdo;
  public:
    Atras(int,int,int,int);
    void moverse();
    void detener();
};

#endif

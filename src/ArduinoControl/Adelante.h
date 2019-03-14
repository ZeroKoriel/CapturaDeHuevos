#ifndef ADELANTE_H
#define ADELANTE_H

#include "Direccion.h"
#include "Motor.h"

class Adelante : public Direccion {
    private:
      Motor *motorDerecho;
      Motor *motorIzquierdo;
    public:
    Adelante(int,int,int,int);
    void moverse();
    void detener();
};

#endif

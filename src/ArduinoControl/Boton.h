#ifndef BOTON_H
#define BOTON_H

#include<Arduino.h>

class Boton{
  public:
      void encender();
      void apagar();
      void setPingBoton(int);
      int getPin();
      void setEstado(int);
      int getEstado();
      void setEstadoAnterior(int);
      int getEstadoAnterior();
      int getLeido();
      void setLeido(int);
      Boton(int);
  private:
      int pin;
      int leido;
      int estado;
      int anterior;
};

#endif

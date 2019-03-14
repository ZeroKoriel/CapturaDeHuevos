#include "Atras.h"

Atras::Atras(int M1A1A, int M1A1B, int M2A1A, int M2A1B) : Direccion(M1A1A, M1A1B, M2A1A, M2A1B) {
 
}

void Atras::moverse() {
  getMotorDerecho()->retroceder();
  getMotorIzquierdo()->retroceder();
}

void Atras::detener() {
  getMotorDerecho()->detener();
  getMotorIzquierdo()->detener();
}

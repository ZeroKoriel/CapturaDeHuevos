#include "Direccion.h"

Direccion::Direccion(int M1A1A, int M1A1B, int M2A1A, int M2A1B) {
  this->motorDerecho = new Motor(M1A1A, M1A1B);
  this->motorIzquierdo = new Motor(M2A1A, M2A1B);
}

void Direccion::moverse(){
  
}

void Direccion::detener() {
  this->motorDerecho->detener();
  this->motorIzquierdo->detener();
}

Motor* Direccion::getMotorDerecho() {
  return this->motorDerecho;
}

Motor* Direccion::getMotorIzquierdo() {
  return this->motorIzquierdo;
}

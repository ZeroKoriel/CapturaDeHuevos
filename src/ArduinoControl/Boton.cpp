#include"Boton.h"

Boton::Boton(int pin) {
  this->pin = pin;
  this->estado = 0;  
  this->anterior = 0;
  this->leido = 0;
}

void Boton::encender(){
  this->estado = 1;
}

void Boton::apagar(){
  this->estado = 0;
}

void Boton::setPingBoton(int pingBoton){
  this->pin = pingBoton;  
}

int Boton::getPin(){
  return this->pin;  
}

void Boton::setEstado(int estado){
  this->estado = estado;  
}

int Boton::getEstado(){
  return this->estado;  
}

void Boton::setEstadoAnterior(int estadoAnterior){
  this->anterior = anterior;
}   

int Boton::getEstadoAnterior(){
  return this->anterior;
}

int Boton::getLeido() {
  return this->leido;
}

void Boton::setLeido(int leido) {
  this->leido = leido;  
}

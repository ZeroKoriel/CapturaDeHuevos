#include "Motor.h"

Motor::Motor(int A1A, int A1B) {
  this->A1A = A1A;
  this->A1B = A1B;

  pinMode(this->A1A, OUTPUT);
  pinMode(this->A1B, OUTPUT);
}

void Motor::setA1A(int A1A) {
  this->A1A = A1A;
}

void Motor::setA1B(int A1B) {
  this->A1B = A1B;
}

int Motor::getA1A() {
  return this->A1A;
}

int Motor::getA1B() {
  return this->A1B;
}

void Motor::avanzar() {
  digitalWrite(this->A1A, HIGH);
  digitalWrite(this->A1B, LOW);
}

void Motor::retroceder() {
  digitalWrite(this->A1A, LOW);
  digitalWrite(this->A1B, HIGH);
}

void Motor::detener() {
  digitalWrite(this->A1A, LOW);
  digitalWrite(this->A1B, LOW);
}

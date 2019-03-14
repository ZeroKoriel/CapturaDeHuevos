#include "Motor.h"
#include "Direccion.h"
#include "Adelante.h"
#include "Atras.h"
#include "Izquierda.h"
#include "Derecha.h"
#include "Boton.h"

Boton bAdelante(4);
Boton bDerecha(6);
Boton bAbajo(7);
Boton bIzquierda(5);
Boton bPlay(2);
Boton bStop(3);

int contadorDeInstrucciones = 0;
int tamanio = 15;
int correr = 0;
int d = 30;
int i = 0;

Direccion *vector[5];
int * valores;

void setup() {
  Serial.begin(9600);
  Direccion *a = new Adelante(10,11,8,9); 
  Direccion *b = new Derecha(10,11,8,9);
  Direccion *c = new Atras(10,11,8,9);
  Direccion *d = new Izquierda(10,11,8,9); 
  
  vector[0] = NULL;
  vector[1] = a;
  vector[2] = b;
  vector[3] = c;
  vector[4] = d;

  valores = calloc(15, sizeof(int));
  
}

void loop() {
  bPlay.setLeido(digitalRead(bPlay.getPin()));
    if (bPlay.getLeido() == HIGH) {
      delay(d);
      correr = 1;
    }
    bStop.setLeido(digitalRead(bStop.getPin()));
    if (bStop.getLeido() == HIGH) {
     delay(d);
     Serial.println("Parar");
     correr = 0;
     parar();
   }
  if (correr == 1) {
    Serial.println("*");
    moverse(valores[i]);
    ++i;
    if (i == contadorDeInstrucciones) {
      parar();
      i = 0;
      correr = 0;
      contadorDeInstrucciones = 0; 
    }
  } else {
    delay(200);
    bAdelante.setLeido(digitalRead(bAdelante.getPin()));
    if (bAdelante.getLeido() == HIGH) {
      ++contadorDeInstrucciones;
      Serial.println(contadorDeInstrucciones,DEC);
      delay(d);
      insertar(1);
    }

    bDerecha.setLeido(digitalRead(bDerecha.getPin()));
    if (bDerecha.getLeido() == HIGH) {
      ++contadorDeInstrucciones;
      Serial.println(contadorDeInstrucciones,DEC);
      delay(d);
      insertar(2);
    }

    bAbajo.setLeido(digitalRead(bAbajo.getPin()));
    if (bAbajo.getLeido() == HIGH) {
      ++contadorDeInstrucciones;
      Serial.println(contadorDeInstrucciones,DEC);
      delay(d);
      insertar(3);
    }

    bIzquierda.setLeido(digitalRead(bIzquierda.getPin()));
    if (bIzquierda.getLeido() == HIGH) {
      ++contadorDeInstrucciones;
      Serial.println(contadorDeInstrucciones,DEC);
      delay(d);
      insertar(4);
    }
  }
}

void insertar(int opcion) {
  if (contadorDeInstrucciones > tamanio) {
    redimensionar();
  } else {
    valores[contadorDeInstrucciones - 1] = opcion;
  }
}

void redimensionar() {
  tamanio *= 2;
  int *temp = realloc(valores, tamanio * sizeof(int));

  if (temp == NULL) {
  } else {
    memmove(valores,temp, tamanio * sizeof(int));
  }
}

void moverse(int i) {
  if(vector[i] == NULL)
    parar();
  else
    vector[i]->moverse();
  if (i == 2 || i == 4)
    delay(450);
  else 
    delay(1000);
}

void parar() {
  for (int j = 1; j < 5; j++) {
    vector[j]->detener();
  }
}

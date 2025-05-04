package aed;

import java.util.NoSuchElementException;

public class ListaEnlazada<T> implements Secuencia<T> {
  private class Nodo {
    T elemento;
    Nodo siguiente;
    Nodo anterior;

    Nodo(T elem) {
      this.elemento = elem;
      this.siguiente = null;
      this.anterior = null;
    }
  }

  private Nodo cabeza;
  private Nodo cola;
  private int tamaño;

  public ListaEnlazada() {
    cabeza = null;
    cola = null;
    tamaño = 0;
  }

  @Override
  public int longitud() {
    return tamaño;
  }

  @Override
  public void agregarAdelante(T elem) {
    Nodo nodo = new Nodo(elem);
    nodo.siguiente = cabeza;
    if (cabeza != null) {
      cabeza.anterior = nodo;
    }
    cabeza = nodo;
    if (cola == null) {
      cola = nodo;
    }
    tamaño++;
  }

  @Override
  public void agregarAtras(T elem) {
    Nodo nodo = new Nodo(elem);
    nodo.anterior = cola;
    if (cola != null) {
      cola.siguiente = nodo;
    }
    cola = nodo;
    if (cabeza == null) {
      cabeza = nodo;
    }
    tamaño++;
  }

  @Override
  public T obtener(int indice) {
    checkIndice(indice);
    Nodo actual = cabeza;
    for (int i = 0; i < indice; i++) {
      actual = actual.siguiente;
    }
    return actual.elemento;
  }

  @Override
  public void eliminar(int indice) {
    checkIndice(indice);
    if (tamaño == 1) {
      cabeza = null;
      cola = null;
    } else if (indice == 0) {
      cabeza = cabeza.siguiente;
      cabeza.anterior = null;
    } else if (indice == tamaño - 1) {
      cola = cola.anterior;
      cola.siguiente = null;
    } else {
      Nodo actual = cabeza;
      for (int i = 0; i < indice; i++) {
        actual = actual.siguiente;
      }
      actual.anterior.siguiente = actual.siguiente;
      actual.siguiente.anterior = actual.anterior;
    }
    tamaño--;
  }

  @Override
  public void modificarPosicion(int indice, T valor) {
    checkIndice(indice);
    Nodo actual = cabeza;
    for (int i = 0; i < indice; i++) {
      actual = actual.siguiente;
    }
    actual.elemento = valor;
  }

  public ListaEnlazada(ListaEnlazada<T> lista) {
    this();
    Iterador<T> it = lista.iterador();
    while (it.haySiguiente()) {
      agregarAtras(it.siguiente());
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    Nodo actual = cabeza;
    while (actual != null) {
      sb.append(actual.elemento);
      if (actual.siguiente != null) {
        sb.append(", ");
      }
      actual = actual.siguiente;
    }
    sb.append(']');
    return sb.toString();
  }

  private void checkIndice(int indice) {
    if (indice < 0 || indice >= tamaño) {
      throw new IndexOutOfBoundsException("Índice: " + indice + ", Tamaño: " + tamaño);
    }
  }

  private class ListaIterador implements Iterador<T> {
    private Nodo ptrNext;
    private Nodo ptrPrev;

    public ListaIterador() {
      this.ptrNext = cabeza;
      this.ptrPrev = null;
    }

    @Override
    public boolean haySiguiente() {
      return ptrNext != null;
    }

    @Override
    public boolean hayAnterior() {
      return ptrPrev != null;
    }

    @Override
    public T siguiente() {
      if (!haySiguiente()) {
        throw new NoSuchElementException();
      }
      T elem = ptrNext.elemento;
      ptrPrev = ptrNext;
      ptrNext = ptrNext.siguiente;
      return elem;
    }

    @Override
    public T anterior() {
      if (!hayAnterior()) {
        throw new NoSuchElementException();
      }
      T elem = ptrPrev.elemento;
      ptrNext = ptrPrev;
      ptrPrev = ptrPrev.anterior;
      return elem;
    }
  }

  public Iterador<T> iterador() {
    return new ListaIterador();
  }
}

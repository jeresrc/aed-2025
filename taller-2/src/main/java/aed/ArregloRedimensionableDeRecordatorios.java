package aed;

public class ArregloRedimensionableDeRecordatorios {
  private Recordatorio[] arreglo;
  private int size;

  public ArregloRedimensionableDeRecordatorios() {
    arreglo = new Recordatorio[10];
    size = 0;
  }

  public int longitud() {
    return size;
  }

  public void agregarAtras(Recordatorio i) {
    if (size == arreglo.length) {
      redimensionar();
    }
    arreglo[size] = i;
    size++;
  }

  private void redimensionar() {
    Recordatorio[] nuevo = new Recordatorio[arreglo.length * 2];
    for (int i = 0; i < size; i++) {
      nuevo[i] = arreglo[i];
    }
    arreglo = nuevo;
  }

  public Recordatorio obtener(int i) {
    if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
    return arreglo[i];
  }

  public void quitarAtras() {
    if (size > 0) {
      arreglo[size - 1] = null;
      size--;
    }
  }

  public void modificarPosicion(int indice, Recordatorio valor) {
    if (indice < 0 || indice >= size) throw new IndexOutOfBoundsException();
    arreglo[indice] = valor;
  }

  public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
    this.arreglo = new Recordatorio[vector.arreglo.length];
    this.size = vector.size;
    for (int i = 0; i < size; i++) {
      this.arreglo[i] = vector.arreglo[i];
    }
  }

  public ArregloRedimensionableDeRecordatorios copiar() {
    return new ArregloRedimensionableDeRecordatorios(this);
  }
}

package aed;

public class Fecha {
  private int dia;
  private int mes;

  public Fecha(int dia, int mes) {
    this.dia = dia;
    this.mes = mes;
  }

  public Fecha(Fecha fecha) {
    this.dia = fecha.dia;
    this.mes = fecha.mes;
  }

  public Integer dia() {
    return dia;
  }

  public Integer mes() {
    return mes;
  }

  @Override
  public String toString() {
    return dia + "/" + mes;
  }

  @Override
  public boolean equals(Object otra) {
    if (this == otra) return true;
    if (otra == null || !(otra instanceof Fecha)) return false;
    Fecha f = (Fecha) otra;
    return this.dia == f.dia && this.mes == f.mes;
  }

  public void incrementarDia() {
    if (dia < diasEnMes(mes)) {
      dia++;
    } else {
      dia = 1;
      if (mes == 12) {
        mes = 1;
      } else {
        mes++;
      }
    }
  }

  private int diasEnMes(int mes) {
    int dias[] = {
      // ene, feb, mar, abr, may, jun
      31, 28, 31, 30, 31, 30,
      // jul, ago, sep, oct, nov, dic
      31, 31, 30, 31, 30, 31
    };
    return dias[mes - 1];
  }
}

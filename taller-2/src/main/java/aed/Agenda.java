package aed;

public class Agenda {
  private Fecha fechaActual;
  private ArregloRedimensionableDeRecordatorios recordatorios;

  public Agenda(Fecha fechaActual) {
    this.fechaActual = new Fecha(fechaActual);
    recordatorios = new ArregloRedimensionableDeRecordatorios();
  }

  public void agregarRecordatorio(Recordatorio recordatorio) {
    recordatorios.agregarAtras(recordatorio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(fechaActual.toString());
    sb.append("\n");
    sb.append("=====\n");
    for (int i = 0; i < recordatorios.longitud(); i++) {
      Recordatorio r = recordatorios.obtener(i);
      if (r.fecha().equals(fechaActual)) {
        sb.append(r.toString());
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  public void incrementarDia() {
    fechaActual.incrementarDia();
  }

  public Fecha fechaActual() {
    return new Fecha(fechaActual);
  }
}

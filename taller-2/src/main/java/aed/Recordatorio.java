package aed;

public class Recordatorio {
    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = mensaje;
        this.fecha = new Fecha(fecha);
        this.horario = horario;
    }

    public Horario horario() {
        return horario;
    }

    public Fecha fecha() {
        // Devolvemos una copia para evitar aliasing.
        return new Fecha(fecha);
    }

    public String mensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return mensaje + " @ " + fecha.toString() + " " + horario.toString();
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro)
            return true;
        if (otro == null || !(otro instanceof Recordatorio))
            return false;
        Recordatorio r = (Recordatorio) otro;
        return this.mensaje.equals(r.mensaje) && this.fecha.equals(r.fecha) && this.horario.equals(r.horario);
    }
}

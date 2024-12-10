package DATABASE;

import java.util.Objects;

public class Entrenamiento {
    private int id;
    private String nombre;
    private int duracion;
    private String nivel;

    public Entrenamiento() {
    }

    public Entrenamiento(int duracion, int id, String nivel, String nombre) {
        this.duracion = duracion;
        this.id = id;
        this.nivel = nivel;
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" +
                "duracion=" + duracion +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nivel='" + nivel + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrenamiento that = (Entrenamiento) o;
        return id == that.id && duracion == that.duracion && Objects.equals(nombre, that.nombre) && Objects.equals(nivel, that.nivel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, duracion, nivel);
    }
}

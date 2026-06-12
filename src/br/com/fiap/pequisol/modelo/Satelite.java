package br.com.fiap.pequisol.modelo;

public abstract class Satelite {

    private int id;
    private String nome;
    private double altitudeKm;
    private String status;

    public Satelite(int id, String nome, double altitudeKm, String status) {
        this.id = id;
        this.nome = nome;
        setAltitudeKm(altitudeKm);
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getAltitudeKm() {
        return altitudeKm;
    }

    public void setAltitudeKm(double altitudeKm) {
        if (altitudeKm <= 0) {
            throw new IllegalArgumentException(
                    "Altitude inválida: deve ser maior que zero (valor informado: " + altitudeKm + ").");
        }
        this.altitudeKm = altitudeKm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Nome: " + nome
                + " | Altitude: " + altitudeKm + " km"
                + " | Status: " + status;
    }
}

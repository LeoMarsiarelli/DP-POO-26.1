package br.com.fiap.pequisol.modelo;

import br.com.fiap.pequisol.operacao.Operavel;

public class CubeSat extends Satelite implements Operavel {

    private double massaKg;

    public CubeSat(int id, String nome, double altitudeKm, String status, double massaKg) {
        super(id, nome, altitudeKm, status);
        setMassaKg(massaKg);
    }

    public double getMassaKg() {
        return massaKg;
    }

    public void setMassaKg(double massaKg) {
        if (massaKg <= 0) {
            throw new IllegalArgumentException(
                    "Massa inválida: deve ser maior que zero (valor informado: " + massaKg + ").");
        }
        this.massaKg = massaKg;
    }

    @Override
    public double calcularConsumoEnergia() {
        return 5.0 + massaKg * 0.8;
    }

    @Override
    public String toString() {
        return "[CubeSat] " + super.toString()
                + " | Massa: " + massaKg + " kg"
                + " | Consumo: " + calcularConsumoEnergia() + " W";
    }
}

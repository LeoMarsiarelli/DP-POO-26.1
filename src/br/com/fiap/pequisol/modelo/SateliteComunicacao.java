package br.com.fiap.pequisol.modelo;

import br.com.fiap.pequisol.operacao.Operavel;

public class SateliteComunicacao extends Satelite implements Operavel {

    private double larguraBandaMbps;

    public SateliteComunicacao(int id, String nome, double altitudeKm, String status, double larguraBandaMbps) {
        super(id, nome, altitudeKm, status);
        setLarguraBandaMbps(larguraBandaMbps);
    }

    public double getLarguraBandaMbps() {
        return larguraBandaMbps;
    }

    public void setLarguraBandaMbps(double larguraBandaMbps) {
        if (larguraBandaMbps <= 0) {
            throw new IllegalArgumentException(
                    "Largura de banda inválida: deve ser maior que zero (valor informado: " + larguraBandaMbps + ").");
        }
        this.larguraBandaMbps = larguraBandaMbps;
    }

    @Override
    public double calcularConsumoEnergia() {
        return 30.0 + larguraBandaMbps * 1.5;
    }

    @Override
    public String toString() {
        return "[Comunicacao] " + super.toString()
                + " | Largura de banda: " + larguraBandaMbps + " Mbps"
                + " | Consumo: " + calcularConsumoEnergia() + " W";
    }
}

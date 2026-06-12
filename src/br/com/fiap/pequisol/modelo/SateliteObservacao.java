package br.com.fiap.pequisol.modelo;

import br.com.fiap.pequisol.operacao.Operavel;

public class SateliteObservacao extends Satelite implements Operavel {

    private double resolucaoMetros;

    public SateliteObservacao(int id, String nome, double altitudeKm, String status, double resolucaoMetros) {
        super(id, nome, altitudeKm, status);
        setResolucaoMetros(resolucaoMetros);
    }

    public double getResolucaoMetros() {
        return resolucaoMetros;
    }

    public void setResolucaoMetros(double resolucaoMetros) {
        if (resolucaoMetros <= 0) {
            throw new IllegalArgumentException(
                    "Resolução inválida: deve ser maior que zero (valor informado: " + resolucaoMetros + ").");
        }
        this.resolucaoMetros = resolucaoMetros;
    }

    @Override
    public double calcularConsumoEnergia() {
        return 50.0 + 1000.0 / resolucaoMetros;
    }

    @Override
    public String toString() {
        return "[Observacao] " + super.toString()
                + " | Resolução: " + resolucaoMetros + " m"
                + " | Consumo: " + calcularConsumoEnergia() + " W";
    }
}

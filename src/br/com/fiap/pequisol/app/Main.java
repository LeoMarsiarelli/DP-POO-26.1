package br.com.fiap.pequisol.app;

import br.com.fiap.pequisol.modelo.CubeSat;
import br.com.fiap.pequisol.modelo.Satelite;
import br.com.fiap.pequisol.modelo.SateliteComunicacao;
import br.com.fiap.pequisol.modelo.SateliteObservacao;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Main {

    private static final String MENU =
            "MENU DO SISTEMA\n"
                    + "Sistema Pequi-Sol — Catálogo de Satélites\n"
                    + "1. Registrar novo satélite\n"
                    + "2. Pesquisar por ID\n"
                    + "3. Alterar status\n"
                    + "4. Excluir\n"
                    + "5. Listar todos\n"
                    + "6. Sair\n"
                    + "Escolha uma opção:";

    private static final ArrayList<Satelite> CATALOGO = new ArrayList<>();

    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            try {
                String entrada = JOptionPane.showInputDialog(null, MENU,
                        "Pequi-Sol", JOptionPane.QUESTION_MESSAGE);

                if (entrada == null) {
                    continue;
                }

                int opcao = Integer.parseInt(entrada.trim());

                switch (opcao) {
                    case 1:
                        registrarSatelite();
                        break;
                    case 2:
                        pesquisarPorId();
                        break;
                    case 3:
                        alterarStatus();
                        break;
                    case 4:
                        excluirSatelite();
                        break;
                    case 5:
                        listarTodos();
                        break;
                    case 6:
                        executando = false;
                        JOptionPane.showMessageDialog(null,
                                "Encerrando o sistema Pequi-Sol. Até a próxima missão!",
                                "Saída", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,
                                "Opção inválida. Escolha um número entre 1 e 6.",
                                "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Entrada inválida: digite um número.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro inesperado: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void registrarSatelite() {
        String menuTipo =
                "Selecione o tipo de satélite:\n"
                        + "1. CubeSat\n"
                        + "2. Satélite de Comunicação\n"
                        + "3. Satélite de Observação";

        String entradaTipo = JOptionPane.showInputDialog(null, menuTipo,
                "Novo Satélite", JOptionPane.QUESTION_MESSAGE);
        if (entradaTipo == null) {
            return;
        }
        int tipo = Integer.parseInt(entradaTipo.trim());
        if (tipo < 1 || tipo > 3) {
            JOptionPane.showMessageDialog(null,
                    "Tipo inválido. Escolha 1, 2 ou 3.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String entradaId = JOptionPane.showInputDialog(null, "Informe o ID do satélite:",
                "Novo Satélite", JOptionPane.QUESTION_MESSAGE);
        if (entradaId == null) {
            return;
        }
        int id = Integer.parseInt(entradaId.trim());

        if (buscarPorId(id) != null) {
            JOptionPane.showMessageDialog(null,
                    "Já existe um satélite cadastrado com o ID " + id + ". Cadastro não realizado.",
                    "ID duplicado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nome = JOptionPane.showInputDialog(null, "Informe o nome do satélite:",
                "Novo Satélite", JOptionPane.QUESTION_MESSAGE);
        if (nome == null) {
            return;
        }
        nome = nome.trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Nome não pode ser vazio.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String entradaAltitude = JOptionPane.showInputDialog(null, "Informe a altitude orbital (km):",
                "Novo Satélite", JOptionPane.QUESTION_MESSAGE);
        if (entradaAltitude == null) {
            return;
        }
        double altitudeKm = Double.parseDouble(entradaAltitude.trim().replace(",", "."));

        String status = pedirStatusValido("Informe o status (operacional, manutenção ou desativado):");
        if (status == null) {
            return;
        }

        Satelite novo;
        switch (tipo) {
            case 1: {
                String entradaMassa = JOptionPane.showInputDialog(null, "Informe a massa (kg):",
                        "Novo CubeSat", JOptionPane.QUESTION_MESSAGE);
                if (entradaMassa == null) {
                    return;
                }
                double massaKg = Double.parseDouble(entradaMassa.trim().replace(",", "."));
                novo = new CubeSat(id, nome, altitudeKm, status, massaKg);
                break;
            }
            case 2: {
                String entradaBanda = JOptionPane.showInputDialog(null, "Informe a largura de banda (Mbps):",
                        "Novo Satélite de Comunicação", JOptionPane.QUESTION_MESSAGE);
                if (entradaBanda == null) {
                    return;
                }
                double larguraBandaMbps = Double.parseDouble(entradaBanda.trim().replace(",", "."));
                novo = new SateliteComunicacao(id, nome, altitudeKm, status, larguraBandaMbps);
                break;
            }
            default: {
                String entradaResolucao = JOptionPane.showInputDialog(null, "Informe a resolução (metros):",
                        "Novo Satélite de Observação", JOptionPane.QUESTION_MESSAGE);
                if (entradaResolucao == null) {
                    return;
                }
                double resolucaoMetros = Double.parseDouble(entradaResolucao.trim().replace(",", "."));
                novo = new SateliteObservacao(id, nome, altitudeKm, status, resolucaoMetros);
                break;
            }
        }

        CATALOGO.add(novo);
        JOptionPane.showMessageDialog(null,
                "Satélite cadastrado com sucesso!\n\n" + novo,
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void pesquisarPorId() {
        String entradaId = JOptionPane.showInputDialog(null, "Informe o ID do satélite a pesquisar:",
                "Pesquisar", JOptionPane.QUESTION_MESSAGE);
        if (entradaId == null) {
            return;
        }
        int id = Integer.parseInt(entradaId.trim());

        Satelite encontrado = buscarPorId(id);
        if (encontrado == null) {
            JOptionPane.showMessageDialog(null,
                    "Nenhum satélite encontrado com o ID " + id + ".",
                    "Não encontrado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    encontrado.toString(),
                    "Satélite encontrado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void alterarStatus() {
        String entradaId = JOptionPane.showInputDialog(null, "Informe o ID do satélite a alterar:",
                "Alterar status", JOptionPane.QUESTION_MESSAGE);
        if (entradaId == null) {
            return;
        }
        int id = Integer.parseInt(entradaId.trim());

        Satelite encontrado = buscarPorId(id);
        if (encontrado == null) {
            JOptionPane.showMessageDialog(null,
                    "Nenhum satélite encontrado com o ID " + id + ".",
                    "Não encontrado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String novoStatus = pedirStatusValido(
                "Status atual: " + encontrado.getStatus()
                        + "\nInforme o novo status (operacional, manutenção ou desativado):");
        if (novoStatus == null) {
            return;
        }

        encontrado.setStatus(novoStatus);
        JOptionPane.showMessageDialog(null,
                "Status atualizado com sucesso!\n\n" + encontrado,
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void excluirSatelite() {
        String entradaId = JOptionPane.showInputDialog(null, "Informe o ID do satélite a excluir:",
                "Excluir", JOptionPane.QUESTION_MESSAGE);
        if (entradaId == null) {
            return;
        }
        int id = Integer.parseInt(entradaId.trim());

        Satelite encontrado = buscarPorId(id);
        if (encontrado == null) {
            JOptionPane.showMessageDialog(null,
                    "Nenhum satélite encontrado com o ID " + id + ".",
                    "Não encontrado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        CATALOGO.remove(encontrado);
        JOptionPane.showMessageDialog(null,
                "Satélite de ID " + id + " excluído com sucesso.",
                "Exclusão", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void listarTodos() {
        if (CATALOGO.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Nenhum satélite cadastrado.",
                    "Catálogo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Catálogo de Satélites Pequi-Sol\n");
        sb.append("---------------------------------\n");
        for (Satelite s : CATALOGO) {
            sb.append(s.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(),
                "Listar todos", JOptionPane.INFORMATION_MESSAGE);
    }

    private static Satelite buscarPorId(int id) {
        for (Satelite s : CATALOGO) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    private static String pedirStatusValido(String mensagem) {
        String entrada = JOptionPane.showInputDialog(null, mensagem,
                "Status", JOptionPane.QUESTION_MESSAGE);
        if (entrada == null) {
            return null;
        }
        String normalizado = entrada.trim().toLowerCase();
        if (normalizado.equals("operacional")
                || normalizado.equals("manutenção")
                || normalizado.equals("manutencao")
                || normalizado.equals("desativado")) {
            if (normalizado.equals("manutencao")) {
                normalizado = "manutenção";
            }
            return normalizado;
        }
        throw new IllegalArgumentException(
                "Status inválido: '" + entrada + "'. Valores aceitos: operacional, manutenção ou desativado.");
    }
}

# Sistema de Gestão da Constelação Pequi-Sol

Projeto da Global Solution 2026/1 — POO com Java e WEB.

Leonardo Marsiarelli - 3SIR/DP POO - RM 555366

## Estrutura

```
src/br/com/fiap/pequisol/
├── app/Main.java                 # Programa principal (menu via JOptionPane)
├── modelo/
│   ├── Satelite.java             # Classe abstrata base
│   ├── CubeSat.java
│   ├── SateliteComunicacao.java
│   └── SateliteObservacao.java
└── operacao/
    └── Operavel.java             # Interface (calcularConsumoEnergia)
```

## Funcionalidades

Menu em loop com `JOptionPane.showInputDialog`:

1. Registrar novo satélite (CubeSat, Comunicação ou Observação)
2. Pesquisar por ID
3. Alterar status (operacional / manutenção / desativado)
4. Excluir
5. Listar todos
6. Sair

## Fórmulas de consumo de energia

| Classe              | Consumo                              |
| ------------------- | ------------------------------------ |
| CubeSat             | `5.0 + massaKg * 0.8`                |
| SateliteComunicacao | `30.0 + larguraBandaMbps * 1.5`      |
| SateliteObservacao  | `50.0 + 1000.0 / resolucaoMetros`    |

## Tratamento de exceções

- `NumberFormatException` em qualquer entrada numérica: mensagem amigável,
  programa continua no menu.
- `IllegalArgumentException` para valores numéricos não positivos
  (altitude, massa, largura de banda, resolução) e para status inválidos.
- `null` no `showInputDialog` (Cancelar): tratado sem encerrar o programa.

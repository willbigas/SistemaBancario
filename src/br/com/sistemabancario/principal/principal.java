package br.com.sistemabancario.principal;

import br.com.sistemabancario.negocio.ManterBancoNegocio;
import br.com.sistemabancario.entidade.Cliente;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author William Bigas Mauro
 */
public class principal {

    public static void main(String[] args) throws Exception {
        Cliente[] umCliente = ManterBancoNegocio.CLIENTES;
        ManterBancoNegocio.lerArquivo();

        DecimalFormat df = new DecimalFormat();
        df.applyPattern("R$ #,##0.00");

        try {
            String opcao1 = "";
            do {

                opcao1 = JOptionPane.showInputDialog(null, "Você deseja Logar?\r\n[S]-Sim\r\n[N]-Nao");
                if (!opcao1.equals("S")) {
                    System.exit(0);
                }

                // Lendo login da Conta //
                String mensagemA = JOptionPane.showInputDialog("Agencia");
                String mensagemC = JOptionPane.showInputDialog("Conta");
                Integer agenciaLogada = Integer.valueOf(mensagemA);
                Integer contaLogada = Integer.valueOf(mensagemC);

                Cliente logado = null;
                for (int i = 1; i < umCliente.length; i++) {

                    if (contaLogada.equals(umCliente[i].getConta()) && agenciaLogada.equals(umCliente[i].getAgencia())) {

                        logado = umCliente[i]; // Variavel de Uso comum
                        break;
                    }

                }
                if (logado == null) {
                    JOptionPane.showMessageDialog(null, "Conta Inexistente");
                }

                //Carlos Malheiros;   264;   4494;  1358.07; Conta Teste //
                // MENU //
                String opcao = "";

                do {
                    opcao = JOptionPane.showInputDialog("-----" + logado.getNome() + "-----\r\n"
                            + "AG: " + agenciaLogada + "    "
                            + "C: "
                            + contaLogada + "\r\n"
                            + "SALDO: "
                            + df.format(logado.getSaldo())
                            + "\r\n\r\n1-Saque\r\n"
                            + "2-Deposito\r\n"
                            + "3-Transferencia \r\n"
                            + "0-sair");

                    switch (opcao) {
                        case "1": // Saque
                            String msgS = JOptionPane.showInputDialog("Valor");
                            Double valorS = Double.valueOf(msgS);

                            if (null != ManterBancoNegocio.fazerSaque(contaLogada, agenciaLogada, valorS)) {

                            } else {
                                JOptionPane.showMessageDialog(null, "Saldo Insuficiente para Saque!");
                            }

                            break;

                        case "2": // Deposito
                            String msgD = JOptionPane.showInputDialog("Valor");
                            Double valorD = Double.valueOf(msgD);
                            if (valorD <= 9) {
                                JOptionPane.showMessageDialog(null, " Valor Minimo para deposito : R$10.00 ");
                            }
                            ManterBancoNegocio.realizarDeposito(contaLogada, agenciaLogada, valorD);

                            break;

                        case "3": // Transferência
                            String msgAd = JOptionPane.showInputDialog("Agencia Destino");
                            Integer AgenciaD = Integer.valueOf(msgAd);
                            String msgCd = JOptionPane.showInputDialog("Conta Destino");
                            Integer contaD = Integer.valueOf(msgCd);
                            String msgVt = JOptionPane.showInputDialog("Valor de Transferencia");
                            double valorT = Double.valueOf(msgVt);

                            try {
                                Cliente Logado = ManterBancoNegocio.existeConta(contaLogada, agenciaLogada);
                                if (Logado.validaSaldo(valorT)) {
                                    ManterBancoNegocio.fazerTransferencia(contaD, AgenciaD, valorT, contaLogada, agenciaLogada);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Valor Indisponivel em Conta.");
                                }
                            } catch (Exception exception) {
                                JOptionPane.showMessageDialog(null, "Conta Inexistente, Tente novamente!");
                            }
                            break;

                    }
                } while (!opcao.equals("0"));
            } while (opcao1.equals("S"));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Operacão Finalizada");
        }

        // FIM DO MENU //
        //Carlos Malheiros;   264;   4494;  1358.07; Conta Teste //
        //Dirceu Canejo;   264;     5948;  1628.25;  Conta teste //
    }

}

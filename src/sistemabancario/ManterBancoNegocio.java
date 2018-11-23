/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario;

/**
 *
 * @author William
 */
public class ManterBancoNegocio {

    public static Cliente[] CLIENTES = new Cliente[49]; // Array de Clientes

    public static Cliente existeConta(Integer conta, Integer agencia) {
        for (int i = 1; i < CLIENTES.length; i++) {
            if (conta.equals(CLIENTES[i].getConta()) && agencia.equals(CLIENTES[i].getAgencia())) {
                return CLIENTES[i];
            }
        }
        return null;
    }

    public static Cliente fazerSaque(Integer conta, Integer agencia, Double valor) {
        Cliente logado = existeConta(conta, agencia);
        if (logado != null && logado.validaSaldo(valor)) {
            logado.saque(valor);
            return logado;
        }
        return null;
    }

    public static Cliente realizarDeposito(Integer conta, Integer agencia, Double valor) {
        Cliente logado = existeConta(conta, agencia);
        if (null != logado) {
            logado.deposito(valor);
            return logado;
        }
        return null;
    }

    public static Cliente fazerTransferencia(Integer conta, Integer agencia, Double valor, Integer contaProprietario, Integer Agenciapro) {
        Cliente recebe = existeConta(conta, agencia);

        Cliente Logado = existeConta(contaProprietario, Agenciapro);

        if (Logado.getConta() == contaProprietario && Logado.getAgencia() == Agenciapro) {
            Logado.saque(valor);
            recebe.deposito(valor);
        }
        return Logado;
    }

    public static void lerArquivo() throws Exception {

        String[] linhas = util.lerArquivoArray("corretista.txt"); // Linhas do Arquivo

        for (int i = 1; i < CLIENTES.length; i++) {

            String umaLinha = linhas[i];

            String[] umCampo = umaLinha.split("[;]"); // Lendo os Campos

            //Atribuindo todos os Campos ao Objeto //
            CLIENTES[i] = new Cliente();
            CLIENTES[i].setNome(umCampo[0]);
            CLIENTES[i].setAgencia(Integer.valueOf(umCampo[1]));
            CLIENTES[i].setConta(Integer.valueOf(umCampo[2]));
            CLIENTES[i].setSaldo(Double.valueOf(umCampo[3]));

        }

    }
}

package br.com.sistemabancario.entidade;

/**
 *
 * @author William Bigas Mauro
 */
public class Cliente {

    private String nome;
    private Integer agencia;
    private Integer conta;
    private double saldo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void saque(double valor) {
        saldo = saldo - valor;
    }

    public double deposito(double valor) {
        return saldo += valor;
    }

    public boolean validaSaldo(Double valor) {
        if ((saldo - valor) < 0) {
            return false;
        }
        return true;
    }

}

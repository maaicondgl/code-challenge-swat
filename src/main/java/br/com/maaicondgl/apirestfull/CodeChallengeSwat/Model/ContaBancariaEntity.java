package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contas")
public class ContaBancariaEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long idConta;
    private String conta;
    private String agencia;
    private double saldo;
    private double limite;
    private boolean chequeEspecial; // Se saldo for < R$1000 cheque especial False
    private double jurosChequeEspecial;

    public ContaBancariaEntity() {
    }

    public ContaBancariaEntity(Long idConta, String conta, String agencia, double saldo, double limite, boolean chequeEspecial) {
        this.idConta = idConta;
        this.conta = conta;
        this.agencia = agencia;
        this.saldo = saldo;
        this.limite = limite;
        this.chequeEspecial = chequeEspecial;
        this.jurosChequeEspecial = jurosChequeEspecial;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public boolean getChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(boolean chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    public double getJurosChequeEspecial() {
        return jurosChequeEspecial;
    }

    public void setJurosChequeEspecial(double juros) {
        this.jurosChequeEspecial = juros;
    }

    @Override
    public String toString() {
        return "ContaBancariaEntity{" +
                "idConta=" + idConta +
                ", conta='" + conta + '\'' +
                ", agencia='" + agencia + '\'' +
                ", saldo=" + saldo +
                ", limite=" + limite +
                ", chequeEspecial=" + chequeEspecial +
                ", jurosChequeEspecial=" + jurosChequeEspecial +
                '}';
    }
}


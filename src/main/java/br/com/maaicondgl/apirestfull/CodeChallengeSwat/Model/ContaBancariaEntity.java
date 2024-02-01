package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "contas")
public class ContaBancariaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;

    @JoinColumn(name = "nome_banco")
    private String nomeBanco;

    @JoinColumn(name = "agencia")
    private Long agenciaBancaria;

    @JoinColumn(name = "conta")
    private Long conta;

    @JoinColumn(name = "saldo")
    private Double saldo;

    private double limite;
    private boolean chequeEspecial;

    public ContaBancariaEntity() {
    }

    public ContaBancariaEntity(Long idConta, String nomeBanco, Long agenciaBancaria, Long conta, Double saldo, double limite, boolean chequeEspecial) {
        this.idConta = idConta;
        this.nomeBanco = nomeBanco;
        this.agenciaBancaria = agenciaBancaria;
        this.conta = conta;
        this.saldo = saldo;
        this.limite = limite;
        this.chequeEspecial = chequeEspecial;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public Long getAgenciaBancaria() {
        return agenciaBancaria;
    }

    public void setAgenciaBancaria(Long agenciaBancaria) {
        this.agenciaBancaria = agenciaBancaria;
    }

    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
        this.conta = conta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContaBancariaEntity that)) return false;
        return Objects.equals(getIdConta(), that.getIdConta()) && Objects.equals(getNomeBanco(), that.getNomeBanco()) && Objects.equals(getAgenciaBancaria(), that.getAgenciaBancaria()) && Objects.equals(getConta(), that.getConta()) && Objects.equals(getSaldo(), that.getSaldo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdConta(), getNomeBanco(), getAgenciaBancaria(), getConta(), getSaldo());
    }

    @Override
    public String toString() {
        return "ContaBancariaEntity{" +
                "nomeBanco='" + nomeBanco + '\'' +
                ", agenciaBancaria=" + agenciaBancaria +
                ", conta=" + conta +
                ", saldo=" + saldo +
                ", limite=" + limite +
                ", chequeEspecial=" + chequeEspecial +
                '}';
    }
}


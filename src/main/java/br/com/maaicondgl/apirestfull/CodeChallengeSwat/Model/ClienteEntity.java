package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
@JsonPropertyOrder({"id","Name", "phoneNumber", "address"})
public class ClienteEntity implements Serializable {
    @Column(unique=true)
    @Id
    private String cpf;
    @Column(unique=true)
    private String rg;
    private String nome;
    @OneToOne(cascade = CascadeType.PERSIST)
    private ContaBancariaEntity contaBancaria;

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    public ClienteEntity() {
    }

    public ClienteEntity(String cpf, String rg, String nome, ContaBancariaEntity contaBancaria) {
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.contaBancaria = contaBancaria;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ContaBancariaEntity getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancariaEntity contaBancaria) {
        this.contaBancaria = contaBancaria;
    }


    @Override
    public String toString() {
        return "ClienteEntity{" +
                "cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", nome='" + nome + '\'' +
                ", contaBancaria=" + contaBancaria +
                '}';
    }
}

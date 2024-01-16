package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "name", nullable = false)
    private String Name;
    @JoinColumn(name = "phone_number", nullable = false)
    private String phoneNumber;
    @JoinColumn(name = "address", nullable = false)
    private String address;
    @OneToOne
    @JoinColumn(name = "id_conta", referencedColumnName = "idConta")
    private ContaBancariaEntity contaBancariaEntity;

    public ClienteEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ContaBancariaEntity getContaBancariaEntity() {
        return contaBancariaEntity;
    }

    public void setContaBancariaEntity(ContaBancariaEntity contaBancariaEntity) {
        this.contaBancariaEntity = contaBancariaEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPhoneNumber(), that.getPhoneNumber()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getContaBancariaEntity(), that.getContaBancariaEntity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPhoneNumber(), getAddress(), getContaBancariaEntity());
    }
}

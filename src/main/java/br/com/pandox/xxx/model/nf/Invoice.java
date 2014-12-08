package br.com.pandox.xxx.model.nf;

import br.com.pandox.xxx.dto.nf.InvoiceDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Invoice")
public class Invoice {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "value")
    private Double value;

    @Column(name = "date")
    private Date date;

    @Column(name = "COO")
    private String coo;

    @Column(name = "status")
    private Integer status;


    @Column(name = "cnpj")
    private Integer cnpj;

    public Invoice(InvoiceDTO dto) {
        this.value = dto.value;
        this.coo = dto.coo;
        this.status = 0;
        this.cnpj = dto.cnpj;

    }

    public Invoice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCoo() {
        return coo;
    }

    public void setCoo(String coo) {
        this.coo = coo;
    }

    public Integer getCnpj() {
        return cnpj;
    }

    public void setCnpj(Integer cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (id != null ? !id.equals(invoice.id) : invoice.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

package br.com.alura.springdata.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "work_units")
public class WorkUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String address;

    @ManyToMany(mappedBy = "workUnits", fetch = FetchType.EAGER)
    private List<Employee> employees;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addres) {
        this.address = addres;
    }

    @Override
    public String toString() {
        return "Unidades: " + "id:" + id +
                "| descricao:" + description +
                "| endereco:" + address;
    }
}

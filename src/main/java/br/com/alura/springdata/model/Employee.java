package br.com.alura.springdata.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String cpf;
    private Double salary;
    private final LocalDate hiredAt = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employees_workunits", joinColumns = {
            @JoinColumn(name = "fk_employee")
    },
    inverseJoinColumns = {@JoinColumn(name = "fk_workunit")})
    private List<WorkUnit> workUnits;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getHiredAt() {
        return hiredAt;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<WorkUnit> getWorkUnits() {
        return workUnits;
    }

    public void setWorkUnits(List<WorkUnit> workUnits) {
        this.workUnits = workUnits;
    }

    @Override
    public String toString() {
        return "Funcionario: " + "id:" + id + "| nome:" + name + "| cpf:" + cpf + "| salario:" + salary
                + "| dataContratacao:" + hiredAt + "| cargo:" + position.getDescription();
    }
}

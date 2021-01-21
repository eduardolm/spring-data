package br.com.alura.springdata.specification;

import br.com.alura.springdata.model.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EmployeeSpecification {

    public static Specification<Employee> name(String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Employee> cpf(String cpf) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("cpf"), cpf);
    }

    public static Specification<Employee> salary(Double salary) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("salary"), salary);
    }

    public static Specification<Employee> hiredAt(LocalDate hiredAt) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("hiredAt"), hiredAt);
    }
}

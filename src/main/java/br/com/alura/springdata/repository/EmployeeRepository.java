package br.com.alura.springdata.repository;

import br.com.alura.springdata.model.Employee;
import br.com.alura.springdata.model.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

    List<Employee> findByName(String name);

    @Query("SELECT e FROM Employee e WHERE e.name = :name AND e.salary >= :salary AND e.hiredAt = :date")
    List<Employee> findNameSalaryGreaterHiredAt(String name, Double salary, LocalDate date);

    @Query(value = "SELECT * FROM employees e WHERE e.hired_at >= :date", nativeQuery = true)
    List<Employee> findHiredAtGreater(LocalDate date);

    @Query(value = "SELECT e.id, e.name, e.salary FROM employees e", nativeQuery = true)
    List<EmployeeProjection> findEmployeeSalary();
}

package br.com.alura.springdata.service;

import br.com.alura.springdata.model.Employee;
import br.com.alura.springdata.repository.EmployeeRepository;
import br.com.alura.springdata.specification.EmployeeSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class DynamicEmployeeReportService {

    private final EmployeeRepository employeeRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public DynamicEmployeeReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void begin(Scanner scanner) {
        System.out.println("Digite o nome: ");
        String name = scanner.next();

        if (name.equalsIgnoreCase("NULL")) {
            name = null;
        }

        System.out.println("Digite o cpf: ");
        String cpf = scanner.next();

        if (cpf.equalsIgnoreCase("NULL")) {
            cpf = null;
        }

        System.out.println("Digite o salário: ");
        Double salary = scanner.nextDouble();

        if (salary == 0) {
            salary = null;
        }

        System.out.println("Digite a data de contratação: ");
        String date = scanner.next();
        LocalDate hiredAt = null;

        if (date.equalsIgnoreCase("NULL")) {
            date = null;
        }
        else {
            hiredAt = LocalDate.parse(date, formatter);
        }

        List<Employee> employees = employeeRepository.findAll(Specification
                .where(EmployeeSpecification.name(name))
                .or(EmployeeSpecification.cpf(cpf))
                .or(EmployeeSpecification.salary(salary))
                .or(EmployeeSpecification.hiredAt(hiredAt))
        );
        employees.forEach(System.out::println);
    }
}

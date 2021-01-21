package br.com.alura.springdata.service;

import br.com.alura.springdata.model.Employee;
import br.com.alura.springdata.repository.EmployeeRepository;
import br.com.alura.springdata.repository.PositionRepository;
import br.com.alura.springdata.repository.WorkUnitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class ReportService {

    private final PositionRepository positionRepository;

    private final EmployeeRepository employeeRepository;

    private final WorkUnitRepository workUnitRepository;

    private Boolean system = true;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ReportService(PositionRepository positionRepository, EmployeeRepository employeeRepository, WorkUnitRepository workUnitRepository) {
        this.positionRepository = positionRepository;
        this.employeeRepository = employeeRepository;
        this.workUnitRepository = workUnitRepository;
    }

    public void begin(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de cargo deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Buscar funcionário por nome");
            System.out.println("2 - Buscar funcionário por nome, data de contratação e salário maior que...");
            System.out.println("3 - Buscar funcionário por  data de contratação maior que...");


            int action = scanner.nextInt();

            switch(action) {
                case 1: {
                    findEmployeeByName(scanner);
                    break;
                }
                case 2: {
                    findEmployeeSalaryGreaterDate(scanner);
                    break;
                }
                case 3: {
                    findEmployeeHiredAt(scanner);
                    break;
                }
                default: {
                    system = false;
                    break;
                }
            }
        }
    }

    private void findEmployeeByName(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar? ");
        String name = scanner.next();
        List<Employee> list = employeeRepository.findByName(name);
        list.forEach(System.out::println);
    }

    private void findEmployeeSalaryGreaterDate(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar? ");
        String name = scanner.next();

        System.out.println("Qual data de contratação deseja pesquisar? ");
        String date = scanner.next();
        LocalDate localDate = LocalDate.parse(date, formatter);

        System.out.println("Qual salário deseja pesquisar? ");
        Double salary = scanner.nextDouble();

        List<Employee> list = employeeRepository.findNameSalaryGreaterHiredAt(name, salary, localDate);
        list.forEach(System.out::println);
    }

    private void findEmployeeHiredAt(Scanner scanner) {
        System.out.println("Qual data de contratação deseja pesquisar?" );
        String date = scanner.next();
        LocalDate localDate = LocalDate.parse(date, formatter);

        List<Employee> list = employeeRepository.findHiredAtGreater(localDate);
        list.forEach(System.out::println);
    }
}

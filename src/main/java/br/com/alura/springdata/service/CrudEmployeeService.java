package br.com.alura.springdata.service;

import br.com.alura.springdata.model.Employee;
import br.com.alura.springdata.model.Position;
import br.com.alura.springdata.model.WorkUnit;
import br.com.alura.springdata.repository.EmployeeRepository;
import br.com.alura.springdata.repository.PositionRepository;
import br.com.alura.springdata.repository.WorkUnitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final WorkUnitRepository workUnitRepository;
    private final PositionRepository positionRepository;
    private Boolean system = true;

    public CrudEmployeeService(EmployeeRepository employeeRepository,
                               WorkUnitRepository workUnitRepository,
                               PositionRepository positionRepository) {

        this.employeeRepository = employeeRepository;
        this.workUnitRepository = workUnitRepository;
        this.positionRepository = positionRepository;
    }

    public void begin(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de unidade de trabalho deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Apagar");

            int action = scanner.nextInt();

            switch(action) {
                case 1: {
                    save(scanner);
                    break;
                }
                case 2: {
                    update(scanner);
                    break;
                }
                case 3: {
                    view();
                    break;
                }
                case 4: {
                    delete(scanner);
                    break;
                }
                default: {
                    system = false;
                    break;
                }
            }
        }
    }

    private void save(Scanner scanner) {
        System.out.println("Nome do funcionário: ");
        String name = scanner.next();
        System.out.println("CPF: ");
        String cpf = scanner.next();
        System.out.println("Salário: ");
        Double salary = scanner.nextDouble();
        System.out.println("Cargo_id: ");
        Integer positionId = scanner.nextInt();

        Employee employee = new Employee();
        employee.setName(name);
        employee.setCpf(cpf);
        employee.setSalary(salary);
        Optional<Position> position = positionRepository.findById(positionId);

        position.ifPresent(employee::setPosition);
        employeeRepository.save(employee);
    }

    private void update(Scanner scanner) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        System.out.println("Novo nome do funcionário: ");
        String newName = scanner.next();
        System.out.println("Novo CPF do funcionário: ");
        String newCpf = scanner.next();
        System.out.println("Novo salário do funcionário: ");
        Double newSalary = scanner.nextDouble();
        System.out.println("Cargo_id: ");
        Integer positionId = scanner.nextInt();

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(newName);
        employee.setCpf(newCpf);
        employee.setSalary(newSalary);
        Optional<Position> position = positionRepository.findById(positionId);
        position.ifPresent(employee::setPosition);
        employeeRepository.save(employee);
        System.out.println("Atualizado!");
    }

    private void view() {
        Iterable<Employee> employees = employeeRepository.findAll();
        employees.forEach(System.out::println);
    }

    private void delete(Scanner scanner) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        employeeRepository.deleteById(id);
        System.out.println("Apagado!");
    }

    private List<WorkUnit> workUnits(Scanner scanner) {
        Boolean isTrue = true;
        List<WorkUnit> units = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidade_id (para sair digite 0): ");
            Integer unitId = scanner.nextInt();

            if (unitId != 0) {
                Optional<WorkUnit> unit = workUnitRepository.findById(unitId);
                units.add(unit.get());
            }
            else {
                isTrue = false;
            }
        }
        return units;
    }

}

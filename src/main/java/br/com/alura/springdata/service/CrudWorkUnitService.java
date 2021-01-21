package br.com.alura.springdata.service;

import br.com.alura.springdata.model.WorkUnit;
import br.com.alura.springdata.repository.WorkUnitRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudWorkUnitService {

    private final WorkUnitRepository workUnitRepository;
    private Boolean system = true;

    public CrudWorkUnitService(WorkUnitRepository workUnitRepository) {
        this.workUnitRepository = workUnitRepository;
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
        System.out.println("Descrição da unidade de trabalho: ");
        String description = scanner.next();
        System.out.println("Endereço da unidade de trablalho: ");
        String address = scanner.next();
        WorkUnit workUnit = new WorkUnit();
        workUnit.setDescription(description);
        workUnit.setAddress(address);
        workUnitRepository.save(workUnit);
    }

    private void update(Scanner scanner) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        System.out.println("Nova descrição da unidade de trabalho: ");
        String newDescription = scanner.next();
        System.out.println("Novo endereço da unidade de trabalho: ");
        String newAddress = scanner.next();

        WorkUnit workUnit = new WorkUnit();
        workUnit.setId(id);
        workUnit.setDescription(newDescription);
        workUnit.setAddress(newAddress);
        workUnitRepository.save(workUnit);
        System.out.println("Atualizado!");
    }

    private void view() {
        Iterable<WorkUnit> workUnits = workUnitRepository.findAll();
        workUnits.forEach(System.out::println);
    }

    private void delete(Scanner scanner) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        workUnitRepository.deleteById(id);
        System.out.println("Apagado!");
    }

}

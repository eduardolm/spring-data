package br.com.alura.springdata.service;

import br.com.alura.springdata.model.Position;
import br.com.alura.springdata.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudPositionService {

    private final PositionRepository positionRepository;

    private Boolean system = true;

    public CrudPositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public void begin(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de cargo deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Savar");
            System.out.println("2 - Atualizar");

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

                default: {
                    system = false;
                    break;
                }

            }
        }

    }

    private void save(Scanner scanner) {
        System.out.println("Descrição do cargo: ");
        String description = scanner.next();
        Position position = new Position();
        position.setDescription(description);
        positionRepository.save(position);
    }

    private void update(Scanner scanner) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        System.out.println("Nova descrição do cargo: ");
        String newDescription = scanner.next();

        Position position = new Position();
        position.setId(id);
        position.setDescription(newDescription);
        positionRepository.save(position);
        System.out.println("Atualizado!");

    }
}

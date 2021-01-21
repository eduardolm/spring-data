package br.com.alura.springdata;

import br.com.alura.springdata.model.Position;
import br.com.alura.springdata.repository.PositionRepository;
import br.com.alura.springdata.service.CrudPositionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final CrudPositionService positionService;

    private Boolean system = true;

    public SpringDataApplication(CrudPositionService positionService) {
        this.positionService = positionService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (system) {
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Cargo");

            int action = scanner.nextInt();
            if (action == 1) {
                positionService.begin(scanner);
            }
            else {
                system = false;
            }
        }

    }
}

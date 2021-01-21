package br.com.alura.springdata;

import br.com.alura.springdata.service.CrudEmployeeService;
import br.com.alura.springdata.service.CrudPositionService;
import br.com.alura.springdata.service.CrudWorkUnitService;
import br.com.alura.springdata.service.ReportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final CrudPositionService positionService;

    private final CrudEmployeeService employeeService;

    private final CrudWorkUnitService workUnitService;

    private final ReportService reportService;

    private Boolean system = true;

    public SpringDataApplication(CrudPositionService positionService,
                                 CrudEmployeeService employeeService,
                                 CrudWorkUnitService workUnitService,
                                 ReportService reportService) {

        this.positionService = positionService;
        this.employeeService = employeeService;
        this.workUnitService = workUnitService;
        this.reportService = reportService;
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
            System.out.println("2 - Unidade de trabalho");
            System.out.println("3 - Funcionário");
            System.out.println("4 - Relatórios");

            int action = scanner.nextInt();

            switch(action) {
                case 1: {
                    positionService.begin(scanner);
                    break;
                }
                case 2: {
                    workUnitService.begin(scanner);
                    break;
                }
                case 3: {
                    employeeService.begin(scanner);
                    break;
                }
                case 4: {
                    reportService.begin(scanner);
                    break;
                }
                default: {
                    system = false;
                    break;
                }
            }
        }
    }
}

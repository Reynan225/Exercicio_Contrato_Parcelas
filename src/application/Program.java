package application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre os dados do contrato: ");
        System.out.print("Numero: ");
        int number = sc.nextInt();
        sc.nextLine();

        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Valor do contrato: ");
        double valueContract = sc.nextDouble();

        Contract contract = new Contract(number, date, valueContract);

        System.out.print("Entre com número de parcelas: ");
        int installments = sc.nextInt();

        ContractService service = new ContractService(new PaypalService());

        service.processContract(contract, installments);

        System.out.println("\nPARCELAS:");
        for (Installment c : contract.getInstallment()) {
            System.out.println(c.toString());
        }

        sc.close();
    }
}
package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.time.LocalDate;

public class ContractService {

    private  OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months) {

        double basicQuota = contract.getTotalValue() / months;

        // 'i' irá representar as parcelas de cada mês
        for (int i = 1; i <= months; i++) {

            LocalDate dueDate = contract.getDate().plusMonths(i);
            double updatedQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
            double fullQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota);

            contract.addInstallment(new Installment(dueDate, fullQuota));
        }

    }
}
package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.time.LocalDate;

public class ContractService {

    private OnlinePayementService onlinePayementService;

    public ContractService(OnlinePayementService onlinePayementService) {
        this.onlinePayementService = onlinePayementService;
    }
    public void processContract(Contract contract, int months){

        double basicQuota = contract.getTotalValue() /months;

        for (int i =1; i <= months; i++){
            LocalDate dueDate = contract.getDate().plusMonths(i);

            double interest = onlinePayementService.interest(basicQuota,i);
            double fee =  onlinePayementService.paymentFee(basicQuota + interest);
            double quota = basicQuota + interest + fee;

            contract.getInstallments().add(new Installment(dueDate,quota));

        }
    }
}

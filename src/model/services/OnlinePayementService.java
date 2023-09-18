package model.services;

public interface OnlinePayementService {

    double paymentFee(double amount);
    double interest(double amount, int months);

}

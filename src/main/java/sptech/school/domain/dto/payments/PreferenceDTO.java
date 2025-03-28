package sptech.school.domain.dto.payments;

public class PreferenceDTO {
    private double amount;
    private String payer_email;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayer_email() {
        return payer_email;
    }

    public void setPayer_email(String payer_email) {
        this.payer_email = payer_email;
    }
}

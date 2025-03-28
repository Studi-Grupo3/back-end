package sptech.school.domain.dto.payments;

public class PayerDTO {
    private String email;
    private String firstName;
    private IdentificationDTO identification;
    private AddressDTO address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public IdentificationDTO getIdentification() {
        return identification;
    }

    public void setIdentification(IdentificationDTO identification) {
        this.identification = identification;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}

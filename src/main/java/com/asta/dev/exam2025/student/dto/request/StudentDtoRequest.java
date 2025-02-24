package com.asta.dev.exam2025.student.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoRequest {

    private String firstName;

    private String lastName;

    private String emailPro;

    private String emailPerso;

    private String phoneNumber;

    private String address;

    private Boolean archive;

    private String registrationNu;

    // Getter & Setter

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailPro() {
        return emailPro;
    }

    public void setEmailPro(String emailPro) {
        this.emailPro = emailPro;
    }

    public String getEmailPerso() {
        return emailPerso;
    }

    public void setEmailPerso(String emailPerso) {
        this.emailPerso = emailPerso;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = Boolean.valueOf(archive);
    }

    public String getRegistrationNu() {
        return registrationNu;
    }

    public void setRegistrationNu(String registrationNu) {
        this.registrationNu = registrationNu;
    }
}

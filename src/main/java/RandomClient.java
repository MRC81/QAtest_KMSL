import com.github.javafaker.Faker;

public class RandomClient {

    private final String firstName, lastName, email, phone, company;
    Faker faker = new Faker();

    // Constructor
    RandomClient() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        phone = faker.phoneNumber().phoneNumber();
        company = faker.company().name();
        email = firstName + "@" + company.replaceAll("[^a-zA-Z]", "").substring(0, 6) + ".com";
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCompany() {
        return company;
    }

}

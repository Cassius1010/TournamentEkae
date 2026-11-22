
package cashtime;

public class Organiser {

    private int organiserId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String organisationName;
    private String password;

    public Organiser(int organiserId, String fullName,
            String email, String phoneNumber,
            String organisationName, String password) {

        this.organiserId = organiserId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.organisationName = organisationName;
        this.password = password;
    }

    public int getOrganiserId() {
        return organiserId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public String getPassword() {
        return password;
    }
}

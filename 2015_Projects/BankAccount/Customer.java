/**
 * This class represents a bank customer identified by three attributes:
 * name, phoneNumber, and emailAddress.
 * 
 * @author Ping Zhang Oct. 2015
 * @version %I%, %G%
 * @since 1.0
 */
public class Customer {

    private String name;
    private String phoneNumber;
    private String emailAddress;

    Customer() {
        this("Default Customer", "000-0000000", "000@mybank.com");
    }

    Customer(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    Customer(Customer other) {
        this.name = other.name;
        this.phoneNumber = other.name;
        this.emailAddress = other.emailAddress;
    }

    /**
     *
     * @return the name of the client
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @return email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     *
     * @param name the client name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param phoneNumber a string represent the client's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @param emailAddress a string represent the client's email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n"
                + "PhoneNumber: " + phoneNumber + "\n"
                + "EmailAddress: " + emailAddress;
    }

    /**
     * To compare two Customer objects which is considered equal if they have
     * identical states.
     * @param obj another Customer object
     * @return the compare result
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Customer other = (Customer) obj;

        if (this == obj) {
            return true;
        }

        if (!this.name.equals(other.name)) {
            return false;
        }
        if (!this.phoneNumber.equals(other.phoneNumber)) {
            return false;
        }
        if (!this.emailAddress.equals(other.emailAddress)) {
            return false;
        }
        return true;
    }

}

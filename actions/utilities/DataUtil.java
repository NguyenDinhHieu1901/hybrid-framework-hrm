package utilities;

import com.github.javafaker.Faker;

public class DataUtil {
	private Faker faker;

	public DataUtil() {
		faker = new Faker();
	}

	public static DataUtil getDataUtil() {
		return new DataUtil();
	}

	private enum Gender {
		Male, Female;
	}

	private enum MaritalStatus {
		Married, Single;
	}
	
	private enum EmployeeStatus {
		Enabled
	}

	private enum Nationality {
		Vietnamese, Japanese, Indian, French, America, Italian, Spanish;
	}

	public String getFirstName() {
		return faker.name().firstName();
	}

	public String getLastName() {
		return faker.name().lastName();
	}

	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}

	public String getUserName() {
		return faker.name().username();
	}

	public String getPassword() {
		return faker.internet().password(8, 10, true, true, true);
	}

	public String getEmployeeStatus() {
		return faker.options().option(EmployeeStatus.values()).toString();
	}

	public String getGender() {
		return faker.options().option(Gender.values()).toString();
	}

	public String getMaritalStatus() {
		return faker.options().option(MaritalStatus.values()).toString();
	}

	public String getNationality() {
		return faker.options().option(Nationality.values()).toString();
	}

	public String getZipCode() {
		return faker.address().zipCode();
	}

	public String getCity() {
		return faker.address().city();
	}

	public String getAddressStreet() {
		return faker.address().streetAddress();
	}

	public String getHomeTelephone() {
		return faker.phoneNumber().phoneNumber();
	}

	public String getMobileTelephone() {
		return faker.phoneNumber().cellPhone();
	}

	public String getEditFirstName() {
		return faker.name().firstName();
	}

	public String getEditLastName() {
		return faker.name().lastName();
	}
}

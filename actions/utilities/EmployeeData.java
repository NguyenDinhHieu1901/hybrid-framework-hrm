package utilities;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class EmployeeData {

	public static EmployeeData getEmployee() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.PROJECT_PATH + "\\json\\employee.json"), EmployeeData.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@JsonProperty("AddNewEmployee")
	AddNewEmployee addNewEmployee;

	public static class AddNewEmployee {
		@JsonProperty("firstName")
		private String firstName;

		@JsonProperty("lastName")
		private String lastName;

		@JsonProperty("fullName")
		private String fullName;

		@JsonProperty("statusValue")
		private String statusValue;

		@JsonProperty("userName")
		private String userName;

		@JsonProperty("password")
		private String password;
	}

	public String getFirstName() {
		return addNewEmployee.firstName;
	}

	public String getLastName() {
		return addNewEmployee.lastName;
	}

	public String getFullName() {
		return addNewEmployee.fullName;
	}

	public String getStatusValue() {
		return addNewEmployee.statusValue;
	}

	public String getUserName() {
		return addNewEmployee.userName;
	}

	public String getPassword() {
		return addNewEmployee.password;
	}

	@JsonProperty("PersonalDetails")
	PersonalDetails personalDetails;

	public static class PersonalDetails {
		@JsonProperty("editFirstName")
		private String editFirstName;

		@JsonProperty("editLastName")
		private String editLastName;

		@JsonProperty("editFullName")
		private String editFullName;

		@JsonProperty("editGender")
		private String editGender;

		@JsonProperty("editMaritalStatus")
		private String editMaritalStatus;

		@JsonProperty("editNationality")
		private String editNationality;

	}

	public String getEditFirstName() {
		return personalDetails.editFirstName;
	}

	public String getEditLastName() {
		return personalDetails.editLastName;
	}

	public String getEditFullName() {
		return personalDetails.editFullName;
	}

	public String getEditGender() {
		return personalDetails.editGender;
	}

	public String getEditMaritalStatus() {
		return personalDetails.editMaritalStatus;
	}

	public String getEditNationality() {
		return personalDetails.editNationality;
	}
}

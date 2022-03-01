package com.hrm.data;

import utilities.DataUtil;

public class Employee {

	public static class Add_New_Employee {
		static DataUtil data = DataUtil.getDataUtil();
		public static String firstName = data.getFirstName();
		public static String lastName = data.getLastName();
		public static String username = data.getUserName();
		public static String password = data.getPassword();
		public static String employeeStatus = data.getEmployeeStatus();
	}

	public static class PersonalDetails {
		static DataUtil data = DataUtil.getDataUtil();
		public static String firstName = data.getEditFirstName();
		public static String lastName = data.getEditLastName();
		public static String gender = data.getGender();
		public static String maritalStatus = data.getMaritalStatus();
		public static String nationality = data.getNationality();
	}
}

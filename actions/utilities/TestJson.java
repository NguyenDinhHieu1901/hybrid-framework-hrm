package utilities;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import commons.GlobalConstants;

public class TestJson {
	static JSONParser jsonParser = new JSONParser();

	@SuppressWarnings({ "unchecked", "static-access" })
	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		obj.put("firstName", "Nguyen");
		obj.put("lastName", "Test");
		obj.put("salary", "2500");
		System.out.println(obj.toJSONString());
		
		TestJson test = new TestJson();
		System.out.println(test.readJSON("users", "userName"));
	}

	public static String readJSON(String functionName, String dataName) {

		try {
			Object obj = jsonParser.parse(new FileReader(GlobalConstants.PROJECT_PATH + "\\json\\employee.json"));
			JSONArray employeeList = (JSONArray) obj;
			for (int i = 0; i < employeeList.size(); i++) {
				JSONObject function = (JSONObject) employeeList.get(i);
				JSONObject fuctionList = (JSONObject) function.get(functionName);
				dataName = (String) fuctionList.get(dataName);
			}
			return dataName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}

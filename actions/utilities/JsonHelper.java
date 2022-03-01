package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHelper {
	JSONParser jsonParser;

	public JsonHelper() {
		jsonParser = new JSONParser();
	}

	public List<String> readWriteJSON(String arrayKey, String key) throws IOException, ParseException {
		List<String> list = new ArrayList<String>();
		try {
			FileReader reader = new FileReader("json\\user.json");
			Object obj = jsonParser.parse(reader);

			JSONArray userList = (JSONArray) obj;
			System.out.println("User List -> " + userList); // This prints the entire json file

			for (int i = 0; i < userList.size(); i++) {
				JSONObject users = (JSONObject) userList.get(i);
				System.out.println("Users -> " + users); // This prints every block - one json object

				JSONObject user = (JSONObject) users.get(arrayKey);
				System.out.println("User -> " + user); // This prints each data in the block
				list.add((String) user.get(key));
			}
			return list;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) throws IOException, ParseException {
		JsonHelper helper = new JsonHelper();
		List<String> usernames = helper.readWriteJSON("users", "username");
		for (String username : usernames) {
			System.out.println(username);
		}

		List<String> passwords = helper.readWriteJSON("users", "password");
		for (String password : passwords) {
			System.out.println(password);
		}
	}
}

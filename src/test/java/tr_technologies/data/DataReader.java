package tr_technologies.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		// read json to string

		// String jsonContent= FileUtils.readFileToString(new
		// File(System.getProperty("user.dir")+
		// "\\src\\test\\java\\tr_technologies\\data\\PurchaseOrder.json"));
		// here readFileToString() is deprecated hence we are using below method

		// Specify the file path
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\java\\tr_technologies\\data\\PurchaseOrder.json";
		File file = new File(filePath);

		// Read the file content with UTF-8 encoding
		String jsonContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

		// Print the JSON content
		System.out.println(jsonContent);

		// String to HashMap using Jaskson Databind

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});

		return data;

	}

}

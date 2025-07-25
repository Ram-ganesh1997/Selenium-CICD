package seleniumFrameworkDataJson;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws Exception {
		
		//read json to string
		
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\seleniumFrameworkDataJson\\dataProvider.json"),
		StandardCharsets.UTF_8);
		
		//String to HashMap - Jackson Databind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
}

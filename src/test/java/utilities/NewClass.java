package utilities;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class NewClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> newList = new ArrayList<String>();
		newList.add("1");
		newList.add("2");
		newList.add("3");
		
		JSONObject team = new JSONObject();
		team.put("team", "teamName");
		team.put("video", "videoName");
		team.put("upcoming-videos", newList);
		
		try {
			FileWriter file = new FileWriter("file.json");
            file.write(team.toJSONString());
            file.flush();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}

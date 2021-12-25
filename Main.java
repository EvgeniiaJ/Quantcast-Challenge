import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static int findMaxOccurence(Map<String, ArrayList<TimeStamp>> timeMap, Map<String, Integer> map,
			String inputId, String inputPart) {

		int max = 0;

		for (Map.Entry<String, ArrayList<TimeStamp>> entry : timeMap.entrySet()) {

			int counter = 0;

			for (int i = 0; i < entry.getValue().size(); i++) {

				if (inputId.equals("-d")) {
					if (entry.getValue().get(i).getDate().equals(inputPart)) {
						map.put(entry.getKey(), map.getOrDefault(entry.getKey(), 0) + 1);
						counter++;
					}
				}

				else if (inputId.equals("-t")) {
					if (entry.getValue().get(i).getTime().equals(inputPart)) {
						map.put(entry.getKey(), map.getOrDefault(entry.getKey(), 0) + 1);
						counter++;
					}
				}

				else if (inputId.equals("-u")) {
					if (entry.getValue().get(i).getSmallUnits().equals(inputPart)) {
						map.put(entry.getKey(), map.getOrDefault(entry.getKey(), 0) + 1);
						counter++;
					}
				}

			}

			max = Math.max(max, counter);
		}
		return max;
	}

	public static void populateListWithMaxOccurences(Map<String, Integer> map, int max, ArrayList<String> list) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == max) {
				list.add(entry.getKey());
			}
		}
	}

	public static void printResults(List<String> answer) {
		for (int i = 0; i < answer.size(); i++) {
			System.out.println(answer.get(i));
		}
	}

	public static void main(String[] args) {
		String inputFile = "input.csv";
		File file = new File(inputFile);
		String inputId = "-d";
		String inputPart = "2018-12-09";

		ArrayList<String> result = new ArrayList<>();

		Map<String, Integer> map = new HashMap<>();
		Map<String, ArrayList<TimeStamp>> timeMap = new HashMap<>();

		try {

			Scanner scan = new Scanner(file);

			while (scan.hasNext()) {
				String newLine = scan.nextLine();

				String id = newLine.substring(0, newLine.indexOf(","));

				String timestamp = newLine.substring(newLine.indexOf(",") + 1, newLine.length());

				String date = timestamp.substring(0, timestamp.indexOf("T"));
				String time = timestamp.substring(timestamp.indexOf("T") + 1, timestamp.indexOf("+"));
				String smallUnits = timestamp.substring(timestamp.indexOf("+") + 1, timestamp.length());

				TimeStamp timeStamp = new TimeStamp(date, time, smallUnits);

				if (timeMap.containsKey(id)) {
					(timeMap.get(id)).add(timeStamp);
				} else {
					ArrayList<TimeStamp> timeList = new ArrayList<>();
					timeList.add(timeStamp);
					timeMap.put(id, timeList);
				}
			}

			int max = findMaxOccurence(timeMap, map, inputId, inputPart);

			populateListWithMaxOccurences(map, max, result);

			printResults(result);

			scan.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
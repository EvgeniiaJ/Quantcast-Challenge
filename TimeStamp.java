
public class TimeStamp {
	
	private String date;
	private String time;
	private String smallUnits;

	public TimeStamp(String date, String time, String smallUnits) {
			this.date = date;
			this.time = time;
			this.smallUnits = smallUnits;
		}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSmallUnits() {
		return smallUnits;
	}

	public void setSmallUnits(String smallUnits) {
		this.smallUnits = smallUnits;
	}

	public String toString() {
		return ("Date: " + date + "\nTime: " + time + "\nUnits: " + smallUnits + "\n\n");
	}
}

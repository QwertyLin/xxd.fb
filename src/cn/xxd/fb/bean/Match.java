package cn.xxd.fb.bean;

public class Match {

	private String time;
	private String score;
	private String home;
	private String guest;
	
	public String getScore() {
		if(score.contains("(")){
			score = score.substring(0, score.indexOf("("));
		}
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	public String getTime() {
		if(time.contains(",")){
			time = time.substring(5, 10).replace(",", "-") + "<br/>" + time.substring(11, 16).replace(",", ":");
		}
		return time;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	

	

	public void setTime(String time) {
		this.time = time;
	}
	
	
}

package cn.xxd.fb.bean;

public class LeagueBoard {
	
	private Club club; 
	private int number; //13场
	private int win; //胜
	private int lose; //负
	private int gain; //得
	private int loss; //失
	
	//平局
	public int getDraw(){
		return this.number - this.win - this.lose;
	}
	
	public int getScore(){
		return this.getWin() * 3 + getDraw();
	}
	
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}

	public int getGain() {
		return gain;
	}

	public void setGain(int gain) {
		this.gain = gain;
	}

	public int getLoss() {
		return loss;
	}

	public void setLoss(int loss) {
		this.loss = loss;
	}
	
	
}

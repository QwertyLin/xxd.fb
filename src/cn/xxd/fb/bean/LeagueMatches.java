package cn.xxd.fb.bean;

import java.util.ArrayList;
import java.util.List;

public class LeagueMatches {

	private int currentRun;//当前轮次
	private int runCount;//总轮数
	private int matchCountEachRun; //每轮比赛数
	
	private String[] times;//时间
	private String[] scores;//比分
	private String[] teamHomes;//主队列表
	private String[] teamGuests;//客队列表
	
	public List<LeagueMatch> getMatches(int runNumber){ //获得指定轮数的比赛列表
		List<LeagueMatch> matches = new ArrayList<LeagueMatch>();
		LeagueMatch match = null;
		for(int i = 0; i < teamHomes.length; i++){
	    	if(i / 10 == runNumber - 1){
	    		match = new LeagueMatch();
	    		matches.add(match);
	    		match.setTime(times[i]);
	    		match.setScore(scores[i]);
	    		match.setTeamHome(teamHomes[i]);
	    		match.setTeamGuest(teamGuests[i]);
	    	}
	    }
		return matches;
	}

	public int getCurrentRun() {
		return currentRun;
	}

	public void setCurrentRun(int currentRun) {
		this.currentRun = currentRun;
	}

	public int getRunCount() {
		return runCount;
	}

	public void setRunCount(int runCount) {
		this.runCount = runCount;
	}

	public int getMatchCountEachRun() {
		return matchCountEachRun;
	}

	public void setMatchCountEachRun(int matchCountEachRun) {
		this.matchCountEachRun = matchCountEachRun;
	}

	public String[] getTeamHomes() {
		return teamHomes;
	}

	public void setTeamHomes(String[] teamHomes) {
		this.teamHomes = teamHomes;
	}

	public String[] getTeamGuests() {
		return teamGuests;
	}

	public void setTeamGuests(String[] teamGuests) {
		this.teamGuests = teamGuests;
	}

	public String[] getScores() {
		return scores;
	}

	public void setScores(String[] scores) {
		this.scores = scores;
	}

	public String[] getTimes() {
		return times;
	}

	public void setTimes(String[] times) {
		this.times = times;
	}
	
	
}

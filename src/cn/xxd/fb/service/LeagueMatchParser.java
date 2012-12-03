package cn.xxd.fb.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.xxd.fb.bean.LeagueMatches;

public class LeagueMatchParser {
	
	private static final String URL = "http://data2.7m.cn/matches_data/{id}/gb/fixture.js";
	
	public static final String getUrl(int id){
		return URL.replace("{id}", String.valueOf(id));
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static final LeagueMatches parse(String str){
		LeagueMatches matches = new LeagueMatches();
		//当前轮次
		Matcher m = Pattern.compile("currentRun = (\\d{1,2});").matcher(str);
		if(!m.find()){
			return null;
		}
	    matches.setCurrentRun(Integer.valueOf(m.group(1)));
	    if(matches.getCurrentRun() == 0){
	    	return null;
	    }
	    //总轮数，比如英超是38
	    m = Pattern.compile("run_Head_Arr = \\[ (.*?)\\];").matcher(str);
	    if(!m.find()){
			return null;
		}
	    matches.setRunCount(m.group(1).split(",").length);
	    if(matches.getRunCount() < 5 || matches.getRunCount() > 100){
	    	return null;
	    }
	    //每轮比赛数，比如英超是10
	    m = Pattern.compile("Run_Arr = \\[ (.*?)\\];").matcher(str);
	    if(!m.find()){
			return null;
		}
	    matches.setMatchCountEachRun(m.group(1).split(",").length / matches.getRunCount());
	    if(matches.getMatchCountEachRun() < 5 || matches.getMatchCountEachRun() > 50){
	    	return null;
	    }
	    //时间
	    m = Pattern.compile("Time_Arr = \\[ \\\"(.*?)\\\"\\];").matcher(str);
	    if(!m.find()){
			return null;
		}
	    matches.setTimes(m.group(1).split("\\\",\\\""));
	    if(matches.getTimes() == null || matches.getTimes().length != matches.getRunCount() * matches.getMatchCountEachRun()){
	    	return null;
	    }
	    //比分
	    m = Pattern.compile("Scores_Arr = \\[ \\\"(.*?)\\\"\\];").matcher(str);
	    if(!m.find()){
			return null;
		}
	    matches.setScores(m.group(1).split("\\\",\\\""));
	    if(matches.getScores() == null || matches.getScores().length != matches.getRunCount() * matches.getMatchCountEachRun()){
	    	return null;
	    }
	    //主队
	    m = Pattern.compile("TeamA_Arr = \\[ \\\"(.*?)\\\"\\];").matcher(str);
	    if(!m.find()){
			return null;
		}
	    matches.setTeamHomes(m.group(1).split("\\\",\\\""));
	    if(matches.getTeamHomes() == null || matches.getTeamHomes().length != matches.getRunCount() * matches.getMatchCountEachRun()){
	    	return null;
	    }
	    //客队
	    m = Pattern.compile("TeamB_Arr = \\[ \\\"(.*?)\\\"\\];").matcher(str);
	    if(!m.find()){
			return null;
		}
	    matches.setTeamGuests(m.group(1).split("\\\",\\\""));
	    if(matches.getTeamGuests() == null || matches.getTeamGuests().length != matches.getRunCount() * matches.getMatchCountEachRun()){
	    	return null;
	    }
		return matches;
	}

}

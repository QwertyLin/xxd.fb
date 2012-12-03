package cn.xxd.fb.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeagueBoardParser {
	
	public static final List<LeagueBoard> parse(String str){
		List<LeagueBoard> datas = null;
		Matcher mClub = Pattern.compile("f_sds_tn = \\[ (.*?)\\];").matcher(str);
		String[] sClub = null;
		if(mClub.find()){
			sClub = mClub.group(1).split(",");
		}
		Matcher mClubId = Pattern.compile("f_sds_ti = \\[ (.*?)\\];").matcher(str);
		String[] sClubId = null;
		if(mClubId.find()){
			sClubId = mClubId.group(1).split(",");
		}
		Matcher mNumber = Pattern.compile("f_sds_mnum = \\[ (.*?)\\];").matcher(str);
		String[] sNumber = null;
		if(mNumber.find()){
			sNumber = mNumber.group(1).split(",");
		}
		Matcher mWin = Pattern.compile("f_sds_mw = \\[ (.*?)\\];").matcher(str);
		String[] sWin = null;
		if(mWin.find()){
			sWin = mWin.group(1).split(",");
		}
		Matcher mLose = Pattern.compile("f_sds_ml = \\[ (.*?)\\];").matcher(str);
		String[] sLose = null;
		if(mLose.find()){
			sLose = mLose.group(1).split(",");
		}
		Matcher mGain = Pattern.compile("f_sds_mgs = \\[ (.*?)\\];").matcher(str);
		String[] sGain = null;
		if(mGain.find()){
			sGain = mGain.group(1).split(",");
		}
		Matcher mLoss = Pattern.compile("f_sds_mga = \\[ (.*?)\\];").matcher(str);
		String[] sLoss = null;
		if(mLoss.find()){
			sLoss = mLoss.group(1).split(",");
		}
		if(sClub != null && sClubId != null && sNumber != null && sWin != null && sLose != null && sGain != null && sLoss != null){
			datas = new ArrayList<LeagueBoard>();
			LeagueBoard item;
			for(int i = 0; i < sClub.length; i++){
				item = new LeagueBoard();
				item.setClub(new Club(Integer.valueOf(sClubId[i]), sClub[i].substring(1, sClub[i].length() - 1)));
				item.setNumber(Integer.valueOf(sNumber[i]));
				item.setWin(Integer.valueOf(sWin[i]));
				item.setLose(Integer.valueOf(sLose[i]));
				item.setGain(Integer.valueOf(sGain[i]));
				item.setLoss(Integer.valueOf(sLoss[i]));
				datas.add(item);
			}
		}
		
		return datas;
	}

}

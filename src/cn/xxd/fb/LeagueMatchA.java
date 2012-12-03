package cn.xxd.fb;

import cn.xxd.fb.bean.LeagueMatch;
import cn.xxd.fb.bean.LeagueMatches;
import cn.xxd.fb.service.LeagueMatchParser;
import q.util.AsyncHttpHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LeagueMatchA extends Fragment implements AsyncHttpHelper.OnAsyncHttpListener {
	
	private LeagueMatches matches;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_league_match, null);
		
		new AsyncHttpHelper().get(getActivity(), LeagueMatchParser.getUrl(92), AsyncHttpHelper.NO_CACHE, this);
		
		return view;
	}
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("a", 1);
    }

	@Override
	public boolean onAsyncHttpVerify(String content) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onAsyncHttpSuccess(String content) {
		matches = LeagueMatchParser.parse(content);
		System.out.println(matches.getCurrentRun());
		System.out.println(matches.getRunCount());
		System.out.println(matches.getMatchCountEachRun());
		System.out.println(matches.getTeamHomes().length);
		LeagueMatch match = matches.getMatches(15).get(0);
		System.out.println(match.getTime());
		System.out.println(match.getTeamHome());
		System.out.println(match.getTeamGuest());
		System.out.println(match.getScore());
	}

	@Override
	public void onAsyncHttpFailure(Throwable error, String content) {
		// TODO Auto-generated method stub
		
	}

}

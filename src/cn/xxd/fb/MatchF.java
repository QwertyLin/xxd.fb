package cn.xxd.fb;

import java.util.ArrayList;
import java.util.List;

import cn.xxd.fb.adapter.MatchAdapter;
import cn.xxd.fb.bean.Match;
import cn.xxd.fb.bean.LeagueMatches;
import cn.xxd.fb.service.LeagueMatchParser;
import q.util.AsyncHttpHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

public class MatchF extends Fragment implements OnClickListener, AsyncHttpHelper.OnAsyncHttpListener {
	
	private int mCurrentRun; //最后一次选择的轮次
	private LeagueMatches matches;
	
	private List<Match> datas = new ArrayList<Match>();
	private MatchAdapter adapter;
	private View vNav;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_match, null);
		//
		adapter = new MatchAdapter(getActivity(), datas);
		ListView listView = (ListView)view.findViewById(R.id.match_list);
		listView.setAdapter(adapter);
		//
		vNav = view.findViewById(R.id.match_nav);
		//
		view.findViewById(R.id.match_left).setOnClickListener(this);
		view.findViewById(R.id.match_right).setOnClickListener(this);
		//
		if(datas.size() == 0){
			new AsyncHttpHelper().get(getActivity(), LeagueMatchParser.getUrl(92), AsyncHttpHelper.NO_CACHE, this);
		}else{
			vNav.setVisibility(View.VISIBLE);
		}
		return view;
	}
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("a", 1);
    }
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.match_left:
			onClickLeft();
			break;
		case R.id.match_right:
			onClickRight();
			break;
		}
	}
	
	private void onClickLeft(){
		mCurrentRun--;
		if(mCurrentRun < 1){
			mCurrentRun = 1;
			return;
		}
		onSwitchRun();
	}
	
	private void onClickRight(){
		mCurrentRun++;
		if(mCurrentRun > matches.getRunCount()){
			mCurrentRun = matches.getRunCount();
			return;
		}
		onSwitchRun();
	}
	
	private void onSwitchRun(){
		datas.clear();
		datas.addAll(matches.getMatches(mCurrentRun));
		adapter.notifyDataSetChanged();
	}

	@Override
	public boolean onAsyncHttpVerify(String content) {
		return true;
	}

	@Override
	public void onAsyncHttpSuccess(String content) {
		matches = LeagueMatchParser.parse(content);
		this.mCurrentRun = matches.getCurrentRun();
		onSwitchRun();
		//
		vNav.setVisibility(View.VISIBLE);
		//
		/*System.out.println(matches.getCurrentRun());
		System.out.println(matches.getRunCount());
		System.out.println(matches.getMatchCountEachRun());
		System.out.println(matches.getTeamHomes().length);
		Match match = matches.getMatches(15).get(0);
		System.out.println(match.getTime());
		System.out.println(match.getHome());
		System.out.println(match.getGuest());
		System.out.println(match.getScore());*/
		//
	}

	@Override
	public void onAsyncHttpFailure(Throwable error, String content) {
	}

}

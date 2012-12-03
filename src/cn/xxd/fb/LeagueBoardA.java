package cn.xxd.fb;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import q.util.AsyncHttpHelper;

import cn.xxd.fb.adapter.LeagueBoardAdapter;
import cn.xxd.fb.bean.Club;
import cn.xxd.fb.bean.LeagueBoard;
import cn.xxd.fb.bean.LeagueBoardParser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class LeagueBoardA extends Fragment  implements AsyncHttpHelper.OnAsyncHttpListener {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
    }
	
	List<LeagueBoard> datas;
	LeagueBoardAdapter adapter;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	if(datas == null){
    		datas = new ArrayList<LeagueBoard>();
    		new AsyncHttpHelper().get(getActivity(), "http://data2.7m.cn/matches_data/92/gb/standing.js", AsyncHttpHelper.NO_CACHE, this);
    	}
    	//
    	ListView lv = (ListView)inflater.inflate(R.layout.base_list, null);
    	adapter = new LeagueBoardAdapter(getActivity(), datas);
    	lv.setAdapter(adapter);
    	
        return lv;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("a", 1);
    }

	@Override
	public boolean onAsyncHttpVerify(String content) {
		if(content == null){
			return false;
		}
		return content.contains("var f_sds_tn");
	}

	@Override
	public void onAsyncHttpSuccess(String content) {
		List<LeagueBoard> temp = LeagueBoardParser.parse(content);
		if(temp != null){
			datas.addAll(temp);
			adapter.notifyDataSetChanged();
		}
		
	}

	@Override
	public void onAsyncHttpFailure(Throwable error, String content) {
		// TODO Auto-generated method stub
		
	}
}

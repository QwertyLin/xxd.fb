package cn.xxd.fb.adapter;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import cn.xxd.fb.R;
import cn.xxd.fb.bean.Match;
import q.base.AdapterBase;

public class MatchAdapter extends AdapterBase<Match, MatchAdapter.Holder> {
	
	public MatchAdapter(Context ctx, List<Match> datas) {
		super(ctx, datas);
		// TODO Auto-generated constructor stub
	}

	class Holder{
		View vLayout;
		TextView vTime, vHome, vScore, vGuest;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.list_item_match;
	}

	@Override
	protected Holder getHolder(View v) {
		Holder h = new Holder();
		h.vLayout = v.findViewById(R.id.match_layout);
		h.vTime = (TextView)v.findViewById(R.id.match_time);
		h.vHome = (TextView)v.findViewById(R.id.match_home);
		h.vScore = (TextView)v.findViewById(R.id.match_score);
		h.vGuest = (TextView)v.findViewById(R.id.match_guest);
		return h;
	}

	@Override
	protected void initItem(int position, Match data, Holder holder) {
		holder.vTime.setText(Html.fromHtml(data.getTime()));
		holder.vHome.setText(data.getHome());
		holder.vScore.setText(data.getScore());
		holder.vGuest.setText(data.getGuest());
	}

}

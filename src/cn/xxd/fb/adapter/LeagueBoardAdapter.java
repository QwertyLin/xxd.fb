package cn.xxd.fb.adapter;

import java.util.List;

import q.base.AdapterBase;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import cn.xxd.fb.R;
import cn.xxd.fb.bean.LeagueBoard;

public class LeagueBoardAdapter extends AdapterBase<LeagueBoard, LeagueBoardAdapter.Holder> {
	
	private int colorWhite, colorBlue;

	public LeagueBoardAdapter(Context ctx, List<LeagueBoard> datas) {
		super(ctx, datas);
		// TODO Auto-generated constructor stub
		
		colorWhite = ctx.getResources().getColor(R.color.base_white);
		colorBlue = ctx.getResources().getColor(R.color.base_gray_light);
	}

	class Holder {
		View layout;
		TextView vClub, vNumber, vWin, vDraw, vLose, vGain, vLoss, vScore;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.list_item_league_board;
	}

	@Override
	protected Holder getHolder(View v) {
		Holder h = new Holder();
		h.layout = v.findViewById(R.id.league_board_layout);
		h.vClub = (TextView)v.findViewById(R.id.league_board_club);
		h.vNumber = (TextView)v.findViewById(R.id.league_board_number);
		h.vWin = (TextView)v.findViewById(R.id.league_board_win);
		h.vDraw = (TextView)v.findViewById(R.id.league_board_draw);
		h.vLose = (TextView)v.findViewById(R.id.league_board_lose);
		h.vGain = (TextView)v.findViewById(R.id.league_board_gain);
		h.vLoss = (TextView)v.findViewById(R.id.league_board_loss);
		h.vScore = (TextView)v.findViewById(R.id.league_board_score);
		return h;
	}

	@Override
	protected void initItem(int position, LeagueBoard data, Holder holder) {
		if(position % 2 == 0){
			holder.layout.setBackgroundColor(colorWhite);
		}else{
			holder.layout.setBackgroundColor(colorBlue);
		}
		holder.vClub.setText(position + 1 + ". " + data.getClub().getName());
		holder.vNumber.setText(String.valueOf(data.getNumber()));
		holder.vWin.setText(String.valueOf(data.getWin()));
		holder.vDraw.setText(String.valueOf(data.getDraw()));
		holder.vLose.setText(String.valueOf(data.getLose()));
		holder.vGain.setText(String.valueOf(data.getGain()));
		holder.vLoss.setText(String.valueOf(data.getLoss()));
		holder.vScore.setText(String.valueOf(data.getScore()));
	}
}

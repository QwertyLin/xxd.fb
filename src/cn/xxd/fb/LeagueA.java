package cn.xxd.fb;

import cn.xxd.fb.bean.League;

import q.view.UnderlinePageIndicator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

public class LeagueA extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_layout);
		//
		((FrameLayout)findViewById(R.id.base_layout)).addView(getLayoutInflater().inflate(R.layout.layout_league, null));
		//
		ViewPager viewPager = (ViewPager)findViewById(R.id.league_pager);
		League league = new League();
		league.setId(85);
		league.setName("西甲");
		viewPager.setAdapter(new LeagueAdapter(getSupportFragmentManager(), league));
		UnderlinePageIndicator viewIndicator = (UnderlinePageIndicator)findViewById(R.id.league_indicator);
		viewIndicator.setViewPager(viewPager);
	}
}


class LeagueAdapter extends FragmentPagerAdapter {
	
	private League mLeague;

	public LeagueAdapter(FragmentManager fm, League league) {
		super(fm);
		mLeague = league;
	}

	@Override
	public Fragment getItem(int position) {
		return new LeagueBoardFragment();
	}

	@Override
	public int getCount() {
		return 3;
	}
	
}
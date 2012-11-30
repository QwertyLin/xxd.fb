package cn.xxd.fb;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class MainA extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, LeagueA.class));
        finish();
    }

}

package top.wuhaojie.scrollnumber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import top.wuhaojie.library.MultiScrollNumber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MultiScrollNumber scrollNumber = (MultiScrollNumber) findViewById(R.id.scroll_number);

        scrollNumber.setTextColors(new int[]{R.color.blue01, R.color.red01,
                R.color.green01, R.color.purple01});
//        scrollNumber.setTextSize(64);

//        scrollNumber.setNumber(64, 2048);
//        scrollNumber.setInterpolator(new DecelerateInterpolator());

        scrollNumber.setTextFont("myfont.ttf");
        scrollNumber.setNumber(2048);
    }
}

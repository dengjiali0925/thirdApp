package com.example.administrator.testapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private int mCount;
    private String[] mStrArr = {"a", "b"};
    private Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button buttonPrev = (Button) findViewById(R.id.prev);
        Button buttonNext = (Button) findViewById(R.id.next);

        final ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        viewSwitcher.setFactory(new ViewSwitcher.ViewFactory()
        {
            @Override
            public View makeView()
            {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.homepage_car_speech, null);
                return view;
            }
        });

        Animation slide_in_up = AnimationUtils.loadAnimation(this, R.anim.slide_in_up);
        Animation slide_out_up = AnimationUtils.loadAnimation(this, R.anim.slide_out_up);

        viewSwitcher.setInAnimation(slide_in_up);
        viewSwitcher.setOutAnimation(slide_out_up);

        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                // 在此处添加执行的代码
                if (mCount >= Integer.MAX_VALUE)
                {
                    mCount = mStrArr.length;
                }
                mCount++;

                String content = mStrArr[mCount % 2];
                // 显示车宝头条标题和内容
                viewSwitcher.showNext();

                if (mStrArr.length > 0)
                {
                    mHandler.postDelayed(this, 3000);// 3000是延时时长
                }
            }
        };
        mHandler.postDelayed(runnable, 3000);

        viewSwitcher.setOnClickListener(this);
        buttonPrev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 viewSwitcher.showPrevious(); // 切换效果类似
//                viewSwitcher.setDisplayedChild(0);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 viewSwitcher.showNext(); // 切换效果类似
//                viewSwitcher.setDisplayedChild(1);
            }
        });
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.viewSwitcher:
            {
                Toast.makeText(this, "这是" + mStrArr[mCount % 2], Toast.LENGTH_SHORT).show();
                break;
            }
            default:
                break;
        }
    }
}

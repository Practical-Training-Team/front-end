package com.example.englishapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.englishapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager2;
    private List<Fragment> mList;

    private TextView adviceTv, readTv, mainTv;
    private ImageView advice, read, main;
    private MyListener myListener;
    private FragmentAdapter fragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.main_activity);
        myListener = new MyListener();
        viewPager2.addOnPageChangeListener(myListener);
        advice = findViewById(R.id.advice_page);
        read = findViewById(R.id.read_page1);
        main = findViewById(R.id.main_page);

        adviceTv = findViewById(R.id.advice_tv);
        readTv = findViewById(R.id.practice_tv);
        mainTv = findViewById(R.id.main_tv);

        mList = new ArrayList<>();
        mList.add(new AdviceFragment());
        mList.add(new ReadFragment());
        mList.add(new MineFragment());

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mList);
        viewPager2.setAdapter(fragmentAdapter);
        viewPager2.setCurrentItem(0);
        viewPager2.setOffscreenPageLimit(2);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.advice_page) {
            viewPager2.setCurrentItem(0);
            advice.setBackgroundResource(R.drawable.baseline_collections_bookmark_24);
            read.setBackgroundResource(R.drawable.baseline_contactless_24_grey);
            main.setBackgroundResource(R.drawable.baseline_account_box_24_grey);
            adviceTv.setTextColor(Color.parseColor("#000000"));
            readTv.setTextColor(Color.parseColor("#CCCCCC"));
            mainTv.setTextColor(Color.parseColor("#CCCCCC"));
        }
        else if (view.getId() == R.id.read_page1) {
            viewPager2.setCurrentItem(1);
            advice.setBackgroundResource(R.drawable.baseline_collections_bookmark_24_grey);
            read.setBackgroundResource(R.drawable.baseline_contactless_24);
            main.setBackgroundResource(R.drawable.baseline_account_box_24_grey);
            adviceTv.setTextColor(Color.parseColor("#CCCCCC"));
            readTv.setTextColor(Color.parseColor("#000000"));
            mainTv.setTextColor(Color.parseColor("#CCCCCC"));
        }
        else if (view.getId() == R.id.main_page) {
            viewPager2.setCurrentItem(2);
            advice.setBackgroundResource(R.drawable.baseline_collections_bookmark_24_grey);
            read.setBackgroundResource(R.drawable.baseline_contactless_24_grey);
            main.setBackgroundResource(R.drawable.baseline_account_box_24);
            adviceTv.setTextColor(Color.parseColor("#CCCCCC"));
            readTv.setTextColor(Color.parseColor("#CCCCCC"));
            mainTv.setTextColor(Color.parseColor("#000000"));
        }
    }

    public class MyListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            switch (position) {
                case 0:
                    advice.setBackgroundResource(R.drawable.baseline_collections_bookmark_24);
                    read.setBackgroundResource(R.drawable.baseline_contactless_24_grey);
                    main.setBackgroundResource(R.drawable.baseline_account_box_24_grey);
                    adviceTv.setTextColor(Color.parseColor("#000000"));
                    readTv.setTextColor(Color.parseColor("#CCCCCC"));
                    mainTv.setTextColor(Color.parseColor("#CCCCCC"));
                    break;

                case 1:
                    advice.setBackgroundResource(R.drawable.baseline_collections_bookmark_24_grey);
                    read.setBackgroundResource(R.drawable.baseline_contactless_24);
                    main.setBackgroundResource(R.drawable.baseline_account_box_24_grey);
                    adviceTv.setTextColor(Color.parseColor("#CCCCCC"));
                    readTv.setTextColor(Color.parseColor("#000000"));
                    mainTv.setTextColor(Color.parseColor("#CCCCCC"));
                    break;

                case 2:
                    advice.setBackgroundResource(R.drawable.baseline_collections_bookmark_24_grey);
                    read.setBackgroundResource(R.drawable.baseline_contactless_24_grey);
                    main.setBackgroundResource(R.drawable.baseline_account_box_24);
                    adviceTv.setTextColor(Color.parseColor("#CCCCCC"));
                    readTv.setTextColor(Color.parseColor("#CCCCCC"));
                    mainTv.setTextColor(Color.parseColor("#000000"));
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


}
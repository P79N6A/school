package com.idyoga.yoga.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.view.HorizontalCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.devkit.library.Logcat;

/**
 * 测试一些效果,
 */
public class TextActivity extends AppCompatActivity {

    /*@BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private int data = 0;
    private String mS;
    private String mYear;
    private CalendarAdapter mAdapter;
    private static Integer sDataNum = 0;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        //ButterKnife.bind(this);

        /*long timeMillis = System.currentTimeMillis();
        String time = DateUtils.time(String.valueOf(timeMillis / 1000));
        Logcat.i("---------------time-----------" + time);
        String[] split = time.split("-");*/

        /*mS = split[1];
        mYear = split[0];
        mTvTime.setText(mYear + " 年 " + mS + " 月");
        getDataNum(mS, mYear);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAdapter = new CalendarAdapter();
        mRlv.setLayoutManager(layoutManager);
        mRlv.setAdapter(mAdapter);
        sDataNum = getDataNum(mS, mYear);
        mAdapter.setDataNum(sDataNum);*/

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        /*ListView lv = findViewById(R.id.lv);
        IvAdapter ivAdapter = new IvAdapter(this);
        lv.setAdapter(ivAdapter);*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    /*private int getDataNum(String s, String year) {
        switch (Integer.valueOf(s)){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return data = 31;
            case 2:
                if (Integer.valueOf(year) % 400 == 0 ){
                    return data = 29;
                }else if(Integer.valueOf(year) % 100 != 0 && Integer.valueOf(year) % 4 == 0){
                    return data = 29;
                }else{
                    return data = 28;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                return data = 30;
        }
        return -1;
    }

    @OnClick({R.id.iv_left, R.id.iv_right})
    public void onViewClicked(View view) {
        String timeStr = mTvTime.getText().toString();
        String sub = timeStr.substring(0, 4);
        switch (view.getId()) {
            case R.id.iv_left:
                Integer s = Integer.valueOf(mS);
                s -= 1;
                if (s < 1){
                    s = 12;
                    mYear = String.valueOf(Integer.valueOf(sub) - 1);
                }
                mS = String.valueOf(s);
                sDataNum = getDataNum(String.valueOf(s), mYear);
                mTvTime.setText(mYear + " 年 " + s + " 月");
                mAdapter.setDataNum(sDataNum);
                break;
            case R.id.iv_right:
                Integer ss = Integer.valueOf(mS);
                ss += 1;
                if (ss > 12){
                    ss = 1;
                    mYear = String.valueOf(Integer.valueOf(sub) + 1);
                }
                mS = String.valueOf(ss);
                sDataNum = getDataNum(String.valueOf(ss), mYear);
                mTvTime.setText(mYear + " 年 " + ss + " 月");
                mAdapter.setDataNum(sDataNum);
                break;
        }
    }*/

    class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder>{

        private Integer mDataNum = 0;

        public void setDataNum(Integer dataNum) {
            mDataNum = dataNum;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(TextActivity.this).inflate(R.layout.aa_calendar_item_layout,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mDataNum.setText(position + 1 + "");
        }

        @Override
        public int getItemCount() {
            return mDataNum;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView mDataNum;

            public ViewHolder(View itemView) {
                super(itemView);
                mDataNum = itemView.findViewById(R.id.tv_calendar_num);
            }
        }
    }
}

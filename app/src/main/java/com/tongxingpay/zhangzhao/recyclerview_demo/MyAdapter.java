package com.tongxingpay.zhangzhao.recyclerview_demo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Px;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView.LayoutParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhao on 2016/9/29.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Actor> actors;
    private Context mContent;
    private View currentView;
    private int counts = 0;
    private List<RelativeLayout> relativesArr;
    private List<LinearLayout> subViewsArr;
    private List<Boolean> clickedArr;

    public MyAdapter(Context context, List<Actor> list) {
        this.actors = list;
        this.mContent = context;
        relativesArr = new ArrayList<RelativeLayout>();
        subViewsArr = new ArrayList<LinearLayout>();
        clickedArr = new ArrayList<Boolean>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 给ViewHolder设置布局文件
        currentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(currentView);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Actor actor = actors.get(position);
        holder.mTextView.setText(actor.actorName);
        holder.mImageView.setImageDrawable(mContent.getDrawable(actor.getImageResourceId(mContent)));

        // 存储布局对象
        RelativeLayout layout = (RelativeLayout) currentView.findViewById(R.id.card_relative);
        relativesArr.add(layout);

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedArr.set(position, !clickedArr.get(position));
                if (clickedArr.get(position)) {
                    Log.i("Card", "onClick: clicked " + clickedArr.get(position));
                    Log.i("Card", "onClick: " + "position  " + position);

                    TextView showView = new TextView(currentView.getContext());
                    showView.setTextColor(Color.WHITE);
                    showView.setText("新增的位置:" + position + ", 高度增加一倍");

                    // 在线性布局或者相对布局动态设置宽高时，要先看有没有外层布局，
                    // 如果有要看外层布局是什么，是LinearLayout，则要用LinearLayout.LayoutParams，
                    // 如果是RelativeLayout则要用RelativeLayout.LayoutParams，
                    // 而不是看你要控制的是什么布局，如果外层没有布局文件则要用FrameLayout.LayoutParams。
                    FrameLayout.LayoutParams linearParams =  (FrameLayout.LayoutParams)relativesArr.get(position).getLayoutParams();
                    linearParams.height = 200;
                    relativesArr.get(position).setLayoutParams(linearParams);


                    // 如果父视图是RelativeLayout 或者 FrameLayout，无法动态添加控件，
                    // 解决的办法是新建一个LinearLayout，然后把textView添加给它，
                    // 再把这个LinearLayout添加给父视图：
                    LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, 40);
                    param1.setMargins(20, 120, 0, 0);
                    showView.setLayoutParams(param1);

                    LinearLayout linear = new LinearLayout(currentView.getContext());
                    linear.setOrientation(LinearLayout.VERTICAL);
                    linear.addView(showView);

                    subViewsArr.set(position, linear);
                    relativesArr.get(position).addView(linear);




                } else {
                    Log.i("Card", "onClick: cuo " + clickedArr.get(position));
                    relativesArr.get(position).removeView(subViewsArr.get(position));
                    FrameLayout.LayoutParams linearParams =  (FrameLayout.LayoutParams)relativesArr.get(position).getLayoutParams();
                    linearParams.height = 100;
                    relativesArr.get(position).setLayoutParams(linearParams);

                }
            }
        });
    }



    @Override
    public int getItemCount() {

        if (actors != null) {

            for (int i = 0; i < actors.size(); i++) {
                clickedArr.add(false);
                subViewsArr.add(null);
            }
        }
        return actors == null ? 0 : actors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView mImageView;
        public TextView mShowView;

        public ViewHolder(View v) {
            super(v);

            mImageView = (ImageView)v.findViewById(R.id.card_imageview);
            mTextView = (TextView)v.findViewById(R.id.card_textview);
            mShowView = (TextView)v.findViewById(R.id.card_whiteview);

        }


    }
}

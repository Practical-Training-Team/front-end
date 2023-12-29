package com.example.englishapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.DataBean;
import com.example.englishapp.R;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

public class AdviceFragment extends Fragment {
    private static final String TAG = "123456";

    private Banner banner;
    private RecyclerView recyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advice, container, false);
        banner = view.findViewById(R.id.banner);
        recyclerView = view.findViewById(R.id.advice_rv);

        ImageAdapter imageAdapter = new ImageAdapter(DataBean.getTestData2());
        //加载本地图片
        banner.setAdapter(imageAdapter)
                .addBannerLifecycleObserver(this)
                .setIndicator(new CircleIndicator(this.getActivity()))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        Snackbar.make(banner, ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                        Log.i(TAG, "position: " + position);
                    }
                });

        return view;
    }

    public class ImageAdapter extends BannerAdapter<DataBean, ImageAdapter.ImageHolder> {
        public ImageAdapter(List<DataBean> datas) {
            super(datas);
        }

        //更新数据
        public void updateData(List<DataBean> data) {
            mDatas.clear();
            mDatas.addAll(data);
            notifyDataSetChanged();
        }

        //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
        @Override
        public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(parent.getContext());
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return new ImageHolder(imageView);
        }

        @Override
        public void onBindView(ImageHolder holder, DataBean data, int position, int size) {
            holder.imageView.setImageResource(data.imageRes);
        }

        public static class ImageHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;

            public ImageHolder(@NonNull View itemView) {
                super(itemView);
                this.imageView = (ImageView) itemView;
            }
        }
    }
}

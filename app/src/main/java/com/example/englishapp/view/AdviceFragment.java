package com.example.englishapp.view;

import static com.example.englishapp.view.LoginActivity.KEY_INT_VALUE;
import static com.example.englishapp.view.SearchActivity.PREF_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.englishapp.AdviceAdapter;
import com.example.englishapp.NetUtil;
import com.example.englishapp.R;
import com.example.englishapp.databean.Article;
import com.example.englishapp.databean.DataBean;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdviceFragment extends Fragment {
    private static final String TAG = "123456";
    private List<Article> list = new ArrayList<>();
    private Banner banner;
    private RecyclerView recyclerView;
    private ImageView search;
    private List<DataBean> beanList = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advice, container, false);
        banner = view.findViewById(R.id.banner);
        search = view.findViewById(R.id.search_im);
        search.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });
        recyclerView = view.findViewById(R.id.advice_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initList();
        getHotList();
        return view;
    }

    private void initList() {
        SharedPreferences preferences = getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int id =  preferences.getInt(KEY_INT_VALUE, 0);
        NetUtil.getInstance().getApi().getAdviceList(id).enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.body() != null) {
                    list.addAll(response.body());
                    recyclerView.setAdapter(new AdviceAdapter(list, getActivity()));
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {

            }
        });
    }

    private void getHotList() {
        NetUtil.getInstance().getApi().getSearchHotList().enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                for(int i =0; i < response.body().size(); i++) {
                    beanList.add(new DataBean(response.body().get(i).getImage(), null, 1));
                }
                ImageAdapter imageAdapter = new ImageAdapter(beanList);
                //加载本地图片
                banner.setAdapter(imageAdapter)
                        .addBannerLifecycleObserver(getActivity())
                        .setIndicator(new CircleIndicator(getActivity()))
                        .setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(Object data, int position) {
                                Snackbar.make(banner, ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                                Log.i(TAG, "position: " + position);
                            }
                        });
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {

            }
        });
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
            Glide.with(getActivity()).load(data.imageUrl).into(holder.imageView);
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

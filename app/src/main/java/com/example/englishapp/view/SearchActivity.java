package com.example.englishapp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.englishapp.HistoryAdapter;
import com.example.englishapp.HotRankAdapter;
import com.example.englishapp.NetUtil;
import com.example.englishapp.R;
import com.example.englishapp.databean.AdviceItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    public static final String PREF_NAME = "MyPrefs";
    private static final int MAX_HISTORY_SIZE = 10;
    private static final String HISTORY_KEY = "history_key";
    private RecyclerView history, hot;
    private EditText search;
    private List<String> historyList = new ArrayList<>();
    private List<AdviceItem> hotList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_search);
        initView();
        initListener();
    }

    private void initListener() {

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String query = search.getText().toString();
                    addToSearchHistory(query);
                    Search();
                }
                return false;
            }
        });

        NetUtil.getInstance().getApi().getSearchHotList().enqueue(new Callback<List<AdviceItem>>() {
            @Override
            public void onResponse(Call<List<AdviceItem>> call, Response<List<AdviceItem>> response) {
                assert response.body() != null;
                hotList.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<AdviceItem>> call, Throwable t) {

            }
        });
        hot.setAdapter(new HotRankAdapter(hotList, this));
    }

    private void initView() {
        search = findViewById(R.id.search_et);
        history = findViewById(R.id.history_rv);
        hot = findViewById(R.id.hot_rv);
        history.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));
        hot.setLayoutManager(new LinearLayoutManager(this));
        historyList = loadSearchHistory();
        history.setAdapter(new HistoryAdapter(historyList));
    }

    private ArrayList<String> loadSearchHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> historySet = sharedPreferences.getStringSet(HISTORY_KEY, new HashSet<>());
        return new ArrayList<>(historySet);
    }

    private void addToSearchHistory(String query) {
        if (historyList.size() >= MAX_HISTORY_SIZE) {
            // 如果搜索历史达到最大数量，移除最旧的记录
            historyList.remove(historyList.size() - 1);
        }

        // 添加新的搜索记录到列表
        historyList.add(0, query);

        history.setAdapter(new HistoryAdapter(historyList));
        // 持久化存储搜索历史
        saveSearchHistory();
    }

    private void saveSearchHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> historySet = new HashSet<>(historyList);
        editor.putStringSet(HISTORY_KEY, historySet);
        editor.apply();
    }

    private void Search() {

    }

}

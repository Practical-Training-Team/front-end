package com.example.englishapp.view;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.PracticeAdapter;
import com.example.englishapp.R;
import com.example.englishapp.databean.Type;

import java.util.ArrayList;
import java.util.List;

public class PracticeFragment extends Fragment {

    private List<Type> list = new ArrayList<>();
    private RecyclerView recyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_practice, container, false);
        init();
        recyclerView = view.findViewById(R.id.practice_rv);
        recyclerView.setAdapter(new PracticeAdapter(list, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        return view;
    }

    private void init() {
        list.add(new Type(4, "一种开放、灵活的心态，通过独特的观点和创意的组合，寻找新颖解决方案并推动进步。"
                , BitmapFactory.decodeResource(getResources(), R.mipmap.innovation), "创新思维"));

        list.add(new Type(1, "每一次旅程都是一段难忘的故事，留下的不仅是风景的美好，更是内心深处的成长与回忆。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.trip), "旅行见闻"));

        list.add(new Type(3, "国家采取组织、装备和战略手段，维护国家安全、捍卫主权，并在面临威胁时采取行动保障国家的独立和稳定。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.military), "国防军事"));

        list.add(new Type(2, "个体通过观察和经历，获取对社会各方面事物、现象和文化的广泛了解和认知。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.society), "社会见闻"));

        list.add(new Type(5, "采取可持续的行动，维护和平衡自然系统，以减少人类活动对生态系统的负面影响，确保地球资源的可持续利用和生态平衡的持续稳定。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.enviroment), "生态环境"));

        list.add(new Type(0, "通过良好的生活习惯和行为选择，促进身体、心理和社交的整体健康，以实现长期幸福和生活品质的提升。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.healthy), "养生健康"));
    }
}

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
        list.add(new Type(0, "日常生活是人们在日复一日中进行的一系列活动和经历，包括休闲、社交、个人护理等方面。"
                , BitmapFactory.decodeResource(getResources(), R.mipmap.life), "日常生活"));

        list.add(new Type(1, "每一次旅程都是一段难忘的故事，留下的不仅是风景的美好，更是内心深处的成长与回忆。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.trip), "旅行见闻"));

        list.add(new Type(2, "无论是在大银幕上感受震撼的视觉效果，还是在耳边享受动人的音乐旋律，影音娱乐都为生活增添了无尽的乐趣和情感体验。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.macro), "影音娱乐"));

        list.add(new Type(3, "网络科技推动着信息的高速传递，连接了世界各地的人们，使得跨越时空的沟通变得轻松而便利。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.net), "网络科技"));

        list.add(new Type(4, "商业职场是充满竞争与机遇的舞台，汇聚着各类专业人才和企业精英，人们通过合作与竞争塑造着商业格局，追求创新、效益和可持续发展。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.job), "商业职场"));

        list.add(new Type(5, "体育运动是一项融合了竞技、健康和团队协作的活动，是人们通过运动来强身健体、锻炼意志和享受乐趣的重要方式。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.sport), "体育运动"));
    }
}

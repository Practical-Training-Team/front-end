package com.example.englishapp.view;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;

import java.util.List;

public class PracticeFragment extends Fragment {

    private List<Type> list;
    private RecyclerView recyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_practice, container, false);
        init();
        recyclerView = view.findViewById(R.id.practice_rv);

        return view;
    }

    private void init() {
        list.add(new Type(0, "日常生活是人们在日复一日中进行的一系列活动和经历，包括休闲、社交、个人护理等方面。每个人的日常生活都有独特之处，受到职业、兴趣、家庭和个人选择等因素的影响。"
                , BitmapFactory.decodeResource(getResources(), R.mipmap.life), "日常生活"));

        list.add(new Type(1, "在旅途中，我们穿越风景宜人的山川河流，感受不同地域的文化与风俗，品味异国的美食，结交来自各地的新朋友。每一次旅程都是一段难忘的故事，留下的不仅是风景的美好，更是内心深处的成长与回忆。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.trip), "旅行见闻"));

        list.add(new Type(2, "影音娱乐不仅是一种消遣方式，更是一种文化交流的桥梁，让人们跨越时空和地域，共享全球各地丰富多元的艺术表达。无论是在大银幕上感受震撼的视觉效果，还是在耳边享受动人的音乐旋律，影音娱乐都为生活增添了无尽的乐趣和情感体验。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.macro), "影音娱乐"));

        list.add(new Type(3, "网络科技推动着信息的高速传递，连接了世界各地的人们，使得跨越时空的沟通变得轻松而便利。网络科技推动了创新，催生了无数互联网企业和数字化产品，从而深刻影响着商业、教育、医疗、娱乐等各个领域。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.net), "网络科技"));

        list.add(new Type(4, "商业职场是充满竞争与机遇的舞台，汇聚着各类专业人才和企业精英。在这个环境中，人们通过合作与竞争塑造着商业格局，追求创新、效益和可持续发展。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.job), "商业职场"));

        list.add(new Type(5, "体育运动是一项融合了竞技、健康和团队协作的活动，是人们通过运动来强身健体、锻炼意志和享受乐趣的重要方式。体育运动激发了人们的激情和活力，不仅促进了身体素质的提升，也培养了团队合作和竞争意识。",
                BitmapFactory.decodeResource(getResources(), R.mipmap.sport), "体育运动"));
    }
}

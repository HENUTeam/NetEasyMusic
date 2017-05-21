package t3.henu.left_library.YHQ_solve;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import t3.henu.left_library.R;

/**
 * Created by 117 on 2017/5/18.
 */

public class BillboardActivity extends AppCompatActivity {
    private List<BillboardListInfo> mBillboardlist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yhq_activity_billboard);
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.yhq_billboard_list);
        init();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        BillboardAdapter adapter=new BillboardAdapter(mBillboardlist);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void init() {
        mBillboardlist = AppCache.getSongListInfos();
        if (mBillboardlist.isEmpty()) {
            String[] titles = getResources().getStringArray(R.array.yhq_online_music_list_title);
            String[] types = getResources().getStringArray(R.array.yhq_online_music_list_type);
            for (int i = 0; i < titles.length; i++) {
                BillboardListInfo info = new BillboardListInfo();
                info.setTitle(titles[i]);
                info.setType(types[i]);
                mBillboardlist.add(info);
            }
        }
    }
}

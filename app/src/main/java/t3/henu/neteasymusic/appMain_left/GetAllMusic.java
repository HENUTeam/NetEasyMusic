package t3.henu.neteasymusic.appMain_left;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;


import t3.henu.neteasymusic.R;

/**
 * Created by 高逸博 on 2017/4/12.
 */

public class GetAllMusic extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_main_left_liebiao,container,false);
        recyclerView= (RecyclerView) recyclerView.findViewById(R.id.id_main_frament_left_liebiao_recyclerview);
        return rootView;
    }
}

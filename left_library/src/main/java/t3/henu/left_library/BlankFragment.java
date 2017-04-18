package t3.henu.left_library;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 高逸博 on 2017/4/1.
 */

public class BlankFragment extends Fragment {

    private TextView textView;
    private View rootView;
    private String text="";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public BlankFragment(String text){
        this.text=text;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_blank,container,false);
        textView= (TextView) rootView.findViewById(R.id.id_blankFragment_textview);
        textView.setText(text);
        return rootView;
    }
}


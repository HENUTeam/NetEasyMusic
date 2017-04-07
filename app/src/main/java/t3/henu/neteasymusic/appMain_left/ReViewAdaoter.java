package t3.henu.neteasymusic.appMain_left;

import android.support.v7.widget.*;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

import t3.henu.neteasymusic.*;

/**
 * Created by 高逸博 on 2017/4/7.
 */

public class ReViewAdaoter extends RecyclerView.Adapter<> {

    public enum TYPE{
        ITEM,ITEM_DIVID;
    }
    private LayoutInflater inflater;
    private List<RecyclerData> datas;


    public ReViewAdaoter(MainActivity mainActivity, List<RecyclerData> recView_datas) {
        this.datas=recView_datas;
        inflater=LayoutInflater.from(mainActivity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if(position==5||position==8){
            return TYPE.ITEM_DIVID.ordinal();
        }
        return TYPE.ITEM.ordinal();
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }


    private class itemViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;
        public itemViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.id_appmain_drawelayout_left_recyclerview1_item_imageview);
            text= (TextView) itemView.findViewById(R.id.id_appmain_drawelayout_left_recyclerview1_item_textview);
        }
    }

    private class dividViewHolder
}

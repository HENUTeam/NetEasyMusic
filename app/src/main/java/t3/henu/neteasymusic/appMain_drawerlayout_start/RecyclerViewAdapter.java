package t3.henu.neteasymusic.appMain_drawerlayout_start;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import t3.henu.neteasymusic.R;

/**
 * Created by 高逸博 on 2017/4/12.
 */

public class RecyclerViewAdapter extends BaseMultiItemQuickAdapter<RecyclerData> {


    public RecyclerViewAdapter(List<RecyclerData> data) {
        super(data);
       addItemType(RecyclerData.ITEM,R.layout.activity_main_drawerlayout_left_recyclerview_item);
        addItemType(RecyclerData.DIVIDE,R.layout.activity_main_drawerlayout_left_recyclerview_divide);
        addItemType(RecyclerData.HEADER,R.layout.activity_main_drawerlayout_left_header);
    }

    @Override
    protected int getDefItemViewType(int position) {
        if(position==0){
            return RecyclerData.HEADER;
        }
        else if(position==6||position==9){
            return RecyclerData.DIVIDE;
        }else{
            return RecyclerData.ITEM;
        }
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RecyclerData recyclerData) {
        switch (baseViewHolder.getItemViewType()){
            case RecyclerData.ITEM:
                baseViewHolder.setBackgroundRes(R.id.id___linerlayout,R.drawable.activity_main_drawerlayout_left_recyclerview_item_bg);
                baseViewHolder.setText(R.id.id_appmain_drawelayout_left_recyclerview1_item_textview,recyclerData.getText());
                baseViewHolder.setImageResource(R.id.id_appmain_drawelayout_left_recyclerview1_item_imageview,recyclerData.getImage());
                break;
            case RecyclerData.HEADER:
                baseViewHolder.setOnClickListener(R.id.id_appmain_drawelayout_left_btn_signin,
                        new OnItemChildClickListener());
                baseViewHolder.setOnClickListener(R.id.id_appmain_drawelayout_left_btn_qiandao,
                        new OnItemChildClickListener());
                break;
        }
        }
}

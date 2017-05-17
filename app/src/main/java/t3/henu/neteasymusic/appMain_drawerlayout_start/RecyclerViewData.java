package t3.henu.neteasymusic.appMain_drawerlayout_start;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by 高逸博 on 2017/5/3.
 */

public class RecyclerViewData extends MultiItemEntity{
    public final static int TYPE1=1;
    public final static int TYPE2=2;
    public final static int TYPE3=3;
    public int Image;
    public String Text;
    public RecyclerViewData(int Im,String text){
        this.Image=Im;
        this.Text=text;
    }
}

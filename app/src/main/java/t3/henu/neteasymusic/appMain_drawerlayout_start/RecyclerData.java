package t3.henu.neteasymusic.appMain_drawerlayout_start;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by 高逸博 on 2017/4/7.
 */

public class RecyclerData extends MultiItemEntity {
    public static final int HEADER=0;
    public static final int ITEM=1;
    public static final int DIVIDE=2;
    private int image;
    private String text;
    public RecyclerData(int image,String text){
        this.image=image;
        this.text=text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

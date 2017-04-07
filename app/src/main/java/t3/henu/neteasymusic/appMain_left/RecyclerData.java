package t3.henu.neteasymusic.appMain_left;

/**
 * Created by 高逸博 on 2017/4/7.
 */

public class RecyclerData {

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

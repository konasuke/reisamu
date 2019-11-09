package android.wings.websarva.samuraispirits2019_capture;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;



public class TestAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int layoutID;
    private Bitmap[] photolist;

    static class ViewHolder {
        ImageView img;
    }

    TestAdapter(Context context, int itemLayoutId, int[] photos ){

        inflater = LayoutInflater.from(context);
        layoutID = itemLayoutId;

        // bitmapの配列
        photolist = new Bitmap[photos.length];
        // drawableのIDからbitmapに変換
        for( int i = 0; i< photos.length; i++){
            photolist[i] = BitmapFactory.decodeResource(context.getResources(), photos[i]);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(layoutID, null);
            holder = new ViewHolder();
            holder.img = convertView.findViewById(R.id.img_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.img.setImageBitmap(photolist[position]);

        return convertView;
    }

    @Override
    public int getCount() {
        return photolist.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
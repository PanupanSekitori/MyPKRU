package appewtc.masterung.mypkru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by masterUNG on 5/26/2017 AD.
 */

public class FriendAdapter extends BaseAdapter{

    private Context context;
    private String[] nameStrings, imageStrings;

    public FriendAdapter(Context context,
                         String[] nameStrings,
                         String[] imageStrings) {
        this.context = context;
        this.nameStrings = nameStrings;
        this.imageStrings = imageStrings;
    }

    @Override
    public int getCount() {
        return nameStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.friend_listview, viewGroup, false);

        //Initial View
        TextView textView = (TextView) view1.findViewById(R.id.txtNameFriend);
        ImageView imageView = (ImageView) view1.findViewById(R.id.imvHumen);

        //Show View
        textView.setText(nameStrings[i]);



        return null;
    }
}   // Main Class

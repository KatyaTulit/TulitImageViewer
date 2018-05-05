package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tulit.tulitimageviewer.R;

import java.util.List;

import Model.ListItem;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private Context context;
    private List<ListItem> listItems;

    public MyAdapter(Context context, List<ListItem> listItems) {
        this.context = context;
        this.listItems = listItems;

    }

    // Inflates our list_item.xml file
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    // Here we bind our views to the data source and the RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem item = listItems.get(position);
        holder.name.setText(item.getName());
    }

    // Gives us the count of listItems that we have
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    // Holds all the items that we have in the list_item.xml
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameID);
            image = itemView.findViewById(R.id.imageID);
        }
    }
}

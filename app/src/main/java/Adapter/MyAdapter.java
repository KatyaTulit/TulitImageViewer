package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tulit.tulitimageviewer.DetailsActivity;
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
        Picasso.get()
                .load(item.getImageUrl())
                .placeholder(R.drawable.loading) // Use a placeholder until download is finished
                .fit() // Resize the image to match imageView constraints
                .centerCrop()
                .noFade() // When the image is loaded, it is displayed right away
                .into(holder.image);
    }

    // Gives us the count of listItems that we have
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    // Holds all the items that we have in the list_item.xml
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this); // make the item clickable

            image = itemView.findViewById(R.id.imageID);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // Getting the position of the item clicked
            ListItem item = listItems.get(position);

            // Here we create an intent and put some extras: photographer name and the image url
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("name", item.getName());
            intent.putExtra("url", item.getImageUrl());

            context.startActivity(intent);
        }
    }
}

package developersancho.paginglibraryapp;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemViewHolder> {

    private Context context;

    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.answer_id == newItem.answer_id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.equals(newItem);
        }
    };

    protected ItemAdapter(Context ctx) {
        super(DIFF_CALLBACK);
        this.context = ctx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int pos) {
        Item item = getItem(pos);
        if (item != null) {
            Glide.with(context).load(item.owner.profile_image).into(holder.profile_image);
            holder.text_name.setText(item.owner.display_name);
        } else {
            Toast.makeText(context, "Item is null", Toast.LENGTH_SHORT).show();
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_image;
        TextView text_name;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.imageView);
            text_name = itemView.findViewById(R.id.textViewName);
        }
    }
}

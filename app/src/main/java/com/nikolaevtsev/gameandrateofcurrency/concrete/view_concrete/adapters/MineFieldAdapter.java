package com.nikolaevtsev.gameandrateofcurrency.concrete.view_concrete.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nikolaevtsev.gameandrateofcurrency.R;
import com.nikolaevtsev.gameandrateofcurrency.abstract_.model_abstract.pojo.MineFieldInfo;
import com.nikolaevtsev.gameandrateofcurrency.concrete.model_concrete.utils.DrawableBuild;

import java.util.List;

public class MineFieldAdapter extends RecyclerView.Adapter<MineFieldAdapter.MineFieldViewHolder> {

    private OnMineFieldFragmentClickListener onMineFieldFragmentClickListener;
    private List<MineFieldInfo> fragments;
    private int countOfColumn;
    private int line;

    public MineFieldAdapter(int countOfColumn) {
        this.countOfColumn = countOfColumn;
        line = countOfColumn - 1;
    }

    public List<MineFieldInfo> getFragments() {
        return fragments;
    }

    public void setFragments(List<MineFieldInfo> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public interface OnMineFieldFragmentClickListener {
        void onMineFieldFragmentClick(int position);
    }

    public void setOnMineFieldFragmentClickListener(OnMineFieldFragmentClickListener
                                                            onMineFieldFragmentClickListener) {
        this.onMineFieldFragmentClickListener = onMineFieldFragmentClickListener;
    }

    @NonNull
    @Override
    public com.nikolaevtsev.gameandrateofcurrency.concrete.view_concrete.adapters.
            MineFieldAdapter.MineFieldViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                    int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent,
                false);
        return new MineFieldViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.nikolaevtsev.gameandrateofcurrency.concrete.
            view_concrete.adapters.MineFieldAdapter.MineFieldViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    public class MineFieldViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewMinefieldFragment;

        public MineFieldViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMinefieldFragment = itemView.findViewById(R.id.imageViewMinefieldFragment);
            imageViewMinefieldFragment.setImageDrawable(DrawableBuild.getDrawable
                            (itemView.getContext(), R.drawable.question));
            imageViewMinefieldFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onMineFieldFragmentClickListener != null &&
                            getAdapterPosition() / countOfColumn == line) {
                        line--;
                        imageViewMinefieldFragment.setImageDrawable
                                (fragments.get(getAdapterPosition()).getImage());
                        onMineFieldFragmentClickListener.onMineFieldFragmentClick
                                (getAdapterPosition());
                    }
                }
            });
        }

    }

}

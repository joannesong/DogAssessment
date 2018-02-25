package nyc.c4q.dogassessment.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.dogassessment.R;
import nyc.c4q.dogassessment.model.RandoPuppies;
import nyc.c4q.dogassessment.model.RandoPuppy;
import nyc.c4q.dogassessment.viewholder.DogViewHolder;

/**
 * Created by joannesong on 2/25/18.
 */

public class DogAdapter extends RecyclerView.Adapter<DogViewHolder> {
    List<String> puppyList = new ArrayList<>();

    public DogAdapter(List<String> puppyList) {
        this.puppyList = puppyList;
    }

    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_itemview, parent, false);

        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DogViewHolder holder, int position) {
        holder.onBind(puppyList.get(position));

    }

    @Override
    public int getItemCount() {
        return puppyList.size();
    }


}

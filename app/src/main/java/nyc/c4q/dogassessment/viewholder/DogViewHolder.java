package nyc.c4q.dogassessment.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import nyc.c4q.dogassessment.BreedsActivity;
import nyc.c4q.dogassessment.LoginActivity;
import nyc.c4q.dogassessment.PhotoActivity;
import nyc.c4q.dogassessment.R;
import nyc.c4q.dogassessment.model.RandoPuppies;
import nyc.c4q.dogassessment.model.RandoPuppy;

/**
 * Created by joannesong on 2/25/18.
 */

public class DogViewHolder extends RecyclerView.ViewHolder {
    private ImageView dogImage;
    public DogViewHolder(View itemView) {
        super(itemView);

        dogImage = itemView.findViewById(R.id.image_itemview);
    }
    public void onBind(final String str){
        Picasso.with(itemView.getContext())
                .load(str)
                .into(dogImage);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), PhotoActivity.class);
                intent.putExtra("url", str);
                itemView.getContext().startActivity(intent);
            }
        });

    }
}

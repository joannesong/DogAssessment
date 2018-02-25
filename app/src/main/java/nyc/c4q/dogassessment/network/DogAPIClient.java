package nyc.c4q.dogassessment.network;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nyc.c4q.dogassessment.controller.DogAdapter;
import nyc.c4q.dogassessment.model.RandoPuppies;
import nyc.c4q.dogassessment.model.RandoPuppy;
import nyc.c4q.dogassessment.service.PuppyService;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joannesong on 2/25/18.
 */

public class DogAPIClient implements Callback<RandoPuppies>{

    final static String BASE_URL = "https://dog.ceo/";
    RandoPuppies randoPuppies;
    List<String> puppyList = new ArrayList<>();
    RecyclerView recyclerView;
    PuppyService puppyService;

    public DogAPIClient(RecyclerView recycler){
        recyclerView = recycler;
    }

    public void start(String dog){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        puppyService = retrofit.create(PuppyService.class);
//        Call<RandoPuppies> call = puppyService.getPuppy();
//        call.enqueue(this);

        switch(dog){
            case "terriers":
                terrier();
                break;
                case "spaniel":
                spaniel();
                break;
            case "poodle":
                poodle();
                break;
            case "retriever":
                retriever();
                break;
            default:
                Log.d("fail", "fail");
        }

    }

    public void terrier(){
        Call<RandoPuppies> call1 = puppyService.getPuppy();
        call1.enqueue(this);
    }
    public void spaniel(){
        Call<RandoPuppies> call2 = puppyService.getSpaniels();
        call2.enqueue(this);
    }
    public void poodle(){
        Call<RandoPuppies> call3 = puppyService.getPoodles();
        call3.enqueue(this);
    }
    public void retriever(){
        Call<RandoPuppies> call4 = puppyService.getRetreivers();
        call4.enqueue(this);
    }


    @Override
    public void onResponse(Call<RandoPuppies> call, Response<RandoPuppies> response) {
        randoPuppies = response.body();
        puppyList = randoPuppies.getMessage();
        DogAdapter dogAdapter = new DogAdapter(puppyList);
        recyclerView.setAdapter(dogAdapter);
    }

    @Override
    public void onFailure(Call<RandoPuppies> call, Throwable t) {
        t.printStackTrace();
    }
}

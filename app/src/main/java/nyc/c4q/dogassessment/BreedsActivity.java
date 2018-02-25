package nyc.c4q.dogassessment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.dogassessment.model.RandoPuppy;
import nyc.c4q.dogassessment.service.PuppyService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BreedsActivity extends AppCompatActivity {
    private static final String SHARED_PREFS_KEY = "loginSP";

    private TextView welcome;

    private SharedPreferences pref;
    private Intent intent;
    private String username;
    private String password;

    private CardView terrierCard;
    private CardView spanielCard;
    private CardView retrieverCard;
    private CardView poodleCard;

    private ImageView terrierImage;
    private ImageView spanielImage;
    private ImageView retrieverImage;
    private ImageView poodleImage;

    private String terrierUrl;
    private String spanielUrl;
    private String retrieverUrl;
    private String poodleUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);

        intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");

        welcome = findViewById(R.id.welcome_text);
        welcome.setText("What kind of dog would you like to see, " + username + "?");

        pref = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);

        terrierCard = findViewById(R.id.terrier_card);
        spanielCard = findViewById(R.id.spaniel_card);
        retrieverCard = findViewById(R.id.retriever_card);
        poodleCard = findViewById(R.id.poodle_card);

        terrierImage = findViewById(R.id.terrier_cardview_image);
        spanielImage = findViewById(R.id.spaniel_cardview_image);
        retrieverImage = findViewById(R.id.retriever_cardview_image);
        poodleImage = findViewById(R.id.poodle_cardview_image);

        network();
        onClick();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                logOut();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void logOut() {
        pref.edit().clear().apply();

        Log.d("logMe","logOut");
        Intent intent = new Intent(BreedsActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void onClick() {
        terrierCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BreedsActivity.this, DogsActivity.class);
                intent.putExtra("dog","Terrier");
                startActivity(intent);
            }
        });

        spanielCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BreedsActivity.this, DogsActivity.class);
                intent.putExtra("dog","Spaniel");
                startActivity(intent);
            }
        });

        retrieverCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BreedsActivity.this, DogsActivity.class);
                intent.putExtra("dog","Retriever");
                startActivity(intent);
            }
        });

        poodleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BreedsActivity.this, DogsActivity.class);
                intent.putExtra("dog","Poodle");
                startActivity(intent);
            }
        });

    }

    private void network() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PuppyService puppyService = retrofit.create(PuppyService.class);
        Call<RandoPuppy> puppy = puppyService.getTerrier();
        puppy.enqueue(new Callback<RandoPuppy>() {
            @Override
            public void onResponse(Call<RandoPuppy> call, Response<RandoPuppy> response) {
                terrierUrl = response.body().getMessage();
                Picasso.with(getApplicationContext())
                        .load(response.body().getMessage())
                        .into(terrierImage);

            }

            @Override
            public void onFailure(Call<RandoPuppy> call, Throwable t) {

            }
        });
        Call<RandoPuppy> puppy2 = puppyService.getRetriever();
        puppy2.enqueue(new Callback<RandoPuppy>() {
            @Override
            public void onResponse(Call<RandoPuppy> call, Response<RandoPuppy> response) {
                retrieverUrl = response.body().getMessage();
                Picasso.with(getApplicationContext())
                        .load(response.body().getMessage())
                        .into(retrieverImage);

            }

            @Override
            public void onFailure(Call<RandoPuppy> call, Throwable t) {

            }
        });
        Call<RandoPuppy> puppy3 = puppyService.getSpaniel();
        puppy3.enqueue(new Callback<RandoPuppy>() {
            @Override
            public void onResponse(Call<RandoPuppy> call, Response<RandoPuppy> response) {
                spanielUrl = response.body().getMessage();
                Picasso.with(getApplicationContext())
                        .load(response.body().getMessage())
                        .into(spanielImage);

            }

            @Override
            public void onFailure(Call<RandoPuppy> call, Throwable t) {

            }
        });
        Call<RandoPuppy> puppy4 = puppyService.getPoodle();
        puppy4.enqueue(new Callback<RandoPuppy>() {
            @Override
            public void onResponse(Call<RandoPuppy> call, Response<RandoPuppy> response) {
                poodleUrl = response.body().getMessage();
                Picasso.with(getApplicationContext())
                        .load(response.body().getMessage())
                        .into(poodleImage);

            }

            @Override
            public void onFailure(Call<RandoPuppy> call, Throwable t) {

            }
        });

    }
}










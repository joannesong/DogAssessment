package nyc.c4q.dogassessment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import nyc.c4q.dogassessment.network.DogAPIClient;

public class DogsActivity extends AppCompatActivity {
    private static final String SHARED_PREFS_KEY = "loginSP";

    private SharedPreferences pref;
    private TextView breed;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);

        Intent intent = getIntent();
        String dogName = intent.getStringExtra("dog");
        Log.d("test", dogName);

        pref = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);

        breed = findViewById(R.id.dog_name);
        breed.setText(dogName);

        recyclerView = findViewById(R.id.recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        final DogAPIClient client = new DogAPIClient(recyclerView);


        switch(dogName){
            case "Terrier":
                client.start("terriers");
                break;
            case "Spaniel":
                client.start("spaniel");
                break;
            case "Poodle":
                client.start("poodle");
                break;
            case "Retriever":
                client.start("retriever");
                break;
            default:
                break;

        }

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

        Log.d("logMe2","logOut");
        Intent intent = new Intent(DogsActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}

package nyc.c4q.dogassessment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {
    private static final String SHARED_PREFS_KEY = "loginSP";

    private SharedPreferences pref;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        pref = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);

        image = findViewById(R.id.dog_picture);

        Picasso.with(getApplicationContext())
                .load(url)
                .into(image);
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
        Intent intent = new Intent(PhotoActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}

package nyc.c4q.dogassessment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_KEY = "loginSP";
    private TextInputLayout til;
    private TextInputLayout til2;

    private SharedPreferences login;

    private EditText username;
    private EditText password;

    private String mUsername;
    private String mPassword;

    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login = getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        username = findViewById(R.id.login_edit_text_1);
        password = findViewById(R.id.login_edit_text_2);


        String checkUsername = login.getString("username", null);
        String checkPassword = login.getString("password", null);

        Log.d("login", checkPassword +" " + checkUsername);
        if (checkUsername != null && checkPassword != null) {
            Log.d("repeat", "repeat");
            Intent intent = new Intent(LoginActivity.this, BreedsActivity.class);
            startActivity(intent);
        }
        til = (TextInputLayout) findViewById(R.id.text_input_layout_username);
        til2 = (TextInputLayout) findViewById(R.id.text_input_layout_password);

        Button button = findViewById(R.id.login_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUsername = username.getText().toString();
                mPassword = password.getText().toString();

                editor = login.edit();

                if(mUsername.matches("")){
                    til.setError("please enter a username");

                }
                if(mPassword.matches("")){
                    til2.setError("please enter a password");
                }
                else if(mPassword.contains(mUsername)){
                    til2.setError("password cannot contain username");
                }

                else{
                    editor.putString("username", mUsername);
                    editor.putString("password", mPassword);
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, BreedsActivity.class);
                    intent.putExtra("username", mUsername);
                    intent.putExtra("password",mPassword);
                    startActivity(intent);
                }

            }
        });
    }
}

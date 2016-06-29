package co.gymglishproject.mobile.gymglishproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();


        final EditText emailEditText = (EditText) findViewById(R.id.email);
        final EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        final Button loginButton = (Button) findViewById(R.id.btn_login);


        assert loginButton != null;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(emailEditText.getText().toString().trim().length() > 0 &&
                        passwordEditText.getText().toString().trim().length() > 0) {

                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                            R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("login in...");
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressDialog.dismiss();


                                    if (task.isSuccessful()) {

                                        Intent intent = new Intent(LoginActivity.this, WebViewActivity.class);
                                        startActivity(intent);


                                    }


                                    if (!task.isSuccessful()) {


                                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();


                                        //// login failed

                                    }


                                }
                            });

                }else {

                    Toast.makeText(getApplicationContext(), "Please fill all the lines", Toast.LENGTH_LONG).show();


                }
            }
        });


    }
}

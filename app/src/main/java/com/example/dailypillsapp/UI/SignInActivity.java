package com.example.dailypillsapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailypillsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    EditText userEmail, userPassword;
    Button signInButton;
    ProgressBar progressCircle;

    TextView textSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        signInButton = findViewById(R.id.btnSignIN);
        progressCircle = findViewById(R.id.progressCircle);

        textSignUp = findViewById(R.id.textSignUp);
        //Action
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressCircle.setVisibility(View.VISIBLE);

                String UserEmail = userEmail.getText().toString().trim();
                String UserPassword = userPassword.getText().toString().trim();

                if (UserEmail.isEmpty() || UserPassword.isEmpty()) {
                    progressCircle.setVisibility(View.INVISIBLE);
                    Toast.makeText(SignInActivity.this, "من فضلك تأكد من البيانات", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(UserEmail).matches()) {
                    progressCircle.setVisibility(View.INVISIBLE);
                    Toast.makeText(SignInActivity.this, "الأيميل غير صحيح", Toast.LENGTH_SHORT).show();
                } else {

                    firebaseAuth.signInWithEmailAndPassword(UserEmail, UserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignInActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignInActivity.this, "خطاء في تسجيل الدخول", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
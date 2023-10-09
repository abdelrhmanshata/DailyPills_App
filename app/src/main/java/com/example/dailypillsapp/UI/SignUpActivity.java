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

import com.example.dailypillsapp.Model.User;
import com.example.dailypillsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Users = database.getReference("Users");

    EditText userName, userEmail, userPassword, userRePassword;
    Button signUpButton;
    ProgressBar progressCircle;

    TextView textSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        userRePassword = findViewById(R.id.userRePassword);
        progressCircle = findViewById(R.id.progressCircle);
        signUpButton = findViewById(R.id.btnSignUp);

        textSignIn = findViewById(R.id.textSignIn);

        //Action
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressCircle.setVisibility(View.VISIBLE);

                String UserName = userName.getText().toString().trim();
                String UserEmail = userEmail.getText().toString().trim();
                String UserPassword = userPassword.getText().toString().trim();
                String UserRePassword = userRePassword.getText().toString().trim();


                if (UserName.isEmpty() || UserEmail.isEmpty() || UserPassword.isEmpty() || UserRePassword.isEmpty()) {
                    progressCircle.setVisibility(View.INVISIBLE);
                    Toast.makeText(SignUpActivity.this, "من فضلك تأكد من البيانات", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(UserEmail).matches()) {
                    progressCircle.setVisibility(View.INVISIBLE);
                    Toast.makeText(SignUpActivity.this, "الأيميل غير صحيح", Toast.LENGTH_SHORT).show();
                } else if (!UserPassword.equals(UserRePassword)) {
                    progressCircle.setVisibility(View.INVISIBLE);
                    Toast.makeText(SignUpActivity.this, "من فضلك تأكد من كلمة المرور", Toast.LENGTH_SHORT).show();
                } else {

                    firebaseAuth
                            .createUserWithEmailAndPassword(UserEmail, UserPassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                                String UID = currentUser.getUid();

                                User user = new User();
                                user.setUID(UID);
                                user.setName(UserName);
                                user.setEmail(UserEmail);
                                user.setPassword(UserPassword);

                                // Save Data into realTime Database
                                Users.child(UID).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        progressCircle.setVisibility(View.INVISIBLE);
                                        Toast.makeText(SignUpActivity.this, "OK", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
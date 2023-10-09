package com.example.dailypillsapp.UI;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dailypillsapp.Model.Medicine;
import com.example.dailypillsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser CurrentUser = auth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Medicines = database.getReference("Medicines");
    DatabaseReference Reset = database.getReference("Reset");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        boolean connection = isNetworkAvailable(this);

        if (connection) {
            Format format = new SimpleDateFormat("EEEE", Locale.ENGLISH);
            String dayName = format.format(new Date());

            if (CurrentUser != null) {
                // Reset Daily
                resetDaily(dayName);

                // Reset Weekly
                if (dayName.equals("Saturday")) {
                    resetWeekly();
                } else {
                    Reset.child("Weekly").setValue(true);
                }
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent;
                    if (CurrentUser != null) {
                        intent = new Intent(SplashActivity.this, MainActivity.class);
                    } else {
                        intent = new Intent(SplashActivity.this, SignInActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        } else {
            Toast.makeText(this, "من فضلك تأكد من أتصالك بالأنترنت أولا و أعادة تشغيل البرنامج", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    void resetDaily(String DayName) {
        Reset.child("Daily")
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String day = snapshot.getValue(String.class);
                if (!day.equals(DayName)) {
                    Medicines.child(CurrentUser.getUid())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Medicine medicine = snapshot.getValue(Medicine.class);
                                if (medicine != null) {
                                    for (int i = 1; i < 25; i++) {
                                        Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("stateDose" + i).setValue("");
                                    }
                                    Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("dosesNumberTaken").setValue(0);
                                }
                            }
                            Reset.child("Daily").setValue(DayName);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("Error", error.getMessage());
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void resetWeekly() {
        Reset.child("Weekly")
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean Weekly = snapshot.getValue(Boolean.class);
                if (Weekly) {
                    Medicines.child(CurrentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Medicine medicine = snapshot.getValue(Medicine.class);
                                if (medicine != null) {
                                    Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("stateSaturday").setValue("");
                                    Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("stateSunday").setValue("");
                                    Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("stateMonday").setValue("");
                                    Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("stateTuesday").setValue("");
                                    Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("stateWednesday").setValue("");
                                    Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("stateThursday").setValue("");
                                    Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("stateFriday").setValue("");
                                }
                            }
                            Reset.child("Weekly").setValue(false);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("Error", error.getMessage());
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
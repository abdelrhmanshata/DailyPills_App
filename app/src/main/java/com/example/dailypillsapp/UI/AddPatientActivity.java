package com.example.dailypillsapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dailypillsapp.Model.Patient;
import com.example.dailypillsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPatientActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser CurrentUser = auth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Patients = database.getReference("Patients");

    ImageView backIcon, male, female;
    EditText patientName;
    DatePicker datePicker;
    Button confirmButton;

    String gender = "Male";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        backIcon = findViewById(R.id.backIcon);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        patientName = findViewById(R.id.patientName);
        datePicker = findViewById(R.id.datePicker);
        confirmButton = findViewById(R.id.confirmButton);


        //Action

        male.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                male.setBackgroundColor(getColor(R.color.lavender));
                female.setBackgroundColor(getColor(R.color.white));
                gender = "Male";
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                female.setBackgroundColor(getColor(R.color.lavender));
                male.setBackgroundColor(getColor(R.color.white));
                gender = "Female";
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (patientName.getText().toString().isEmpty()) {
                    Toast.makeText(AddPatientActivity.this, "Please Enter Patient Name", Toast.LENGTH_SHORT).show();
                } else {
                    int day = datePicker.getDayOfMonth();
                    int month = datePicker.getMonth() + 1;
                    int year = datePicker.getYear();

                    String ID = Patients.push().getKey();

                    Patient patient = new Patient();
                    patient.setId(ID);
                    patient.setUserId(CurrentUser.getUid());
                    patient.setPatientName(patientName.getText().toString().trim());
                    patient.setPatientGender(gender);
                    patient.setPatientBirthDay(day + " / " + month + " / " + year);

                    Patients.child(patient.getUserId()).child(patient.getId()).setValue(patient);
                    Toast.makeText(AddPatientActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
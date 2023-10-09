package com.example.dailypillsapp.UI;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dailypillsapp.Broadcast_Receiver.Broadcast_Receiver;
import com.example.dailypillsapp.Model.Medicine;
import com.example.dailypillsapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateMedicineActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser CurrentUser = auth.getCurrentUser();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Medicines = database.getReference("Medicines");
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference("Medicines");

    EditText medicineName, medicineAmount;
    ImageView backIcon, medicineImage;
    TextView repetitionText, amountText, repetitionHours, repetitionDaily;
    Spinner repetitionDailySpinner, repetitionHoursSpinner, amountSpinner;
    TimePicker timePicker;
    Button save;

    String[] repetition_Daily = {"مرة واحدة", "كل يوم", "كل يومين", "كل ثلاث أيام"};
    String[] repetition_Hours = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    String[] types = {"حبة", "ملل", "مج", "مل - مج"};

    int SELECT_PICTURE = 200;
    String RepetitionType = "Daily", Repetition = "كل يوم", Types = "حبة";
    Uri selectedImageUri = null;
    long TimeAtMillis;

    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    Medicine medicine = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medicine);

        medicineName = findViewById(R.id.medicineName);
        medicineImage = findViewById(R.id.medicineImage);
        backIcon = findViewById(R.id.backIcon);
        medicineAmount = findViewById(R.id.medicineAmount);
        repetitionHours = findViewById(R.id.repetitionHours);
        repetitionDaily = findViewById(R.id.repetitionDaily);
        repetitionText = findViewById(R.id.repetitionText);
        repetitionDailySpinner = findViewById(R.id.repetitionDailySpinner);
        repetitionHoursSpinner = findViewById(R.id.repetitionHoursSpinner);
        amountText = findViewById(R.id.amountText);
        amountSpinner = findViewById(R.id.amountSpinner);
        timePicker = findViewById(R.id.timePicker);
        save = findViewById(R.id.save);

        //
        ArrayAdapter repetitionDailyAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, repetition_Daily);
        repetitionDailyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repetitionDailySpinner.setAdapter(repetitionDailyAdapter);
        repetitionDailySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                repetitionText.setText(repetition_Daily[position]);
                Repetition = repetition_Daily[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        ArrayAdapter repetitionHoursAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, repetition_Hours);
        repetitionHoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repetitionHoursSpinner.setAdapter(repetitionHoursAdapter);
        repetitionHoursSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                repetitionText.setText(repetition_Hours[position]);
                Repetition = repetition_Hours[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter amountAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, types);
        amountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amountSpinner.setAdapter(amountAdapter);
        amountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Types = types[position];
                amountText.setText(Types);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Data Of Object Medicine
        medicine = (Medicine) getIntent().getSerializableExtra("KeyMedicine");

        if (medicine != null) {
            Picasso.get().load(medicine.getImageUri()).into(medicineImage);

            medicineName.setText(medicine.getMedicationName());

            Repetition = medicine.getMedicationRepetition();
            if (medicine.getMedicationRepetitionType().equals("Daily")) {
                repetitionDaily.setBackgroundColor(getColor(R.color.lavender));
                repetitionHours.setBackgroundColor(getColor(R.color.white));
                RepetitionType = "Daily";
                repetitionDailySpinner.setSelection(repetitionDailyAdapter.getPosition(Repetition));
                repetitionHoursSpinner.setVisibility(View.GONE);
                repetitionDailySpinner.setVisibility(View.VISIBLE);
            } else {
                repetitionHours.setBackgroundColor(getColor(R.color.lavender));
                repetitionDaily.setBackgroundColor(getColor(R.color.white));
                RepetitionType = "Hours";
                repetitionHoursSpinner.setSelection(repetitionHoursAdapter.getPosition(Repetition));
                repetitionHoursSpinner.setVisibility(View.VISIBLE);
                repetitionDailySpinner.setVisibility(View.GONE);
            }

            Types = medicine.getMedicationType();
            amountSpinner.setSelection(amountAdapter.getPosition(Types));

            medicineAmount.setText(medicine.getMedicationAmount());

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(medicine.getTimeAtMillis()));

            timePicker.setHour(calendar.get(Calendar.HOUR));
            timePicker.setMinute(calendar.get(Calendar.MINUTE));

        }

        // Action
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        medicineImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        repetitionHours.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                repetitionHours.setBackgroundColor(getColor(R.color.lavender));
                repetitionDaily.setBackgroundColor(getColor(R.color.white));
                RepetitionType = "Hours";

                repetitionHoursSpinner.setVisibility(View.VISIBLE);
                repetitionDailySpinner.setVisibility(View.GONE);

            }
        });
        repetitionDaily.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                repetitionDaily.setBackgroundColor(getColor(R.color.lavender));
                repetitionHours.setBackgroundColor(getColor(R.color.white));
                RepetitionType = "Daily";

                repetitionHoursSpinner.setVisibility(View.GONE);
                repetitionDailySpinner.setVisibility(View.VISIBLE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (medicineName.getText().toString().isEmpty() || medicineAmount.getText().toString().isEmpty()) {
                    Toast.makeText(UpdateMedicineActivity.this, "من فضلك تأكد من الصورة و الأسم و الكمية ", Toast.LENGTH_SHORT).show();
                } else {

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                    calendar.set(Calendar.MINUTE, timePicker.getMinute());
                    calendar.set(Calendar.SECOND, 00);

                    TimeAtMillis = calendar.getTimeInMillis();

                    medicine.setMedicationName(medicineName.getText().toString().trim());
                    medicine.setMedicationRepetitionType(RepetitionType);
                    medicine.setMedicationRepetition(Repetition);
                    medicine.setMedicationAmount(medicineAmount.getText().toString().trim());
                    medicine.setMedicationType(Types);
                    medicine.setTimeAtMillis(String.valueOf(TimeAtMillis));
                    medicine.setTime(getTime(timePicker.getHour(), timePicker.getMinute()));
                    if (RepetitionType.equals("Hours")) {
                        int hour = Integer.parseInt(Repetition);
                        int dosesNumber = 24 / hour;
                        medicine.setDosesNumber(dosesNumber);
                    } else {
                        medicine.setDosesNumber(0);
                    }

                    //
                    if (selectedImageUri == null) {
                        Medicines.child(CurrentUser.getUid()).child(medicine.getId()).setValue(medicine);
                        finish();
                    } else {
                        uploadImage(selectedImageUri, medicine);
                    }

                    //
                    Intent intent = new Intent(UpdateMedicineActivity.this, Broadcast_Receiver.class);
                    intent.putExtra("ID", Integer.parseInt(medicine.getId()));
                    pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Integer.parseInt(medicine.getId()), intent, PendingIntent.FLAG_IMMUTABLE);
                    setAlarmNotification(TimeAtMillis, RepetitionType, Repetition);


                }
            }
        });
    }

    void SelectImage() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    medicineImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

    // UploadImage method
    private void uploadImage(Uri ImageUri, Medicine medicine) {
        if (ImageUri != null) {

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child(CurrentUser.getUid()).child(medicine.getId() + ".jpg");
            ref.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            medicine.setImageUri(uri.toString());
                            // App Medicine Data in realTime
                            Medicines.child(CurrentUser.getUid()).child(medicine.getId()).setValue(medicine);
                            progressDialog.dismiss();
                            finish();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Error, Image not uploaded
                    progressDialog.dismiss();
                    Toast.makeText(UpdateMedicineActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private String getTime(int hr, int min) {
        Time tme = new Time(hr, min, 0);//seconds by default set to zero
        Format formatter;
        formatter = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
        return formatter.format(tme);
    }

    public void setAlarmNotification(long TimeAtMillis, String repetitionType, String repetition) {

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (repetitionType.equals("Daily")) {
            if (repetition.equals("كل يوم")) {
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, TimeAtMillis, 1 * 24 * 60 * 60 * 1000, pendingIntent);
            } else if (repetition.equals("كل يومين")) {
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, TimeAtMillis, 2 * 24 * 60 * 60 * 1000, pendingIntent);
            } else if (repetition.equals("كل ثلاث أيام")) {
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, TimeAtMillis, 3 * 24 * 60 * 60 * 1000, pendingIntent);
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, TimeAtMillis, pendingIntent);
            }
        } else {
            int hour = Integer.parseInt(repetition);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, TimeAtMillis, hour * 60 * 60 * 1000, pendingIntent);
        }
    }
}









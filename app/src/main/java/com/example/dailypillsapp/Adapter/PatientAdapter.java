package com.example.dailypillsapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailypillsapp.Model.Medicine;
import com.example.dailypillsapp.Model.Patient;
import com.example.dailypillsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PatientAdapter extends BaseAdapter {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Patients = database.getReference("Patients");

    private Context context;
    private List<Patient> allPatients;

    public PatientAdapter(Context context, List<Patient> allPatients) {
        this.context = context;
        this.allPatients = allPatients;
    }

    private static class ViewHolder {
        TextView patientName;
        ImageView deletePatient;
    }

    @Override
    public int getCount() {
        return allPatients.size();
    }

    @Override
    public Patient getItem(int i) {
        return allPatients.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Patient patient = getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_patient_layout, parent, false);

        viewHolder.patientName = convertView.findViewById(R.id.patientName);
        viewHolder.deletePatient = convertView.findViewById(R.id.deletePatient);

        viewHolder.patientName.setText(patient.getPatientName());
        viewHolder.deletePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Patients.child(patient.getUserId()).child(patient.getId()).removeValue();
                Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}

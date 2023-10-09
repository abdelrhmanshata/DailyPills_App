package com.example.dailypillsapp.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dailypillsapp.Adapter.MedicineAdapter;
import com.example.dailypillsapp.Model.Medicine;
import com.example.dailypillsapp.R;
import com.example.dailypillsapp.UI.AddMedicineActivity;
import com.example.dailypillsapp.UI.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser CurrentUser = auth.getCurrentUser();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Medicines = database.getReference("Medicines");

    ImageView addMedicine, logout;
    ListView listMedicine;
    List<Medicine> allMedicines;
    MedicineAdapter medicineAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        addMedicine = root.findViewById(R.id.addMedicine);
        logout = root.findViewById(R.id.logout);
        listMedicine = root.findViewById(R.id.listMedicine);
        allMedicines = new ArrayList<>();
        medicineAdapter = new MedicineAdapter(getContext(), allMedicines);
        listMedicine.setAdapter(medicineAdapter);

        // Action
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                getActivity().finish();
                Intent intent = new Intent(getContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
        addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddMedicineActivity.class);
                getContext().startActivity(intent);
            }
        });

        // Read Data From Database
        Medicines.child(CurrentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allMedicines.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Medicine medicine = snapshot.getValue(Medicine.class);
                    if (medicine != null) allMedicines.add(medicine);
                }
                medicineAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", error.getMessage());
            }
        });

        return root;
    }
}
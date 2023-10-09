package com.example.dailypillsapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailypillsapp.Model.Medicine;
import com.example.dailypillsapp.R;
import com.example.dailypillsapp.UI.UpdateMedicineActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MedicineAdapter extends BaseAdapter {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser CurrentUser = auth.getCurrentUser();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Medicines = database.getReference("Medicines");

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference("Medicines");

    private Context context;
    private List<Medicine> allMedicines;

    public MedicineAdapter(Context context, List<Medicine> allMedicines) {
        this.context = context;
        this.allMedicines = allMedicines;
    }

    private static class ViewHolder {
        TextView nameMedicine, amountMedicine, timeMedicine;
        ImageView editMedicine, deleteMedicine, imageMedicine;
    }

    @Override
    public int getCount() {
        return allMedicines.size();
    }

    @Override
    public Medicine getItem(int i) {
        return allMedicines.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Medicine medicine = getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_medicine_layout, parent, false);

        viewHolder.nameMedicine = convertView.findViewById(R.id.nameMedicine);
        viewHolder.amountMedicine = convertView.findViewById(R.id.amountMedicine);
        viewHolder.timeMedicine = convertView.findViewById(R.id.timeMedicine);

        viewHolder.editMedicine = convertView.findViewById(R.id.editMedicine);
        viewHolder.deleteMedicine = convertView.findViewById(R.id.deleteMedicine);
        viewHolder.imageMedicine = convertView.findViewById(R.id.imageMedicine);

        viewHolder.nameMedicine.setText(medicine.getMedicationName());
        viewHolder.amountMedicine.setText(medicine.getMedicationAmount() + " " + medicine.getMedicationType());
        viewHolder.timeMedicine.setText(medicine.getTime());

        Picasso.get().load(medicine.getImageUri()).into(viewHolder.imageMedicine);

        viewHolder.editMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateMedicineActivity.class);
                intent.putExtra("KeyMedicine", medicine);
                context.startActivity(intent);
            }
        });
        viewHolder.deleteMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.delete_layout);

                TextView yes = dialog.findViewById(R.id.deleteYes);
                TextView no = dialog.findViewById(R.id.deleteNo);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        storageReference.child(CurrentUser.getUid()).child(medicine.getId() + ".jpg").delete();
                        Medicines.child(CurrentUser.getUid()).child(medicine.getId()).removeValue();
                        Toast.makeText(context, "تم حذف الدواء بنجاح", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(context, "لم يتم حذف الدواء", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
            }
        });

        return convertView;
    }
}

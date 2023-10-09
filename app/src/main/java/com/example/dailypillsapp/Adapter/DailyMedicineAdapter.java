package com.example.dailypillsapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailypillsapp.Model.Medicine;
import com.example.dailypillsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DailyMedicineAdapter extends BaseAdapter {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser CurrentUser = auth.getCurrentUser();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Medicines = database.getReference("Medicines");
    private Context context;
    private List<Medicine> allMedicines;

    boolean stateLayoutVisibility = true;

    public DailyMedicineAdapter(Context context, List<Medicine> allMedicines) {
        this.context = context;
        this.allMedicines = allMedicines;
    }

    private static class ViewHolder {
        LinearLayout layout12Doses, layout24Doses;
        View line12Doses, line24Doses;

        ImageView arrowImage;

        TextView nameMedicine, amountMedicine, timeMedicine, repetitionMedicine;
        ImageView stateMedicine, imageMedicine;
        ImageView doneNotification, cancelNotification;

        LinearLayout layoutDose1, layoutDose2, layoutDose3, layoutDose4, layoutDose5, layoutDose6, layoutDose7, layoutDose8, layoutDose9, layoutDose10, layoutDose11, layoutDose12;
        LinearLayout layoutDose13, layoutDose14, layoutDose15, layoutDose16, layoutDose17, layoutDose18, layoutDose19, layoutDose20, layoutDose21, layoutDose22, layoutDose23, layoutDose24;

        ImageView stateDose1, stateDose2, stateDose3, stateDose4, stateDose5, stateDose6, stateDose7, stateDose8, stateDose9, stateDose10, stateDose11, stateDose12;
        ImageView stateDose13, stateDose14, stateDose15, stateDose16, stateDose17, stateDose18, stateDose19, stateDose20, stateDose21, stateDose22, stateDose23, stateDose24;

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

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Medicine medicine = getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_daily_medicine_layout, parent, false);

        {
            viewHolder.nameMedicine = convertView.findViewById(R.id.nameMedicine);
            viewHolder.amountMedicine = convertView.findViewById(R.id.amountMedicine);
            viewHolder.timeMedicine = convertView.findViewById(R.id.timeMedicine);
            viewHolder.repetitionMedicine = convertView.findViewById(R.id.repetitionMedicine);
            viewHolder.stateMedicine = convertView.findViewById(R.id.stateMedicine);
            viewHolder.imageMedicine = convertView.findViewById(R.id.imageMedicine);
            viewHolder.doneNotification = convertView.findViewById(R.id.doneNotification);
            viewHolder.cancelNotification = convertView.findViewById(R.id.cancelNotification);

            viewHolder.layout12Doses = convertView.findViewById(R.id.layout12Doses);
            viewHolder.layout24Doses = convertView.findViewById(R.id.layout24Doses);

            viewHolder.line12Doses = convertView.findViewById(R.id.line12Doses);
            viewHolder.line24Doses = convertView.findViewById(R.id.line24Doses);

            viewHolder.arrowImage = convertView.findViewById(R.id.arrow);

            viewHolder.layoutDose1 = convertView.findViewById(R.id.layoutDose1);
            viewHolder.layoutDose2 = convertView.findViewById(R.id.layoutDose2);
            viewHolder.layoutDose3 = convertView.findViewById(R.id.layoutDose3);
            viewHolder.layoutDose4 = convertView.findViewById(R.id.layoutDose4);
            viewHolder.layoutDose5 = convertView.findViewById(R.id.layoutDose5);
            viewHolder.layoutDose6 = convertView.findViewById(R.id.layoutDose6);
            viewHolder.layoutDose7 = convertView.findViewById(R.id.layoutDose7);
            viewHolder.layoutDose8 = convertView.findViewById(R.id.layoutDose8);
            viewHolder.layoutDose9 = convertView.findViewById(R.id.layoutDose9);
            viewHolder.layoutDose10 = convertView.findViewById(R.id.layoutDose10);
            viewHolder.layoutDose11 = convertView.findViewById(R.id.layoutDose11);
            viewHolder.layoutDose12 = convertView.findViewById(R.id.layoutDose12);
            viewHolder.layoutDose13 = convertView.findViewById(R.id.layoutDose13);
            viewHolder.layoutDose14 = convertView.findViewById(R.id.layoutDose14);
            viewHolder.layoutDose15 = convertView.findViewById(R.id.layoutDose15);
            viewHolder.layoutDose16 = convertView.findViewById(R.id.layoutDose16);
            viewHolder.layoutDose17 = convertView.findViewById(R.id.layoutDose17);
            viewHolder.layoutDose18 = convertView.findViewById(R.id.layoutDose18);
            viewHolder.layoutDose19 = convertView.findViewById(R.id.layoutDose19);
            viewHolder.layoutDose20 = convertView.findViewById(R.id.layoutDose20);
            viewHolder.layoutDose21 = convertView.findViewById(R.id.layoutDose21);
            viewHolder.layoutDose22 = convertView.findViewById(R.id.layoutDose22);
            viewHolder.layoutDose23 = convertView.findViewById(R.id.layoutDose23);
            viewHolder.layoutDose24 = convertView.findViewById(R.id.layoutDose24);

            viewHolder.stateDose1 = convertView.findViewById(R.id.stateDose1);
            viewHolder.stateDose2 = convertView.findViewById(R.id.stateDose2);
            viewHolder.stateDose3 = convertView.findViewById(R.id.stateDose3);
            viewHolder.stateDose4 = convertView.findViewById(R.id.stateDose4);
            viewHolder.stateDose5 = convertView.findViewById(R.id.stateDose5);
            viewHolder.stateDose6 = convertView.findViewById(R.id.stateDose6);
            viewHolder.stateDose7 = convertView.findViewById(R.id.stateDose7);
            viewHolder.stateDose8 = convertView.findViewById(R.id.stateDose8);
            viewHolder.stateDose9 = convertView.findViewById(R.id.stateDose9);
            viewHolder.stateDose10 = convertView.findViewById(R.id.stateDose10);
            viewHolder.stateDose11 = convertView.findViewById(R.id.stateDose11);
            viewHolder.stateDose12 = convertView.findViewById(R.id.stateDose12);
            viewHolder.stateDose13 = convertView.findViewById(R.id.stateDose13);
            viewHolder.stateDose14 = convertView.findViewById(R.id.stateDose14);
            viewHolder.stateDose15 = convertView.findViewById(R.id.stateDose15);
            viewHolder.stateDose16 = convertView.findViewById(R.id.stateDose16);
            viewHolder.stateDose17 = convertView.findViewById(R.id.stateDose17);
            viewHolder.stateDose18 = convertView.findViewById(R.id.stateDose18);
            viewHolder.stateDose19 = convertView.findViewById(R.id.stateDose19);
            viewHolder.stateDose20 = convertView.findViewById(R.id.stateDose20);
            viewHolder.stateDose21 = convertView.findViewById(R.id.stateDose21);
            viewHolder.stateDose22 = convertView.findViewById(R.id.stateDose22);
            viewHolder.stateDose23 = convertView.findViewById(R.id.stateDose23);
            viewHolder.stateDose24 = convertView.findViewById(R.id.stateDose24);
        }

        viewHolder.nameMedicine.setText(medicine.getMedicationName());
        viewHolder.amountMedicine.setText(medicine.getMedicationAmount() + " " + medicine.getMedicationType());
        viewHolder.timeMedicine.setText(medicine.getTime());

        if (medicine.getMedicationRepetitionType().equals("Daily")) {
            viewHolder.repetitionMedicine.setText(medicine.getMedicationRepetition());
        } else {
            viewHolder.repetitionMedicine.setText(" كل " + medicine.getMedicationRepetition() + " ساعة ");
        }

        Picasso.get().load(medicine.getImageUri()).into(viewHolder.imageMedicine);

        Format format = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        String dayName = format.format(new Date());

        if (medicine.getMedicationRepetitionType().equals("Daily")) {

            String medicationState = "";

            switch (dayName) {
                case "Saturday":
                    medicationState = medicine.getStateSaturday();
                    break;
                case "Sunday":
                    medicationState = medicine.getStateSunday();
                    break;
                case "Monday":
                    medicationState = medicine.getStateMonday();
                    break;
                case "Tuesday":
                    medicationState = medicine.getStateTuesday();
                    break;
                case "Wednesday":
                    medicationState = medicine.getStateWednesday();
                    break;
                case "Thursday":
                    medicationState = medicine.getStateThursday();
                    break;
                case "Friday":
                    medicationState = medicine.getStateFriday();
                    break;
            }
            if (medicationState.equals("Done")) {
                viewHolder.stateMedicine.setImageResource(R.drawable.done);
                viewHolder.nameMedicine.setTextColor(context.getResources().getColor(R.color.green));
                viewHolder.amountMedicine.setTextColor(context.getResources().getColor(R.color.green));
                viewHolder.timeMedicine.setTextColor(context.getResources().getColor(R.color.green));
                viewHolder.repetitionMedicine.setTextColor(context.getResources().getColor(R.color.green));
            } else if (medicationState.equals("Cancel")) {
                viewHolder.stateMedicine.setImageResource(R.drawable.cancel);
                viewHolder.nameMedicine.setTextColor(context.getResources().getColor(R.color.red));
                viewHolder.amountMedicine.setTextColor(context.getResources().getColor(R.color.red));
                viewHolder.timeMedicine.setTextColor(context.getResources().getColor(R.color.red));
                viewHolder.repetitionMedicine.setTextColor(context.getResources().getColor(R.color.red));
            }

        }
        else {

            if (medicine.getDosesNumberTaken() >= medicine.getDosesNumber()) {
                Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("state" + dayName).setValue("Taken");
                viewHolder.stateMedicine.setImageResource(R.drawable.donetaken);
                viewHolder.nameMedicine.setTextColor(context.getResources().getColor(R.color.lemon));
                viewHolder.amountMedicine.setTextColor(context.getResources().getColor(R.color.lemon));
                viewHolder.timeMedicine.setTextColor(context.getResources().getColor(R.color.lemon));
                viewHolder.repetitionMedicine.setTextColor(context.getResources().getColor(R.color.lemon));
            } else {
                Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("state" + dayName).setValue("Pending");
                viewHolder.stateMedicine.setImageResource(R.drawable.warning);
                viewHolder.nameMedicine.setTextColor(context.getResources().getColor(R.color.orange));
                viewHolder.amountMedicine.setTextColor(context.getResources().getColor(R.color.orange));
                viewHolder.timeMedicine.setTextColor(context.getResources().getColor(R.color.orange));
                viewHolder.repetitionMedicine.setTextColor(context.getResources().getColor(R.color.orange));
            }

            viewHolder.arrowImage.setVisibility(View.VISIBLE);

            if (medicine.getDosesNumber() < 13) {
                viewHolder.line12Doses.setVisibility(View.VISIBLE);
                viewHolder.line24Doses.setVisibility(View.GONE);
            } else {
                viewHolder.line12Doses.setVisibility(View.VISIBLE);
                viewHolder.line24Doses.setVisibility(View.VISIBLE);
            }

            List<LinearLayout> lists = new ArrayList();

            {
                lists.add(viewHolder.layoutDose1);
                lists.add(viewHolder.layoutDose2);
                lists.add(viewHolder.layoutDose3);
                lists.add(viewHolder.layoutDose4);
                lists.add(viewHolder.layoutDose5);
                lists.add(viewHolder.layoutDose6);
                lists.add(viewHolder.layoutDose7);
                lists.add(viewHolder.layoutDose8);
                lists.add(viewHolder.layoutDose9);
                lists.add(viewHolder.layoutDose10);
                lists.add(viewHolder.layoutDose11);
                lists.add(viewHolder.layoutDose12);
                lists.add(viewHolder.layoutDose13);
                lists.add(viewHolder.layoutDose14);
                lists.add(viewHolder.layoutDose15);
                lists.add(viewHolder.layoutDose16);
                lists.add(viewHolder.layoutDose17);
                lists.add(viewHolder.layoutDose18);
                lists.add(viewHolder.layoutDose19);
                lists.add(viewHolder.layoutDose20);
                lists.add(viewHolder.layoutDose21);
                lists.add(viewHolder.layoutDose22);
                lists.add(viewHolder.layoutDose23);
                lists.add(viewHolder.layoutDose24);
            }

            for (int i = 0; i < medicine.getDosesNumber(); i++) {
                lists.get(i).setVisibility(View.VISIBLE);
            }



            {
                List<ImageView> stateDoseImage = new ArrayList();

                {
                    stateDoseImage.add(viewHolder.stateDose1);
                    stateDoseImage.add(viewHolder.stateDose2);
                    stateDoseImage.add(viewHolder.stateDose3);
                    stateDoseImage.add(viewHolder.stateDose4);
                    stateDoseImage.add(viewHolder.stateDose5);
                    stateDoseImage.add(viewHolder.stateDose6);
                    stateDoseImage.add(viewHolder.stateDose7);
                    stateDoseImage.add(viewHolder.stateDose8);
                    stateDoseImage.add(viewHolder.stateDose9);
                    stateDoseImage.add(viewHolder.stateDose10);
                    stateDoseImage.add(viewHolder.stateDose11);
                    stateDoseImage.add(viewHolder.stateDose12);
                    stateDoseImage.add(viewHolder.stateDose13);
                    stateDoseImage.add(viewHolder.stateDose14);
                    stateDoseImage.add(viewHolder.stateDose15);
                    stateDoseImage.add(viewHolder.stateDose16);
                    stateDoseImage.add(viewHolder.stateDose17);
                    stateDoseImage.add(viewHolder.stateDose18);
                    stateDoseImage.add(viewHolder.stateDose19);
                    stateDoseImage.add(viewHolder.stateDose20);
                    stateDoseImage.add(viewHolder.stateDose21);
                    stateDoseImage.add(viewHolder.stateDose22);
                    stateDoseImage.add(viewHolder.stateDose23);
                    stateDoseImage.add(viewHolder.stateDose24);
                }

                List<String> stateDoseValue = new ArrayList();

                {
                    stateDoseValue.add(medicine.getStateDose1());
                    stateDoseValue.add(medicine.getStateDose2());
                    stateDoseValue.add(medicine.getStateDose3());
                    stateDoseValue.add(medicine.getStateDose4());
                    stateDoseValue.add(medicine.getStateDose5());
                    stateDoseValue.add(medicine.getStateDose6());
                    stateDoseValue.add(medicine.getStateDose7());
                    stateDoseValue.add(medicine.getStateDose8());
                    stateDoseValue.add(medicine.getStateDose9());
                    stateDoseValue.add(medicine.getStateDose10());
                    stateDoseValue.add(medicine.getStateDose11());
                    stateDoseValue.add(medicine.getStateDose12());
                    stateDoseValue.add(medicine.getStateDose13());
                    stateDoseValue.add(medicine.getStateDose14());
                    stateDoseValue.add(medicine.getStateDose15());
                    stateDoseValue.add(medicine.getStateDose16());
                    stateDoseValue.add(medicine.getStateDose17());
                    stateDoseValue.add(medicine.getStateDose18());
                    stateDoseValue.add(medicine.getStateDose19());
                    stateDoseValue.add(medicine.getStateDose20());
                    stateDoseValue.add(medicine.getStateDose21());
                    stateDoseValue.add(medicine.getStateDose22());
                    stateDoseValue.add(medicine.getStateDose23());
                    stateDoseValue.add(medicine.getStateDose24());
                }

                for (int i = 0; i < medicine.getDosesNumber(); i++) {
                    // Dose{i}
                    if (stateDoseValue.get(i).equals("Done"))
                        stateDoseImage.get(i).setImageResource(R.drawable.done);
                    else if (stateDoseValue.get(i).equals("Cancel"))
                        stateDoseImage.get(i).setImageResource(R.drawable.cancel);
                }
            }
        }

        viewHolder.doneNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (medicine.getMedicationRepetitionType().equals("Daily")) {
                    Medicines.child(CurrentUser.getUid())
                            .child(medicine.getId())
                            .child("state" + dayName)
                            .setValue("Done");
                } else {
                    if (medicine.getDosesNumberTaken() < medicine.getDosesNumber()) {
                        Medicines.child(CurrentUser.getUid())
                                .child(medicine.getId())
                                .child("stateDose" + (medicine.getDosesNumberTaken() + 1))
                                .setValue("Done");
                        Medicines
                                .child(CurrentUser.getUid())
                                .child(medicine.getId()).child("dosesNumberTaken")
                                .setValue(medicine.getDosesNumberTaken() + 1);
                    } else {
                        Toast.makeText(context, "MAX", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        viewHolder.cancelNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (medicine.getMedicationRepetitionType().equals("Daily")) {
                    Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("state" + dayName).setValue("Cancel");
                } else {
                    if (medicine.getDosesNumberTaken() < medicine.getDosesNumber()) {
                        Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("stateDose" + (medicine.getDosesNumberTaken() + 1)).setValue("Cancel");
                        Medicines.child(CurrentUser.getUid()).child(medicine.getId()).child("dosesNumberTaken").setValue(medicine.getDosesNumberTaken() + 1);
                    } else {
                        Toast.makeText(context, "MAX", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        viewHolder.arrowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (medicine.getMedicationRepetitionType().equals("Hours")) {
                    stateLayoutVisibility = !stateLayoutVisibility;

                    viewHolder.layout12Doses.setVisibility(stateLayoutVisibility ? View.VISIBLE : View.GONE);
                    viewHolder.line12Doses.setVisibility(stateLayoutVisibility ? View.VISIBLE : View.GONE);

                    if (medicine.getDosesNumber() > 12) {
                        viewHolder.layout24Doses.setVisibility(stateLayoutVisibility ? View.VISIBLE : View.GONE);
                        viewHolder.line24Doses.setVisibility(stateLayoutVisibility ? View.VISIBLE : View.GONE);
                    }

                    viewHolder.arrowImage.setImageResource(stateLayoutVisibility ? R.drawable.up_arrow : R.drawable.down_arrow);
                }
            }
        });
        return convertView;
    }
}

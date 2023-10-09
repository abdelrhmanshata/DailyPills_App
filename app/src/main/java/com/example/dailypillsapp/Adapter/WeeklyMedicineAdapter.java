package com.example.dailypillsapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailypillsapp.Model.Medicine;
import com.example.dailypillsapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WeeklyMedicineAdapter extends BaseAdapter {

    private Context context;
    private List<Medicine> allMedicines;

    public WeeklyMedicineAdapter(Context context, List<Medicine> allMedicines) {
        this.context = context;
        this.allMedicines = allMedicines;
    }

    private static class ViewHolder {
        TextView nameMedicine, amountMedicine, timeMedicine, repetitionMedicine;
        ImageView imageMedicine;
        ImageView stateSaturday, stateSunday, stateMonday, stateTuesday, stateWednesday, stateThursday, stateFriday;

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
        convertView = inflater.inflate(R.layout.item_weekly_medicine_layout, parent, false);

        viewHolder.nameMedicine = convertView.findViewById(R.id.nameMedicine);
        viewHolder.amountMedicine = convertView.findViewById(R.id.amountMedicine);
        viewHolder.timeMedicine = convertView.findViewById(R.id.timeMedicine);
        viewHolder.repetitionMedicine = convertView.findViewById(R.id.repetitionMedicine);
        viewHolder.imageMedicine = convertView.findViewById(R.id.imageMedicine);
        viewHolder.stateSaturday = convertView.findViewById(R.id.stateSaturday);
        viewHolder.stateSunday = convertView.findViewById(R.id.stateSunday);
        viewHolder.stateMonday = convertView.findViewById(R.id.stateMonday);
        viewHolder.stateTuesday = convertView.findViewById(R.id.stateTuesday);
        viewHolder.stateWednesday = convertView.findViewById(R.id.stateWednesday);
        viewHolder.stateThursday = convertView.findViewById(R.id.stateThursday);
        viewHolder.stateFriday = convertView.findViewById(R.id.stateFriday);

        viewHolder.nameMedicine.setText(medicine.getMedicationName());
        viewHolder.amountMedicine.setText(medicine.getMedicationAmount() + " " + medicine.getMedicationType());
        viewHolder.timeMedicine.setText(medicine.getTime());

        if(medicine.getMedicationRepetitionType().equals("Daily")){
            viewHolder.repetitionMedicine.setText(medicine.getMedicationRepetition());
        }else{
            viewHolder.repetitionMedicine.setText(" كل "+medicine.getMedicationRepetition()+" ساعة ");
        }

        Picasso.get().load(medicine.getImageUri()).into(viewHolder.imageMedicine);

        // Saturday
        if (medicine.getStateSaturday().equals("Done"))
            viewHolder.stateSaturday.setImageResource(R.drawable.done);
        else if (medicine.getStateSaturday().equals("Cancel"))
            viewHolder.stateSaturday.setImageResource(R.drawable.cancel);
        else if (medicine.getStateSaturday().equals("Taken"))
            viewHolder.stateSaturday.setImageResource(R.drawable.donetaken);
        else if (medicine.getStateSaturday().equals("Pending"))
            viewHolder.stateSaturday.setImageResource(R.drawable.warning);

        // Sunday
        if (medicine.getStateSunday().equals("Done"))
            viewHolder.stateSunday.setImageResource(R.drawable.done);
        else if (medicine.getStateSunday().equals("Cancel"))
            viewHolder.stateSunday.setImageResource(R.drawable.cancel);
        else if (medicine.getStateSunday().equals("Taken"))
            viewHolder.stateSunday.setImageResource(R.drawable.donetaken);
        else if (medicine.getStateSunday().equals("Pending"))
            viewHolder.stateSunday.setImageResource(R.drawable.warning);

        // Monday
        if (medicine.getStateMonday().equals("Done"))
            viewHolder.stateMonday.setImageResource(R.drawable.done);
        else if (medicine.getStateMonday().equals("Cancel"))
            viewHolder.stateMonday.setImageResource(R.drawable.cancel);
        else if (medicine.getStateMonday().equals("Taken"))
            viewHolder.stateMonday.setImageResource(R.drawable.donetaken);
        else if (medicine.getStateMonday().equals("Pending"))
            viewHolder.stateMonday.setImageResource(R.drawable.warning);

        // Tuesday
        if (medicine.getStateTuesday().equals("Done"))
            viewHolder.stateTuesday.setImageResource(R.drawable.done);
        else if (medicine.getStateTuesday().equals("Cancel"))
            viewHolder.stateTuesday.setImageResource(R.drawable.cancel);
        else if (medicine.getStateTuesday().equals("Taken"))
            viewHolder.stateTuesday.setImageResource(R.drawable.donetaken);
        else if (medicine.getStateTuesday().equals("Pending"))
            viewHolder.stateTuesday.setImageResource(R.drawable.warning);

        // Wednesday
        if (medicine.getStateWednesday().equals("Done"))
            viewHolder.stateWednesday.setImageResource(R.drawable.done);
        else if (medicine.getStateWednesday().equals("Cancel"))
            viewHolder.stateWednesday.setImageResource(R.drawable.cancel);
        else if (medicine.getStateWednesday().equals("Taken"))
            viewHolder.stateWednesday.setImageResource(R.drawable.donetaken);
        else if (medicine.getStateWednesday().equals("Pending"))
            viewHolder.stateWednesday.setImageResource(R.drawable.warning);

        // Thursday
        if (medicine.getStateThursday().equals("Done"))
            viewHolder.stateThursday.setImageResource(R.drawable.done);
        else if (medicine.getStateThursday().equals("Cancel"))
            viewHolder.stateThursday.setImageResource(R.drawable.cancel);
        else if (medicine.getStateThursday().equals("Taken"))
            viewHolder.stateThursday.setImageResource(R.drawable.donetaken);
        else if (medicine.getStateThursday().equals("Pending"))
            viewHolder.stateThursday.setImageResource(R.drawable.warning);

        // Friday
        if (medicine.getStateFriday().equals("Done"))
            viewHolder.stateFriday.setImageResource(R.drawable.done);
        else if (medicine.getStateFriday().equals("Cancel"))
            viewHolder.stateFriday.setImageResource(R.drawable.cancel);
        else if (medicine.getStateFriday().equals("Taken"))
            viewHolder.stateFriday.setImageResource(R.drawable.donetaken);
        else if (medicine.getStateFriday().equals("Pending"))
            viewHolder.stateFriday.setImageResource(R.drawable.warning);

        return convertView;
    }
}

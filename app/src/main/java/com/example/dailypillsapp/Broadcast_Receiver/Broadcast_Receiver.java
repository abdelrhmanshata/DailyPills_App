package com.example.dailypillsapp.Broadcast_Receiver;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.dailypillsapp.Model.Medicine;
import com.example.dailypillsapp.R;
import com.example.dailypillsapp.UI.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Broadcast_Receiver extends BroadcastReceiver {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser CurrentUser = auth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Medicines = database.getReference("Medicines");

    Medicine medicine = null;
    Context context;
    PendingIntent pendingIntent;
    int ID = 0;

    RemoteViews collapsedView, expandedView;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;
        boolean connection = isNetworkAvailable(context);
        ID = intent.getIntExtra("ID", 0);

        Intent repeating_intent = new Intent(context, MainActivity.class);
        repeating_intent.putExtra("ID", ID);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pendingIntent = PendingIntent.getActivity(context, 0, repeating_intent, PendingIntent.FLAG_IMMUTABLE);

        collapsedView = new RemoteViews(context.getPackageName(), R.layout.notification_collapsed);
        expandedView = new RemoteViews(context.getPackageName(), R.layout.notification_expanded);

        if (connection) {
            Medicines.child(CurrentUser.getUid()).child(ID + "")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    medicine = snapshot.getValue(Medicine.class);
                    if (medicine != null) {

                        String repetition;
                        if(medicine.getMedicationRepetitionType().equals("Daily")){
                            repetition = medicine.getMedicationRepetition();
                        }else{
                            repetition =" كل "+medicine.getMedicationRepetition()+" ساعة ";
                        }

                        String info = "حان الأن موعد الدواء " + medicine.getMedicationName() + " المقرر " + medicine.getTime() + " الكمية " + medicine.getMedicationAmount() + medicine.getMedicationType() + " التكرار "+ repetition;
                        collapsedView.setTextViewText(R.id.textViewCollapsedInfo, info);
                        expandedView.setTextViewText(R.id.textViewExpandedInfo, info);
                        pushNotification();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            pushNotification();
        }
    }

    public void pushNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Notification")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.logo)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "You Need Permission", Toast.LENGTH_SHORT).show();
            return;
        }
        notificationManagerCompat.notify(ID, builder.build());
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

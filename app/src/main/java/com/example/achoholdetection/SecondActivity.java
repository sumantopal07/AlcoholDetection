package com.example.achoholdetection;

import java.util.TimeZone;

import android.Manifest;
import android.app.PendingIntent;
import android.app.Service;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;
import android.provider.Settings;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.content.BroadcastReceiver;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class SecondActivity extends AppCompatActivity{


    private int fetch;
   // private BroadcastReceiver broadcastReceiver;
    /* GPS */
    private String phone;
    private LocationManager mLocationManager;
    private FirebaseAuth firebaseAuth;
    private LocationListener mLocationListener;
    private FirebaseDatabase firebaseDatabase;
    private TextView t;
    private Button btn;
    //private String x=new String(""),y=new String("");
    private int currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //startService(new Intent(this, YourService.class));
        setContentView(R.layout.activity_second);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        DatabaseReference databaseReference1 = firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                phone=userProfile.getContactDetails();
               // phone.setText(userProfile.getContactDetails());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SecondActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("number");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                fetch=(int)dataSnapshot.getValue(Integer.class);
                //fetch=x.getNumber();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btn=findViewById(R.id.btnAction);
        currentTime = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30")).get(Calendar.HOUR);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (fetch)
                {
                    case 0:  //send sms to Home
                    {
                        if( (currentTime>20 && currentTime<=23)||(currentTime>=0 && currentTime<=24))
                        {
                        Uri uri = Uri.parse("smsto:"+phone);
                        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                        it.putExtra("sms_body", "Hello, Im in Cab ,Please Call as soon as Possible");
                        startActivity(it);
                        }
                        break;
                    }
                    case 1:   // share gps location
                    {
                        if( (currentTime>20 && currentTime<=23)||(currentTime>=0 && currentTime<=24))
                        {

                            Vibrator q = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            q.vibrate(new long[]{0, 500, 110, 500, 110, 450, 110, 200, 110, 170, 40, 450, 110, 200, 110, 170, 40, 500}, -1);
                            Uri uri = Uri.parse("smsto:"+phone);
                            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                            it.putExtra("sms_body", "Hello, Im in Cab ,Please Call as soon as Possible");
                            startActivity(it);
                        }
                        break;
                    }
                    case 2: //call Family Member
                    {
                        if( (currentTime>20 && currentTime<=23)||(currentTime>=0 && currentTime<=24))
                        {
                            Vibrator q = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            q.vibrate(new long[]{0, 500, 110, 500, 110, 450, 110, 200, 110, 170, 40, 450, 110, 200, 110, 170, 40, 500}, -1);
                            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone)));
                        }
                        break;
                    }
                    case 3:
                    {
                        if( (currentTime>20 && currentTime<=23)||(currentTime>=0 && currentTime<=24))
                        {
                            Vibrator q = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            q.vibrate(new long[]{0, 500, 110, 500, 110, 450, 110, 200, 110, 170, 40, 450, 110, 200, 110, 170, 40, 500}, -1);
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse(
                                            "https://api.whatsapp.com/send?phone=+91"+phone+"&text=I'm%20sending%20my%20location%20"
                                    )));
                        }
                        break;
                    }
                }

            }
        });
    }

}
//class YourService extends Service {
//
//    private static final int NOTIF_ID = 1;
//    private static final String NOTIF_CHANNEL_ID = "Channel_Id";
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId){
//
//        // do your jobs here
//
//        startForeground();
//
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    private void startForeground() {
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                notificationIntent, 0);
//
//        startForeground(NOTIF_ID, new NotificationCompat.Builder(this,
//                NOTIF_CHANNEL_ID) // don't forget create a notification channel first
//                .setOngoing(true)
//                .setContentText("Service is running background")
//                .setContentIntent(pendingIntent)
//                .build());
//    }
//}

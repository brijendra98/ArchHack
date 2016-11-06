package info.heinhtet.apps.archhacksapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.*;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public String user_name, user_age, user_height, user_weight, user_bloodtype,
            user_organdoner, user_diseases, user_allergies, user_notes, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


/*
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                System.out.println(location.getProvider());
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);} */

        final EditText input_name = (EditText) findViewById(R.id.name);
        final DatePicker input_birthday = (DatePicker) findViewById(R.id.birthday);
        final EditText input_height_ft = (EditText) findViewById(R.id.height_ft);
        final EditText input_height_in = (EditText) findViewById(R.id.height_in);
        final EditText input_weight = (EditText) findViewById(R.id.weight);
        final RadioGroup input_blood = (RadioGroup) findViewById(R.id.blood);
        final Switch input_donor = (Switch) findViewById(R.id.donor);
        final EditText input_diseases = (EditText) findViewById(R.id.diseases);
        final EditText input_allergies = (EditText) findViewById(R.id.allergies);
        final EditText input_notes = (EditText) findViewById(R.id.notes);


        final Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){

                user_name = input_name.getText().toString();
                user_age = Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - input_birthday.getYear());
                user_height = input_height_ft.getText().toString() + " ft. " + input_height_in.getText().toString() + " in.";
                user_weight = input_weight.getText().toString() + " lbs";

                int radioButtonID = input_blood.getCheckedRadioButtonId();
                View radioButton = input_blood.findViewById(radioButtonID);
                int id = input_blood.indexOfChild(radioButton);
                RadioButton button = (RadioButton) input_blood.getChildAt(id);
                user_bloodtype = button.getText().toString();

                if (input_donor.isChecked()) user_organdoner = "Organ Donor"; else user_organdoner = "Not Organ Donor";
                user_diseases = "[ " + input_diseases.getText().toString() + " ]";
                user_allergies = "[ " +input_allergies.getText().toString()+ " ]";
                user_notes = "[" +input_notes.getText().toString()+ " ]";

      /*          System.out.println(user_name);
                System.out.println(user_age);
                System.out.println(user_height);
                System.out.println(user_weight);
                System.out.println(user_bloodtype);
                System.out.println(user_organdoner);
                System.out.println(user_diseases);
                System.out.println(user_allergies);
                System.out.println(user_notes);         */

                {Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                    intent.setType("text/plain");
                    hospital_info hosp = new hospital_info();
                    String send_to_hosp;
                    send_to_hosp = hosp.hosp_email;

                    String pt_info;
                    pt_info = "Name: " + user_name + "\r\nAge= " + user_age + "\r\nBloodType = "
                            + user_bloodtype + "\r\nHeight = " + user_height + "\r\nWeight = "
                            + user_weight + "\r\nAllergies= " + user_allergies + "\r\nDiseases= "
                            + user_diseases + "\r\nNotes= " + user_notes;
                    send_to_hosp = "mailto:" + send_to_hosp;
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Emergency");
                    intent.putExtra(Intent.EXTRA_TEXT, pt_info);
                    intent.setData(Uri.parse(send_to_hosp));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);}
            }

        });

    }

    public class hospital_info {
        String hosp_name;
        String hosp_email;
        double dist_from_pt;
        public hospital_info()

        {hosp_email="contact@hospi.tal";
            hosp_name="Nearest best hospital";
            dist_from_pt=0;}

        public hospital_info(String nm, String em, double dist)
        {hosp_email=em;
            hosp_name=nm;
            dist_from_pt=dist;}
    }

}

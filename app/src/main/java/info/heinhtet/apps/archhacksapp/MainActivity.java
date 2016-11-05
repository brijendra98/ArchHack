package info.heinhtet.apps.archhacksapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public String name;
    public int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText user_name = (EditText) findViewById(R.id.name);
        Button submit = (Button) findViewById(R.id.submit);
        submit.hasOnClickListeners(
        );

        name = user_name.getText().toString();
    }
}

package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
    }


    @Override
    protected void onPause() {
        super.onPause();

        // Step 1a: Get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();
        Float floatGPA = Float.parseFloat(etGPA.getText().toString());
        Integer integerGender = rgGender.getCheckedRadioButtonId();

        // Step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 1c: Obtain an instance of the SharedPreferences Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        // Step 1d: Add the key-value pair
        //          The value should be from the variable defined in Step 1a
        prefEdit.putString("Name", strName);
        prefEdit.putFloat("GPA", floatGPA);
        prefEdit.putInt("Gender", integerGender);

        // Step 1e: Call commit() method to save the changes into SharedPreferences
        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 2b: Retrieve the saved data from the SharedPreferences object
        String msg = prefs.getString("Name", "");
        Float gpa = prefs.getFloat("GPA", 0);
        Integer gender = prefs.getInt("Gender",0);

        // Step 2c: Update the UI element with the value
        etName.setText(msg);
        etGPA.setText(Float.toString(gpa));
        rgGender.check(gender);
    }

}

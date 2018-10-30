package com.example.user.week2daily1.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.week2daily1.Constants;
import com.example.user.week2daily1.R;
import com.example.user.week2daily1.models.PersonParcel;

import java.util.ArrayList;
import java.util.List;

public class PersonForm extends AppCompatActivity {

    private EditText etPersonName;
    private EditText etPersonAge;
    private EditText etPersonGender;
    private List<PersonParcel> personList = new ArrayList<PersonParcel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_form);

        //bind views
        etPersonName = findViewById(R.id.etPersonName);
        etPersonAge = findViewById(R.id.etPersonAge);
        etPersonGender = findViewById(R.id.etPersonGender);
    }

    public void addPerson(View view) {
        //get person values from the edittext
        String personName = etPersonName.getText().toString();
        String personAge = etPersonAge.getText().toString();
        String personGender = etPersonGender.getText().toString();

        PersonParcel personParcel = new PersonParcel(personName, personAge, personGender);
        personList.add(personParcel);

        Toast.makeText(this, "Person Added Successfully", Toast.LENGTH_SHORT).show();

        //after adding person, clear out fields, so another person can be entered in if desired
        etPersonName.setText("");
        etPersonAge.setText("");
        etPersonGender.setText("");
    }

    public void viewList(View view) {
        Intent intent = new Intent(getApplicationContext(), PersonList.class);
        intent.putParcelableArrayListExtra(Constants.KEYS.PERSONLIST, (ArrayList) personList);

        startActivity(intent);
    }
}

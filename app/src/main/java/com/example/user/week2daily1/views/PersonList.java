package com.example.user.week2daily1.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.week2daily1.Constants;
import com.example.user.week2daily1.R;
import com.example.user.week2daily1.models.PersonParcel;

import java.util.ArrayList;
import java.util.List;

public class PersonList extends AppCompatActivity {

    private ListView lvPersonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);

        lvPersonList = findViewById(R.id.lvPersonList);

        List<PersonParcel> listFromIntent = getIntent().getParcelableArrayListExtra(Constants.KEYS.PERSONLIST);
        List<String> personList = new ArrayList<String>();

        for(int i=0; i<listFromIntent.size(); i++)
        {
            personList.add(listFromIntent.get(i).toString());
        }

        //Array adapter: parameters are context, the type of list view, and the array
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, personList );

        lvPersonList.setAdapter(arrayAdapter);
    }
}

package com.example.sqliteproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    UserData dataUser;
    Button add_btn, remove_btn, edit_btn;
    EditText name_edt, tuoi_edt;
    ListView lvName;
    ArrayList nameList;
    ArrayList idList;
    ArrayList tuoiList;
    ArrayAdapter adapter;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataUser = new UserData(this,"userdb.sqlite",null,1);
        idList = new ArrayList();
        nameList = new ArrayList();
        tuoiList = new ArrayList();
        name_edt = findViewById(R.id.name_edt);
        tuoi_edt = findViewById(R.id.tuoi_edt);
        add_btn = findViewById(R.id.add_btn);
        remove_btn = findViewById(R.id.remove_btn);
        // edit
        edit_btn = findViewById(R.id.edit_btn);
        lvName = findViewById(R.id.name_lv);
        getNameList();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nameList);
        lvName.setAdapter(adapter);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUser.addUser(new User(name_edt.getText().toString(), Integer.parseInt(tuoi_edt.getText().toString())));
                getNameList();
                adapter.notifyDataSetChanged();
                name_edt.setText("");
                tuoi_edt.setText("");
            }
        });
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.editUser(new User((int)idList.get(index), name_edt.getText().toString(), Integer.parseInt(tuoi_edt.getText().toString())));
                getNameList();
                adapter.notifyDataSetChanged();
                name_edt.setText("");
                tuoi_edt.setText("");
            }
        });
        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUser.removeUser((int)idList.get(index));
                getNameList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,
                        "Succesful", Toast.LENGTH_SHORT).show();

            }
        });

        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>
                                            adapterView, View view, int i, long l) {
                index = i;
                name_edt.setText((String) nameList.get(index));
                tuoi_edt.setText(tuoiList.get(index).toString());

            }
        });
    }
    private ArrayList getNameList(){

        nameList.clear();
        idList.clear();
        tuoiList.clear();
        for (Iterator iterator = dataUser.getAll().iterator(); iterator.hasNext(); ) {
            User user = (User)  iterator.next();
            nameList.add(user.getName());
            nameList.add(user.getTuoi());
            tuoiList.add(user.getTuoi());
            idList.add(user.getId());
        }
        return nameList;
    }
}
package com.example.sqlife_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class AuthorActivity extends AppCompatActivity {
    EditText id,name,adr,email;
    DBHelper dbHelper;
    GridView gv;
    Button btnSave,btnDel,btnSelect,btnUpdate,btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        id = findViewById(R.id.et_id);
        name = findViewById(R.id.et_name);
        adr = findViewById(R.id.et_addr);
        email = findViewById(R.id.et_mail);
        gv = findViewById(R.id.id_gridView);

        dbHelper = new DBHelper(this);
        btnSave = findViewById(R.id.btn_save);
        btnDel = findViewById(R.id.btn_del);
        btnSelect = findViewById(R.id.btn_select);
        btnUpdate = findViewById(R.id.btn_update);
        btnExit = findViewById(R.id.btn_Exit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author();
                author.setId(Integer.parseInt(id.getText().toString().trim()));
                author.setName(name.getText().toString().trim());
                author.setAddress(adr.getText().toString().trim());
                author.setEmail(email.getText().toString().trim());

                if (dbHelper.insertAuthor(author)>0){
                    Toast.makeText(getApplicationContext(),"save ok",Toast.LENGTH_SHORT).show();
                    System.out.println(author);
                }else
                    Toast.makeText(getApplicationContext(),"save not ok",Toast.LENGTH_SHORT).show();

            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ids = id.getText().toString().trim();

                ArrayList<String> listStr = new ArrayList<>();
                if(ids.length()>0){

                    int idi = Integer.parseInt(ids);
                    Author author = dbHelper.getAuthorId(idi);
                    listStr.add(String.valueOf(author.getId()));
                    listStr.add(author.getName());
                    listStr.add(author.getAddress());
                    listStr.add(author.getEmail());


                }
                else{
                    ArrayList<Author> list ;
                    list =  dbHelper.getAll();
                    //System.out.println(list.get(1));

                    for (Author au : list){
                        listStr.add(String.valueOf(au.getId()));
                        listStr.add(au.getName());
                        listStr.add(au.getAddress());
                        listStr.add(au.getEmail());
                    }
                    System.out.println(listStr.toString());


                }
                ArrayAdapter<String> adapter =new ArrayAdapter<>(AuthorActivity.this, android.R.layout.simple_list_item_1,listStr);
                gv.setAdapter(adapter);
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idi = Integer.parseInt(id.getText().toString().trim());
                String listIt ="";
                ArrayList<String> listStr = new ArrayList<>();
                listStr.add(listIt);
                if(dbHelper.delAuthor(idi)){
                    ArrayAdapter<String> adapter =new ArrayAdapter<>(AuthorActivity.this, android.R.layout.simple_list_item_1,listStr);
                    gv.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(),"delete ok",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(),"delete not ok",Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author();
                author.setId(Integer.parseInt(id.getText().toString().trim()));
                author.setName(name.getText().toString().trim());
                author.setAddress(adr.getText().toString().trim());
                author.setEmail(email.getText().toString().trim());


                if (dbHelper.updateAuthor(author)){
                    ArrayList<String> listStr=new ArrayList<>();
                    listStr.add(author.getId()+"");
                    listStr.add(author.getName());
                    listStr.add(author.getAddress());
                    listStr.add(author.getEmail());
                    ArrayAdapter<String> adapter =new ArrayAdapter<>(AuthorActivity.this, android.R.layout.simple_list_item_1,listStr);
                    gv.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(),"update ok",Toast.LENGTH_SHORT).show();
                    System.out.println(author);
                }else
                    Toast.makeText(getApplicationContext(),"update not ok",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
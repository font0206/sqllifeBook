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

public class BookActivity extends AppCompatActivity {
    EditText id,title,idAuthor;
    Button btnSave, btnDel,btnSelect,btnUpdate;
    DBHelper dbHelper;
    GridView gvBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        gvBook = findViewById(R.id.id_gridViewBook);
        id = findViewById(R.id.et_idBook);
        title = findViewById(R.id.et_tile);
        idAuthor = findViewById(R.id.et_idAuthor);
        btnSave = findViewById(R.id.btn_saveBook);
        btnSelect = findViewById(R.id.btn_selectBook);
        btnDel = findViewById(R.id.btn_delBook);
        btnUpdate = findViewById(R.id.btn_updateBook);
        dbHelper = new DBHelper(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setId(Integer.parseInt(id.getText().toString()));
                book.setTitle(title.getText().toString());
                book.setIdAuthor(Integer.parseInt(idAuthor.getText().toString()));
                if (dbHelper.addBook(book)>0){
                    Toast.makeText(getApplicationContext(),"save ok",Toast.LENGTH_SHORT).show();
                    System.out.println(book);
                }else
                    Toast.makeText(getApplicationContext(),"save not ok",Toast.LENGTH_SHORT).show();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idS = id.getText().toString();
                String idAustr = idAuthor.getText().toString();

                ArrayList<Book> list = new ArrayList<>();
                ArrayList<String> listStr = new ArrayList<>();

                if(idAustr.length()>0){
                    int idAu = Integer.parseInt(idAustr);
                    list = dbHelper.getBookWhereIdAuthor(idAu);
                    for (Book book:list){
                        listStr.add(book.getId()+"");
                        listStr.add(book.getTitle());
                        listStr.add(book.getIdAuthor()+"");
                    }
                    System.out.println(listStr.toString());

                }
                else{
                    list = dbHelper.getAllBook();
                    //System.out.println(list.get(1));

                    for (Book book:list){
                        listStr.add(book.getId()+"");
                        listStr.add(book.getTitle());
                        listStr.add(book.getIdAuthor()+"");
                    }
                    System.out.println(listStr.toString());
                }
                ArrayAdapter<String> adapter =new ArrayAdapter<>(BookActivity.this, android.R.layout.simple_list_item_1,listStr);
                gvBook.setAdapter(adapter);



            }
        });
    }
}
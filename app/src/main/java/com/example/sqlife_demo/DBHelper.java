package com.example.sqlife_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper
{
    public DBHelper(@Nullable Context context) {
        super(context,"myDB",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Authors(id integer primary key, name text, address text, email text);");
        sqLiteDatabase.execSQL("create table Books(id integer primary key, title text, " +
                "id_author integer not null constraint id_authour references Authors(id) on DELETE CASCADE ON UPDATE CASCADE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Books");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Authors");

        onCreate(sqLiteDatabase);

    }
    public int insertAuthor(Author author){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",author.getId()+"");
        contentValues.put("name",author.getName());
        contentValues.put("address",author.getAddress());
        contentValues.put("email",author.getEmail());
        int res = (int) db.insert("Authors",null,contentValues);
        db.close();

        return res;
    }
    public ArrayList<Author> getAll(){
        ArrayList<Author> list = new ArrayList<>();
        String sqlStr="select * from Authors";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlStr,null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Author author = new Author(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                System.out.println(author.toString());
                list.add(author);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }
    public Author getAuthorId(int id){
        Author author = null;
        String sql = "select * from Authors where id = "+id;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                author =new Author(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3) );
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return author;
    }
    public boolean delAuthor(int id){
        SQLiteDatabase db = getWritableDatabase();
        int rs = db.delete("Authors","id="+id,null);

        db.close();
        return rs>0;
    }
    public boolean updateAuthor(Author author){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name",author.getName());
        content.put("address",author.getAddress());
        content.put("email",author.getEmail());
        String id = String.valueOf(author.getId());
        int rs = db.update("Authors",content,"id=?", new String[]{id});

        db.close();
        return rs>0;
    }
    //book
    public int addBook(Book book){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",book.getId()+"");
        contentValues.put("title",book.getTitle());
        contentValues.put("id_author",book.getIdAuthor());

        int res = (int) db.insert("Books",null,contentValues);
        db.close();

        return res;
    }
    public ArrayList<Book> getAllBook(){
        ArrayList<Book> list = new ArrayList<>();
        String sqlStr="select * from Books";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlStr,null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Book book = new Book(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
                //System.out.println(book.toString());
                list.add(book);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }
    public Book getBook(int id){
        Book book = null;
        String sql = "select * from Books where id = "+id;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                book =new Book(cursor.getInt(0), cursor.getString(1), cursor.getInt(2) );
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return book;
    }
    public ArrayList<Book> getBookWhereIdAuthor(int idAuthor){
        ArrayList<Book> listBook = new ArrayList<>();

        String sql ="select * from Books where id_author="+idAuthor;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Book book = new Book(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));

                listBook.add(book);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }

        return listBook;
    }


}

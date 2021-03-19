package com.mehmettolgakutlu.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {  // Kodlar içerisine yazılır.

            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null); // Bir veritabanı açmak.
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY, name VARCHAR, age INT)"); // Eğer yoksa bir tablo oluştur.

            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('James',50)");
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('Lars',60)");
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('Kirk',55)");

            //database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Lars'");
            //database.execSQL("UPDATE musicians SET name = 'Kirk Hammett' WHERE id = 3");

            //database.execSQL("DELETE FROM musicians WHERE id = 2");

            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name = 'James'",null);  // Cursor: İmleç , null: filtreleme istemiyorum anlamında
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%'",null);

            Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);


            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");

            while(cursor.moveToNext()) { // Cursor tek tek ilerleyip verileri gezsin. O sırada istediklerim buraya yazılır.
                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getInt(ageIx));
                System.out.println("Id: " + cursor.getInt(idIx));
            }
            cursor.close();


        } catch (Exception e) { // Try' de hata alınırsa programın çökmemesi için burada hatayı yakalayıp gösterebilir.
            e.printStackTrace(); // Hatanın detaylarını yazdırmak.
        }


    }
}
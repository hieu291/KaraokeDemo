package karaoke.demo.com.baitaprenluyenkaraoke;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import karaoke.demo.com.baitaprenluyenkaraoke.adapter.SongAdapter;
import karaoke.demo.com.baitaprenluyenkaraoke.model.Song;

public class MainActivity extends AppCompatActivity {
    public static String DATABASE_NAME = "dbKaraoke.sqlite";
    String DB_PATH="/databases/";
    public static SQLiteDatabase database = null;
    public static String TablenameSong = "ArirangSongList";
    ListView lvSong;
    ArrayAdapter<Song> adapterallsong;
    private Context  context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processCopy();
        addcontrol();
        showallSong();
    }

    private void showallSong() {
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = database.query(TablenameSong,null,null,null,null,null,null);
        adapterallsong.clear();
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String song = cursor.getString(1);
            String artist = cursor.getString(3);
            int like = cursor.getInt(5);
            Song bh = new Song(id,song,artist,like);
            adapterallsong.add(bh);

        }
    }

    private void addcontrol() {
        lvSong=findViewById(R.id.lvSong);
        adapterallsong= new SongAdapter(MainActivity.this,R.layout.item);
        lvSong.setAdapter(adapterallsong);
    }
//ham thuc hien coppy
    private void processCopy(){
        try
        {
            File dbFile = getDatabasePath(DATABASE_NAME);
            if (!dbFile.exists()){
                coppyDatabasefromAssets();
            }
        }
        catch (Exception ex)
        {
            Log.e("LOI",ex.toString());
        }
    }

    private String getDatabasePath(){
        return getApplicationInfo().dataDir+DB_PATH+DATABASE_NAME;
    }
    //coppy database vao assets
    private void coppyDatabasefromAssets() {
        try{
            InputStream myinput = getAssets().open(DATABASE_NAME);
            String outfile = getDatabasePath();
            File file = new File(getApplicationInfo().dataDir+DB_PATH);
            if (!file.exists()){
                file.mkdir();
                OutputStream myOutfile = new FileOutputStream(outfile);
                byte []buffer = new byte[1024];
                int length;
                while ((length=myinput.read(buffer))>0){
                    myOutfile.write(buffer,0,length);
                }
                myOutfile.flush();
                myinput.close();
                myOutfile.close();
            }
        }catch (Exception ex){
            Log.e("LOI",ex.toString());
        }
    }
}

package alexadamenko.euro2016;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Gallery extends Activity {

    GridView gv;
    ArrayList<File> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery);

        list =  imageReader(new File(
                Environment.getExternalStorageDirectory(), "footballFotos"));

        gv = (GridView) findViewById(R.id.gridView);
        gv.setAdapter(new GridAdapter());
    }

    ArrayList<File> imageReader(File root){

        ArrayList<File> a = new ArrayList<>();
        File[] files = root.listFiles();
        for(int i =0;i<files.length;i++){
            if(files[i].isDirectory()){
                a.addAll(imageReader(files[i]));

            }else{
                if(files[i].getName().endsWith(".jpg")){

                    Bitmap bitmap = BitmapFactory.decodeFile(files[i].getAbsolutePath());
                    Bitmap resized = Bitmap.createScaledBitmap(bitmap, 150, 150, true);

                    OutputStream os;
                    try {
                        os = new FileOutputStream(files[i]);
                        resized.compress(Bitmap.CompressFormat.JPEG, 70, os);
                        os.flush();
                        os.close();
                    } catch (Exception e) {
                        Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                    }

                    a.add(files[i]);
                }
            }
        }
        return a;
    }



    class GridAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.single_grid, parent, false);
            ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);

            iv.setImageURI((Uri.parse(getItem(position).toString())));

            return convertView;
        }
    }
}

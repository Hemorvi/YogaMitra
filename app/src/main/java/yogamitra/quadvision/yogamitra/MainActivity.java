package yogamitra.quadvision.yogamitra;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


public class MainActivity extends ActionBarActivity {

    public MediaController mc;

    Context context;

    GridView grid;

    VideoView vd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Yoga Mitra");

        context = this;

        String[] text = {
                "Yoga Dictionary","Yoga Gyan","Do it your self",
                "List Your yoga business","Yogi inside","Yoga Shop",
        } ;
        int[] imageId = {

                R.drawable.yoga_dictionary,R.drawable.yoga_gyan,R.drawable.self_yoga,
                R.drawable.business_list,R.drawable.yogi_inside,R.drawable.yoga_shop,
        };
        GridImageAdapter adapter = new GridImageAdapter(getApplicationContext(), text, imageId);
        grid=(GridView)findViewById(R.id.main_grid);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                switch(position)
                {
                    case 2:
                        grid.setVisibility(view.GONE);
                        vd = (VideoView) findViewById(R.id.surface_view);
                        vd.setVisibility(view.VISIBLE);
                        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.yoga);
                        mc = new MediaController(context);
                        vd.setMediaController(mc);
                        vd.requestFocus();
                        vd.setVideoURI(uri);
                        vd.start();
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
      // optional depending on your needs

        grid.setVisibility(View.VISIBLE);
        vd.setVisibility(View.GONE);

    }

}

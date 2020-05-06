package encityproject.rightcodeit.com.encityproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private ImageView ivLogoDar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

       ivLogoDar = findViewById(R.id.ivLogoDar);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.disapp);
        ivLogoDar.startAnimation(anim);


        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivLogoDar.setVisibility(View.INVISIBLE);
                Intent myIntent = new Intent(MainActivity.this, MainActivityWithNaviDrawer.class);
                MainActivity.this.startActivity(myIntent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


}

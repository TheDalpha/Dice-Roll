package com.example.dice;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int image1;
    int image2;
    int sum;
    List<String> list = new ArrayList<String>();
    Animation anim1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnRoll = findViewById(R.id.btnRoll);
        anim1 = AnimationUtils.loadAnimation(this, R.anim.rotate);
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        generateRandomNumber();
                    }
                }, 1000);
                animate();

            }
        });
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    private void generateRandomNumber() {
        ImageView imga1 = findViewById(R.id.imgDice1);
        ImageView imga2 = findViewById(R.id.imgDice2);
        Random rand = new Random();
        image1 = rand.nextInt(6) + 1;
        image2 = rand.nextInt(6) + 1;
        sum = image1 + image2;
        if (list.size()>= 5) {
            list.remove(list.size()-1);
        }
        String suml = String.valueOf(image1) + " + " + String.valueOf(image2) + " = " + String.valueOf(sum);

        list.add(0, suml);
        ListView tvList = findViewById(R.id.lvPrior);
        tvList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        String img1 = "d" + image1;
        String img2 = "d" + image2;
        Resources res = getResources();
        int id = res.getIdentifier(img1, "drawable", getPackageName());
        int id2 = res.getIdentifier(img2, "drawable", getPackageName());

        imga1.setImageResource(id);
        imga2.setImageResource(id2);
    }

    private void clear() {
        list.clear();
        ListView tvList = findViewById(R.id.lvPrior);
        tvList.setAdapter(null);
    }
    private void animate() {
        ImageView imga1 = findViewById(R.id.imgDice1);
        ImageView imga2 = findViewById(R.id.imgDice2);
        imga1.startAnimation(anim1);
        imga2.startAnimation(anim1);
    }
}

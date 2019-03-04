package com.example.dice;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final List<ImageView> dices = new ArrayList();
    int image1;
    int sum;
    String rollNumber;
    public static ArrayList<String> list = new ArrayList<String>();
    Animation anim1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnRoll = findViewById(R.id.btnRoll);
        ImageView dice1 = findViewById(R.id.imgDice1);
        dices.add(dice1);
        anim1 = AnimationUtils.loadAnimation(this, R.anim.rotate);
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomNumber();


            }
        });
        GridLayout glDicer = findViewById(R.id.glDice);
        Button btnAdd = findViewById(R.id.btnPlus);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDice();
            }
        });
        Button btnRemove = findViewById(R.id.btnMinus);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDice();
            }
        });

        if(isLandscape()){
            glDicer.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
            glDicer.setColumnCount(3);
            glDicer.setRowCount(2);
            dice1.getLayoutParams().height = 200;
            dice1.getLayoutParams().width = 200;
        } else {
            glDicer.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
            glDicer.setColumnCount(2);
            glDicer.setRowCount(3);
        }

        Button btnHist = findViewById(R.id.btnHist);
        btnHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showHistory();
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        ImageView dice1 = findViewById(R.id.imgDice1);
        GridLayout glDicer = findViewById(R.id.glDice);
        if (isLandscape()) {
            glDicer.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
            glDicer.setColumnCount(3);
            glDicer.setRowCount(2);
            dice1.getLayoutParams().height = 200;
            dice1.getLayoutParams().width = 200;
        } else {
            glDicer.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
            glDicer.setColumnCount(2);
            glDicer.setRowCount(3);
        }
    }

    private List addDice() {
        if (dices.size() <= 5) {
            GridLayout glDicer = findViewById(R.id.glDice);
            ImageView diceView = new ImageView(this);


                diceView.setImageResource(R.drawable.d1);

                glDicer.addView(diceView);
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.rightMargin = 5;
                param.topMargin = 5;
                param.setGravity(Gravity.CENTER);
                diceView.setLayoutParams(param);
            if(isLandscape()){
                diceView.getLayoutParams().height = 200;
                diceView.getLayoutParams().width = 200;
            }
                dices.add(diceView);
            }

        return null;
    }

    private void removeDice() {
        GridLayout glDicer = findViewById(R.id.glDice);
        int iSize = dices.size() - 1;
        glDicer.removeViewAt(iSize);
        dices.remove(iSize);
    }

    private void generateRandomNumber() {
        for (final ImageView dice: dices) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {


        Random rand = new Random();
        image1 = rand.nextInt(6) + 1;
        sum = sum + image1;
        String number = String.valueOf(image1) + " ";
        if (rollNumber == null) {
            rollNumber = number;
        } else {
            rollNumber = rollNumber + " + " + number;
        }
        String img = "d" + image1;
        Resources res = getResources();
        int id = res.getIdentifier(img, "drawable", getPackageName());
        dice.setImageResource(id);
                }
            }, 1000);
        animate(dice);

        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                rollNumber = rollNumber + "= " + String.valueOf(sum);
        list.add(rollNumber);
        rollNumber = "";
        sum = 0;
            }
        }, 1001);
    }

    private void clear() {
        //list.clear();
        ListView tvList = findViewById(R.id.lvPrior);
        tvList.setAdapter(null);
    }
    private void animate(ImageView dice) {
        dice.startAnimation(anim1);
    }

    private boolean isLandscape()
    {
        Configuration config = getResources().getConfiguration();
        return config.orientation == config.ORIENTATION_LANDSCAPE;
    }
}

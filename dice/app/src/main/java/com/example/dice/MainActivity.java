package com.example.dice;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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


    List<ImageView> dices = new ArrayList();
    int image1;
    int image2;
    int sum;
    //List<String> list = new ArrayList<String>();
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
       /* Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });*/

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
        GridLayout glDicer = findViewById(R.id.glDice);
        glDicer.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        glDicer.setColumnCount(2);
        glDicer.setRowCount(3);
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
        String img = "d" + image1;
        Resources res = getResources();
        int id = res.getIdentifier(img, "drawable", getPackageName());
        dice.setImageResource(id);
                }
            }, 1000);
        animate(dice);
        }
    }

    private void clear() {
        //list.clear();
        ListView tvList = findViewById(R.id.lvPrior);
        tvList.setAdapter(null);
    }
    private void animate(ImageView dice) {
        dice.startAnimation(anim1);
    }
}

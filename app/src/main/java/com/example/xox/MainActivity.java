package com.example.xox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int turn;
    private List<ImageView> chips = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.turn = 0;
        this.chips = getAllChips();
        hideAllChips();

    }


    public void userClicked(View view){
        ImageView chip = (ImageView) findViewById(view.getId());

        chip.setImageAlpha(255);
    }

    public void hideAllChips(){
        for (ImageView chip : this.chips){
            chip.setImageAlpha(0);

        }
    }

    public void showAllChips(){
        for (ImageView chip : this.chips){
            chip.setImageAlpha(255);
        }
    }


    public List<ImageView> getAllChips(){
        List<ImageView> allChips = new ArrayList<ImageView>();
        for (int i = 1; i < 10;i++){
            int id = getResources().getIdentifier("chip_"+i,"id", getApplicationContext().getPackageName());
            allChips.add((ImageView) findViewById(id));

        }
        return allChips;
    }


    public void resetBoard(View view){
        hideAllChips();

    }
}

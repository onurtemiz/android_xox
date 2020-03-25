package com.example.xox;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {
    private int turn;
    private List<ImageView> chips = new ArrayList<ImageView>();
    private boolean gameOver;
    private static final String TAG = "MyActivity";
    private int default_tag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.turn = 0;
        this.chips = getAllChips();
        resetGame();
        gameOver = false;

    }


    public void userClicked(View view) {
        ImageView chip = (ImageView) findViewById(view.getId());

        if (isChipValid(chip) && !gameOver) {
            showChip(chip);
            if (!isGameOver()) {
                changeTurn();
            }
        }
    }

    public boolean isGameOver() {
        return (isWon() || isTie());
    }

    public boolean isWon() {
        return (isHorWon() || isVerWon() || isDiagWon());

    }

    public boolean isDiagWon() {
        boolean allSame = false;
        int[][] diagLocs = new int[2][];
        diagLocs[0] = new int[]{1, 5, 9};
        diagLocs[1] = new int[]{3, 5, 7};

        for (int[] locs : diagLocs) {
            int mainColor = (Integer) findChipById(locs[0]).getTag();
            if (!(mainColor == default_tag)) {
                allSame = true;

                for (int loc : locs) {
                    int nextColor = (Integer) findChipById(loc).getTag();
                    if (!(mainColor == nextColor)) {
                        allSame = false;
                    }
                }

                if (allSame) return true;
            }
        }

        return allSame;
    }

    public boolean isHorWon() {
        boolean allSame = false;
        for (int i = 1; i < 8; i += 3) {
            int mainColor = (Integer) findChipById(i).getTag();
            if (!(mainColor == default_tag)) {
                allSame = true;
                for (int k = 1; k < 3; k++) {
                    int nextColor = (Integer) findChipById(i + k).getTag();
                    if (!(mainColor == nextColor)) {
                        allSame = false;
                    }
                }
                if (allSame) return true;
            }
        }
        return allSame;
    }

    public boolean isVerWon() {
        boolean allSame = false;
        for (int i = 1; i < 4; i++) {
            int mainColor = (Integer) findChipById(i).getTag();
            if (!(mainColor == default_tag)) {
                allSame = true;
                for (int k = 3; k < 7; k += 3) {
                    int nextColor = (Integer) findChipById(i + k).getTag();
                    if (!(mainColor == nextColor)) {
                        allSame = false;
                    }
                }
                if (allSame) return true;
            }
        }
        return allSame;
    }

    public ImageView findChipById(int i) {
        int id = getResources().getIdentifier("chip_" + i, "id", getApplicationContext().getPackageName());
        ImageView chip = (ImageView) findViewById(id);
        return chip;
    }

    public boolean isTie() {
        boolean allDone = true;
        for (ImageView chip : this.chips) {
            if (chip.getImageAlpha() == 0) {
                allDone = false;
            }
        }
        return allDone;
    }

    public void changeTurn() {
        this.turn = this.turn == 0 ? 1 : 0;
    }

    public void showChip(ImageView chip) {
        int chipColor = this.turn == 0 ? R.drawable.red : R.drawable.yellow;
        chip.setImageResource(chipColor);
        chip.setTag(chipColor);
        chip.setImageAlpha(255);

    }

    public boolean isChipValid(ImageView chip) {
        return chip.getImageAlpha() == 0 ? true : false;

    }

    public void hideAllChips() {
        for (ImageView chip : this.chips) {
            chip.setImageAlpha(0);

        }
    }

    public void showAllChips() {
        for (ImageView chip : this.chips) {
            chip.setImageAlpha(255);
        }
    }


    public List<ImageView> getAllChips() {
        List<ImageView> allChips = new ArrayList<ImageView>();
        for (int i = 1; i < 10; i++) {
            allChips.add(findChipById(i));

        }
        return allChips;
    }


    public void setAllChips() {
        for (ImageView chip : this.chips) {
            chip.setTag(default_tag);
        }
    }

    public void resetBoard(View view) {
        resetGame();
    }

    public void resetGame() {
        hideAllChips();
        setAllChips();
    }
}

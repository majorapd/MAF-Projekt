package hu.szte.mobilalk.ttc;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final char OX = 'O';
    static final char IX = 'X';
    static final char EMPTY = '-';

    int oxWins;
    int ixWins;

    SharedPreferences mPreferences;

    char[][] map = new char[3][3];
    char currentPlayer = OX;
    boolean win;
    int cnt;

    ImageView currImg;

    void init(){
        switchPlayers();
        cnt = 0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                    map[i][j] = EMPTY;
            }
        }
        win = false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        mPreferences = getApplicationContext().getSharedPreferences("Fields", 0);

        SharedPreferences loadStat = getApplicationContext().getSharedPreferences("Stats", 0);
        oxWins = loadStat.getInt("oxW", 0);
        ixWins = loadStat.getInt("ixW", 0);

        currImg = findViewById(R.id.currentiV);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
       /* resumeGame();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String tmp = "iV" + String.valueOf(i) + String.valueOf(j);
                ImageView tmpimg = findViewById(R.id.)
            }
        }*/
    }

    @Override
    protected void onPause(){
        super.onPause();

       // saveGame();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.item_stat:
                statClick();
                break;
            case R.id.item_reset:
                resetStat();
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void iVClick(View v){
        if(win){
            createToast(getString(R.string.already_won));
            return;
        }
        int id = v.getId();
        ImageView img = findViewById(id);
        switch(id){
            case R.id.iV00:

                if(currentPlayer == OX)
                {
                    if(map[0][0] == EMPTY){
                        img.setImageResource(R.drawable.ox);
                        map[0][0] = OX;
                        cnt++;

                        if(map[0][1] == OX && map[0][2] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);
                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV01);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV02);
                            img.setImageResource(R.drawable.wox);

                            win = true;
                            break;
                        }
                        if(map[1][0] == OX && map[2][0] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV10);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV20);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }
                        if(map[1][1] == OX && map[2][2] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV22);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                } else if(currentPlayer == IX){
                    if(map[0][0] == EMPTY) {
                        img.setImageResource(R.drawable.ix);
                        map[0][0] = IX;
                        cnt++;

                        if(map[0][1] == IX && map[0][2] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV01);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV02);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[1][0] == IX && map[2][0] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV10);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV20);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[1][1] == IX && map[2][2] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV22);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                }
                break;

            case R.id.iV01:

                if(currentPlayer == OX)
                {
                    if(map[0][1] == EMPTY) {
                        img.setImageResource(R.drawable.ox);
                        map[0][1] = OX;
                        cnt++;

                        if(map[0][0] == OX && map[0][2] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV00);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV02);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }
                        if(map[1][1] == OX && map[2][1] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV21);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                } else if(currentPlayer == IX){
                    if(map[0][1] == EMPTY) {
                        img.setImageResource(R.drawable.ix);
                        map[0][1] = IX;
                        cnt++;

                        if(map[0][0] == IX && map[0][2] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV00);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV02);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[1][1] == IX && map[2][1] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV21);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                }
                break;
            case R.id.iV02:
                if(currentPlayer == OX)
                {
                    if(map[0][2] == EMPTY) {
                        img.setImageResource(R.drawable.ox);
                        map[0][2] = OX;
                        cnt++;

                        if(map[0][0] == OX && map[0][1] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV01);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV00);
                            img.setImageResource(R.drawable.wox);

                            win = true;
                            break;
                        }
                        if(map[1][2] == OX && map[2][2] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV12);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV22);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }
                        if(map[1][1] == OX && map[2][0] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV20);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }
                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                } else if(currentPlayer == IX){
                    if(map[0][2] == EMPTY) {
                        img.setImageResource(R.drawable.ix);
                        map[0][2] = IX;
                        cnt++;

                        if(map[0][0] == IX && map[0][1] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV01);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV00);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[1][2] == IX && map[2][2] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV12);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV22);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[1][1] == IX && map[2][0] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV20);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                }
                break;
            case R.id.iV10:
                if(currentPlayer == OX)
                {
                    if(map[1][0] == EMPTY) {
                        img.setImageResource(R.drawable.ox);
                        map[1][0] = OX;
                        cnt++;

                        if(map[1][1] == OX && map[1][2] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV12);
                            img.setImageResource(R.drawable.wox);

                            win = true;
                            break;
                        }
                        if(map[0][0] == OX && map[2][0] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV00);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV20);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                } else if(currentPlayer == IX){
                    if(map[1][0] == EMPTY) {
                        img.setImageResource(R.drawable.ix);
                        map[1][0] = IX;
                        cnt++;

                        if(map[1][1] == IX && map[1][2] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV12);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[0][0] == IX && map[2][0] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV00);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV20);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                }
                break;
            case R.id.iV11:
                if(currentPlayer == OX)
                {
                    if(map[1][1] == EMPTY) {
                        img.setImageResource(R.drawable.ox);
                        map[1][1] = OX;
                        cnt++;

                        if(map[1][0] == OX && map[1][2] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV10);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV12);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }
                        if(map[0][1] == OX && map[2][1] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV01);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV21);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }
                        if(map[0][0] == OX && map[2][2] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV00);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV22);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }
                        if(map[0][2] == OX && map[2][0] == OX){
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV20);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV02);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                } else if(currentPlayer == IX){
                    if(map[1][1] == EMPTY) {
                        img.setImageResource(R.drawable.ix);
                        map[1][1] = IX;
                        cnt++;

                        if(map[1][0] == IX && map[1][2] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV10);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV12);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[0][1] == IX && map[2][1] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV01);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV21);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[0][0] == IX && map[2][2] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV00);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV22);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[0][2] == IX && map[2][0] == IX){
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV20);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV02);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                }
                break;
            case R.id.iV12:
                if(currentPlayer == OX)
                {
                    if(map[1][2] == EMPTY) {
                        img.setImageResource(R.drawable.ox);
                        map[1][2] = OX;
                        cnt++;

                        if(map[1][0] == OX && map[1][1] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV10);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }
                        if(map[0][2] == OX && map[2][2] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV22);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV02);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                } else if(currentPlayer == IX){
                    if(map[1][2] == EMPTY) {
                        img.setImageResource(R.drawable.ix);
                        map[1][2] = IX;
                        cnt++;

                        if(map[1][0] == IX && map[1][1] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV10);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[0][2] == IX && map[2][2] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV02);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV22);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                }
                break;
            case R.id.iV20:
                if(currentPlayer == OX)
                {
                    if(map[2][0] == EMPTY) {
                        img.setImageResource(R.drawable.ox);
                        map[2][0] = OX;
                        cnt++;

                        if(map[0][0] == OX && map[1][0] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV00);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV10);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }
                        if(map[2][1] == OX && map[2][2] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV21);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV22);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }
                        if(map[1][1] == OX && map[0][2] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV02);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                } else if(currentPlayer == IX){
                    if(map[2][0] == EMPTY) {
                        img.setImageResource(R.drawable.ix);
                        map[2][0] = IX;
                        cnt++;

                        if(map[0][0] == IX && map[1][0] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV00);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV10);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[2][1] == IX && map[2][2] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV21);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV22);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[1][1] == IX && map[0][2] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV02);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                }
                break;
            case R.id.iV21:
                if(currentPlayer == OX)
                {
                    if(map[2][1] == EMPTY) {
                        img.setImageResource(R.drawable.ox);
                        map[2][1] = OX;
                        cnt++;

                        if(map[0][1] == OX && map[1][1] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV01);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }
                        if(map[2][0] == OX && map[2][2] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV20);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV22);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                } else if(currentPlayer == IX){
                    if(map[2][1] == EMPTY) {
                        img.setImageResource(R.drawable.ix);
                        map[2][1] = IX;
                        cnt++;

                        if(map[0][1] == IX && map[1][1] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV01);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[2][0] == IX && map[2][2] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV20);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV22);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                }
                break;
            case R.id.iV22:
                if(currentPlayer == OX)
                {
                    if(map[2][2] == EMPTY) {
                        img.setImageResource(R.drawable.ox);
                        map[2][2] = OX;
                        cnt++;

                        if(map[2][0] == OX && map[2][1] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV20);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV21);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }
                        if(map[0][2] == OX && map[1][2] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV12);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV02);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }
                        if(map[1][1] == OX && map[0][0] == OX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wox);

                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wox);
                            img = findViewById(R.id.iV00);
                            img.setImageResource(R.drawable.wox);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                } else if(currentPlayer == IX){
                    if(map[2][2] == EMPTY) {
                        img.setImageResource(R.drawable.ix);
                        map[2][2] = IX;
                        cnt++;

                        if(map[2][0] == IX && map[2][1] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV20);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV21);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[0][2] == IX && map[1][2] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV12);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV02);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }
                        if(map[1][1] == IX && map[0][0] == IX) {
                            createToast(currentPlayer + " " + getString(R.string.win));
                            saveStat(currentPlayer);

                            img.setImageResource(R.drawable.wix);

                            img = findViewById(R.id.iV11);
                            img.setImageResource(R.drawable.wix);
                            img = findViewById(R.id.iV00);
                            img.setImageResource(R.drawable.wix);
                            win = true;
                            break;
                        }

                        if(ifDraw()) {
                            createToast(getString(R.string.draw));
                            return;
                        }
                        switchPlayers();
                    } else {
                        createToast(getString(R.string.taken));
                    }
                }
                break;
        }

    }

    void switchPlayers(){
        if(currentPlayer == OX) {
            currentPlayer = IX;
            currImg.setImageResource(R.drawable.ix);
        }else{
            currentPlayer = OX;
            currImg.setImageResource(R.drawable.ox);
        }
    }

    boolean ifDraw(){
        if(cnt == 9){
            return true;
        }
        return false;
    }


    public void createToast(String msg){
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void newGame(View view){
        init();
        ImageView img;
        img = findViewById(R.id.iV00);
        img.setImageResource(R.drawable.empty);
        img = findViewById(R.id.iV01);
        img.setImageResource(R.drawable.empty);
        img = findViewById(R.id.iV02);
        img.setImageResource(R.drawable.empty);
        img = findViewById(R.id.iV10);
        img.setImageResource(R.drawable.empty);
        img = findViewById(R.id.iV11);
        img.setImageResource(R.drawable.empty);
        img = findViewById(R.id.iV12);
        img.setImageResource(R.drawable.empty);
        img = findViewById(R.id.iV20);
        img.setImageResource(R.drawable.empty);
        img = findViewById(R.id.iV21);
        img.setImageResource(R.drawable.empty);
        img = findViewById(R.id.iV22);
        img.setImageResource(R.drawable.empty);
    }

    public void saveGame(){
        SharedPreferences save = getApplicationContext().getSharedPreferences("Fields", 0);
        SharedPreferences.Editor editor = save.edit();

        for(int i=0; i<3;i++){
            for(int j=0; j<3;j++){
                editor.putString(String.valueOf(i) + String.valueOf(j), String.valueOf(map[i][j]));
            }
        }

        editor.apply();
    }

    public void resumeGame(){
        char[] tmp;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                tmp = mPreferences.getString(String.valueOf(i) + String.valueOf(j), String.valueOf(EMPTY)).toCharArray();
                map[i][j] = tmp[0];
            }
        }
    }

    public void saveStat(char c){
        SharedPreferences saveStat = getApplicationContext().getSharedPreferences("Stats", 0);
        SharedPreferences.Editor StatEditor = saveStat.edit();
        switch(c){
            case OX:
                oxWins++;

                StatEditor.putInt("oxW", oxWins);
                break;
            case IX:
                ixWins++;

                StatEditor.putInt("ixW", ixWins);

                break;
            default:
                StatEditor.putInt("oxW", oxWins);
                StatEditor.putInt("ixW", ixWins);
                break;
        }

        StatEditor.apply();
    }

     public void statClick(){
            AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);
        myAlertBuilder.setTitle(R.string.wins);
        myAlertBuilder.setMessage("O: " + String.valueOf(oxWins) +
                                    "\n" + "X: " + String.valueOf(ixWins));

        myAlertBuilder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        myAlertBuilder.show();

    }

    public void resetStat(){
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);
        myAlertBuilder.setTitle(R.string.reset_title);
        myAlertBuilder.setMessage(R.string.reset_msg);

        myAlertBuilder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                oxWins = 0;
                ixWins = 0;
                saveStat(EMPTY);
                createToast(getString(R.string.deleted));
            }
        });

        myAlertBuilder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        myAlertBuilder.show();

    }

}

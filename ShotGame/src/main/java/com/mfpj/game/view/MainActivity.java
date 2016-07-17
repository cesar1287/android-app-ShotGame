package com.mfpj.game.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.mfpj.game.R;
import com.mfpj.game.engine.EngineSimpleGame;


public class MainActivity extends Activity {

    private Context mContext;
    AlertDialog alert;
    private boolean fiveClickstoAbout = false;
    private int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mContext = this;
        findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, EngineSimpleGame.class));
            }
        });
        findViewById(R.id.about_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("How to Play");
                builder.setMessage("This Game have 10 phases\n\nTo win the Game, you need to kill 10 enemies in phase 1, " +
                        "20 in phase 2, ..., and 100 in phase 10");
                alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                cont++;
                if (fiveClickstoAbout) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Sobre");
                    builder.setMessage("Trabalho final da disciplina :\n\nMatemática e Física para Jogos - SMD\n\nPela Universidade Federal do Ceará\n\n" +
                            "Aluno: César Rodrigues\nCurso: Computação\nProfessor: José Gilvan");
                    alert = builder.create();
                    alert.show();
                }

                if(cont==5) {
                    this.fiveClickstoAbout = true;
                    cont=0;
                }

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        fiveClickstoAbout = false;
                        cont=0;
                    }
                }, 5000);
                break;
        }
        return super.onTouchEvent(event);
    }
}

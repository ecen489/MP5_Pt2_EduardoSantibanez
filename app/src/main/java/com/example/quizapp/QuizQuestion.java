package com.example.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class QuizQuestion extends AppCompatActivity {
    public int randomNum = ThreadLocalRandom.current().nextInt(0, 7 + 1);
    public static String[] questions =new String[14];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);
        Bundle extras = getIntent().getExtras();
        int begin=0;
        if (extras != null) {
            TextView q = (TextView) findViewById(R.id.Title);
            if (extras.containsKey("0")) {
                String topic = extras.getString("0");
                q.setText(topic);
                begin=0;
            }else if (extras.containsKey("1")) {
                String topic = extras.getString("1");
                q.setText(topic);
                begin=14;
            }else {
                String topic = extras.getString("2");
                q.setText(topic);
                begin=28;
            }
            Scanner scan=new Scanner(getResources().openRawResource(R.raw.questions));
            int i=0;
            while(scan.hasNextLine()){
                if(i==begin){
                    for(i=0;i<=13;i++) {
                        String line1 = scan.nextLine();
                        questions[i] = line1;
                    }
                }
                scan.nextLine();
                i++;
            }
            scan.close();
            TextView dispQ=(TextView) findViewById(R.id.rQuestion);
            dispQ.setText(questions[randomNum]);
            System.out.println(randomNum+"poi");
        }
    }

    public void validate(View view) {
        EditText ans=(EditText) findViewById(R.id.userResponse);
        String uans=ans.getText().toString();
        System.out.println(randomNum+"jkl");
        System.out.println(questions[randomNum+7]);
        if(uans.equals(questions[randomNum+7])){
            System.out.println("correct");
            Intent valid=new Intent();
            valid.putExtra("ans",1);
            setResult(RESULT_OK,valid);
            finish();
        }else{
            System.out.println("incorrect");
            Intent valid=new Intent();
            valid.putExtra("ans",0);
            setResult(RESULT_CANCELED,valid);
            finish();
        }
    }
}
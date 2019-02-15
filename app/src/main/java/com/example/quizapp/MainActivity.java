package com.example.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE_ANS = 2468;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create an OnItemClickListener
        AdapterView.OnItemClickListener itemListener=new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> View, View view, int position, long id) {
                if(position==0){
                    Intent intent=new Intent(MainActivity.this,QuizQuestion.class);
                    intent.putExtra("0","Bible Question");
                    startActivityForResult(intent,REQ_CODE_ANS);
                }
                if(position==1){
                    Intent intent=new Intent(MainActivity.this,QuizQuestion.class);
                    intent.putExtra("1","Android Question");
                    startActivityForResult(intent,REQ_CODE_ANS);
                }
                if(position==2){
                    Intent intent=new Intent(MainActivity.this,QuizQuestion.class);
                    intent.putExtra("2","AI Question");
                    startActivityForResult(intent,REQ_CODE_ANS);
                }
            }
        };
        //Add the listener to the list view
        ListView listView=(ListView) findViewById(R.id.topics);
        listView.setOnItemClickListener(itemListener);
    }

    public void resetscore(View view) {
        TextView v=(TextView) findViewById(R.id.score);
        v.setText(String.valueOf(0));
    }
    //BackIntent returns control back here
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode==REQ_CODE_ANS) {
            if(resultCode==-1){
                TextView v=(TextView) findViewById(R.id.score);
                String current1=v.getText().toString();
                Integer current=Integer.parseInt(current1);
                Integer ret_ans = intent.getIntExtra("ans",4);
                System.out.println(ret_ans);
                int finalans=current+ret_ans;
                v.setText(String.valueOf(finalans));
                Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
            }

        }
    }
}

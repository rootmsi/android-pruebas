package com.example.git;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_2 extends AppCompatActivity {

    Button bt02,bt03,bt04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        bt02=findViewById(R.id.button5);
        bt03=findViewById(R.id.button6);
        bt04=findViewById(R.id.button7);

    }

    public void ira3(View view){

        Intent ir = new Intent(this,activity_3.class);
        startActivity(ir);

    }

    public void ira4(View view){

        Intent ir = new Intent(this,activity_4.class);
        startActivity(ir);

    }

    public void ira5(View view){

        Intent ir = new Intent(this,activity_5.class);
        startActivity(ir);

    }

}

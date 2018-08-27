package com.example.android.notification;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
public class minamount extends Activity implements OnClickListener
{
    EditText amount;
    Button b;
    public static int min_amount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minamount);
        TextView t=(TextView)findViewById(R.id.show);
        t.setText("Current Minimum Amount is :"+min_amount);
        amount = (EditText) findViewById(R.id.minimum);
        b = (Button)findViewById(R.id.buttonmin);
        b.setOnClickListener(this);

    }


    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.buttonmin)
        {
            String name1=amount.getText().toString();

            String old_value;
            double value;
            if(name1.equals(""))
            {
                Toast.makeText(this, "enter name and amount",Toast.LENGTH_LONG).show();
            }
            else
            {
                SharedPreferences preferences = getSharedPreferences("min", 0);
                old_value = preferences.getString("old_min", "0.00");
                value=Double.parseDouble(name1);
                Editor editor = preferences.edit();
                editor.putString("old_min", name1);

                editor.commit();
                 min_amount = Integer.parseInt(name1);
                Intent intent = new Intent(minamount.this, PocketMain.class);
                startActivity(intent);
                finish();
            }
        }

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, PocketMain.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    }








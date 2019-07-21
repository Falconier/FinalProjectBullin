package com.example.finalprojectbullin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class OrderPlaced extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);

        TextView output = (TextView) findViewById(R.id.lblOrderOutput);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        double total = 0.0;

        String name = sharedPref.getString("Name", "");
        String coffeeSize = sharedPref.getString("Size", "Small");
        String coffeeType = sharedPref.getString("Type", "Railhead Espresso");
        Boolean cream = sharedPref.getBoolean("Cream", false);
        Boolean sugar = sharedPref.getBoolean("Sugar", false);

        if (coffeeSize.equals("Small")) {
            total += .75;
        } else if (coffeeSize.equals("Medium")) {
            total += 1.00;
        } else if (coffeeSize.equals("Large")) {
            total += 1.25;
        } else if (coffeeSize.equals("Extra Large")) {
            total += 1.5;
        }

        if (cream)
            total += .15;

        if (sugar)
            total += .15;


        //TODO: Finish this ie add label text with total

        DecimalFormat currency = new DecimalFormat("$###,###.##");

        String creamOrSugar = "";
        if(cream && sugar)
        {
            creamOrSugar = "Cream and Sugar ";
        }
        else if(cream)
        {
            creamOrSugar = "Cream ";
        }
        else if(sugar)
        {
            creamOrSugar = "Sugar ";
        }
        else
            creamOrSugar = "";
        output.setText("Thank you " + name + ".\nYour order of a \n" + coffeeSize + " " + coffeeType + "\nwith " + creamOrSugar +"has been ordered.\nYour total is " + currency.format(total));


//        output.setText();

        Button btnDone = (Button) findViewById(R.id.btnDone);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderPlaced.this, MainActivity.class));
            }
        });
    }
}

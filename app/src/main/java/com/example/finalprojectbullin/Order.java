package com.example.finalprojectbullin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class Order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        final EditText txtName = (EditText) findViewById(R.id.txtName);

        final RadioGroup sizes = (RadioGroup) findViewById(R.id.rdbtnSizes);
        final RadioButton small = (RadioButton) findViewById(R.id.rdbtnSmall);
        final RadioButton medium = (RadioButton) findViewById(R.id.rdbtnMedium);
        final RadioButton large = (RadioButton) findViewById(R.id.rdbtnLarge);
        final RadioButton extraLarge = (RadioButton) findViewById(R.id.rdbtnExtraLrg);

        final Spinner coffeeType = (Spinner) findViewById(R.id.spnrType);

        final CheckBox sugar = (CheckBox) findViewById(R.id.chkboxSugar);
        final CheckBox cream = (CheckBox) findViewById(R.id.chkboxCream);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
                final SharedPreferences.Editor edit = sharedPref.edit();


        Button placeOrder = (Button) findViewById(R.id.btnPlaceOrder);

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: add input validation
                String size = "";
                System.out.println(sizes.getCheckedRadioButtonId());
                {
                    if(sizes.getCheckedRadioButtonId() == findViewById(R.id.rdbtnSmall).getId())
                    {
                        size = small.getText().toString();
                    }
                    else if(sizes.getCheckedRadioButtonId() == findViewById(R.id.rdbtnMedium).getId())
                    {
                        size = medium.getText().toString();
                    }
                    else if(sizes.getCheckedRadioButtonId() == findViewById(R.id.rdbtnLarge).getId())
                    {
                        size = large.getText().toString();
                    }
                    else if(sizes.getCheckedRadioButtonId() == findViewById(R.id.rdbtnExtraLrg).getId())
                    {
                        size = extraLarge.getText().toString();
                    }
                }
                edit.putString("Name",txtName.getText().toString());
                edit.putString("Size",size);
                edit.putString("Type",coffeeType.getSelectedItem().toString());
                edit.putBoolean("Cream",(cream.isChecked()) ? true:false);
                edit.putBoolean("Sugar",(sugar.isChecked()) ? true:false);
                edit.commit();
                startActivity(new Intent(Order.this, OrderPlaced.class));
            }
        });
    }
}

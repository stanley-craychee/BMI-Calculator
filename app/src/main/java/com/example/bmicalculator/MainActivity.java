package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //class variables also are called 'fields'
    private TextView resultText;
    private EditText editAge;
    private EditText editInches;
    private EditText editFeet;
    private EditText editWeight;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private Button calculateButton;

    @Override
    //onCreate runs upon app load
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView tells Android what to show on the screen using an XML file
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        resultText.setText("This shows you can update a TextView using code.");

        editAge = findViewById(R.id.edit_text_age);
        editInches = findViewById(R.id.edit_text_inches);
        editFeet = findViewById(R.id.edit_text_feet);
        editWeight = findViewById(R.id.edit_text_weight);
        radioMale = findViewById(R.id.radio_button_male);
        radioFemale = findViewById(R.id.radio_button_female);
        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bmiResult = calculateBMI();
                String ageText = editAge.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }
            }
        });
    }


    private double calculateBMI() {
        String inchesText = editInches.getText().toString();
        String feetText = editFeet.getText().toString();
        String weightText = editWeight.getText().toString();

        //converting the number 'Strings' into 'int' variables
        int feet = Integer.parseInt(feetText);
        int weight = Integer.parseInt(weightText);
        int inches = Integer.parseInt(inchesText);

        int totalInches = (feet * 12) + inches;
        double heightInMeters = totalInches * 0.0254;
        double weightInPounds = weight * 0.4536;

        return weightInPounds / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (bmi<18.5) {
            //display underweight
            fullResultString = bmiTextResult + " - You are underweight.";
        } else if (bmi>25) {
            //display overweight
            fullResultString = bmiTextResult + " - You are overweight.";
        } else {
            //display healthy
            fullResultString = bmiTextResult + " - You are a healthy weight.";
        }

        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);
        String fullResultString;
        if (radioMale.isChecked()) {
            fullResultString = bmiTextResult + " - As you are under 18. Please consult with your doctor for the healthy range for boys.";
        } else if (radioFemale.isChecked()) {
            fullResultString = bmiTextResult + " - As you are under 18. Please consult with your doctor for the healthy range for girls.";
        } else {
            fullResultString = bmiTextResult + " - As you are under 18. Please consult with your doctor for the healthy range.";
        }
        resultText.setText(fullResultString);
    }
}
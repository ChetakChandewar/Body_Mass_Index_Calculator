package com.example.bodymassindexcalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    private MaterialCardView statusCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextWeight = findViewById(R.id.weight);
        EditText editTextHeight = findViewById(R.id.height);
        Button button = findViewById(R.id.btnSubmit);
        TextView textView = findViewById(R.id.result);
        TextView textView1 = findViewById(R.id.status);
        statusCardView = findViewById(R.id.statusCardView);

        button.setOnClickListener(view -> {
            try {
                String heightStr = String.valueOf(editTextHeight.getText()).trim();
                String weightStr = String.valueOf(editTextWeight.getText()).trim();

                if (heightStr.isEmpty() || weightStr.isEmpty()) {
                    showToast("Please enter valid numbers for height and weight.");
                    return;
                }

                float height = Float.parseFloat(heightStr);
                float weight = Float.parseFloat(weightStr);

                if (height == 0 || weight == 0) {
                    showToast("Height & weight cannot be zero");
                    return;
                }

                float bmi = (weight / (height * height)) * 10000;

                textView.setText(String.valueOf(bmi));

                if (bmi < 18.5) {
                    textView1.setText(getString(R.string.under_weight));
                    updateStatusCardColor(Color.YELLOW);
                } else if (bmi >= 18.5 && bmi < 24.9) {
                    textView1.setText(getString(R.string.healthy));
                    updateStatusCardColor(Color.GREEN);
                } else if (bmi >= 24.9 && bmi < 30) {
                    textView1.setText(getString(R.string.overweight));
                    updateStatusCardColor(Color.parseColor("#FFA500")); // Orange color
                } else if (bmi >= 30) {
                    textView1.setText(getString(R.string.obesity));
                    updateStatusCardColor(Color.RED);
                } else {
                    textView1.setText(getString(R.string.valid_numbers));
                }

                textView1.setVisibility(View.VISIBLE);

            } catch (NumberFormatException e) {
                showToast("Invalid input. Please enter valid numbers for height and weight.");
            }
        });
    }

    private void updateStatusCardColor(int color) {
        statusCardView.setCardBackgroundColor(color);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

package com.example.user.week2daily1.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.user.week2daily1.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class EMI_Calculator extends AppCompatActivity {
    private TextView tvAmount;
    private TextView tvInterest;
    private TextView tvTenure;
    private TextView tvMonthlyAmount;
    private int loanVal;
    private float interestVal;
    private int tenureVal;
    private boolean loanMoved = false;
    private boolean intMoved = false;
    private boolean tenureMoved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi__calculator);

        tvAmount = findViewById(R.id.tvAmount);
        tvInterest = findViewById(R.id.tvInterest);
        tvTenure = findViewById(R.id.tvTenure);
        tvMonthlyAmount = findViewById(R.id.tvMonthlyAmount);

        loanVal = 0;
        interestVal = 0;
        tenureVal = 0;

        seekBarFunc();
    }

    public void seekBarFunc()
    {
        SeekBar sbAmount = findViewById(R.id.sbAmount);
        SeekBar sbInterest = findViewById(R.id.sbInterest);
        SeekBar sbTenure = findViewById(R.id.sbTenure);
        sbAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                loanVal = progress;
                tvAmount.setText("Loan Amount: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                loanMoved = true;
                if (intMoved == false)
                    interestVal = 0.1F;
                if (tenureMoved == false)
                    tenureVal = 1;

                calculateEMI(loanVal, interestVal, tenureVal);
            }
        });

        sbInterest.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                interestVal = ((float)progress / (float)10.0);
                tvInterest.setText("Interest: " + interestVal);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                intMoved = true;
                if (loanMoved == false)
                    loanVal = 10000;
                if (tenureMoved == false)
                    tenureVal = 1;

                calculateEMI(loanVal, interestVal, tenureVal);
            }
        });

        sbTenure.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tenureVal = progress;
                tvTenure.setText("Tenure: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tenureMoved = true;
                if (loanMoved == false)
                    loanVal = 10000;
                if (intMoved == false)
                    interestVal = 0.1F;

                calculateEMI(loanVal, interestVal, tenureVal);
            }
        });

    }

    private void calculateEMI(int loan, float interest, int tenure)
    {
        double result = ((interest / 100 / 12) * loan) / (1 - (Math.pow((1 + (interest / 100 / 12)), (-tenure * 12))));
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        DecimalFormat df = new DecimalFormat("#.00");
        tvMonthlyAmount.setText(defaultFormat.format(result));
    }

}


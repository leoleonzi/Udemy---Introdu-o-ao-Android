package com.example.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.festafimdeano.Constant.FimDeAnoConstant;
import com.example.festafimdeano.R;
import com.example.festafimdeano.data.SecurityPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurtityPreferences;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mSecurtityPreferences = new SecurityPreferences(this);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);


        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()), " dias.");
        this.mViewHolder.textDaysLeft.setText(daysLeft);


    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_confirm){
            String presence = this.mSecurtityPreferences.getStoredString(FimDeAnoConstant.PRESENCE_KEY);
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstant.PRESENCE_KEY, presence);
            startActivity(intent);
        }
    }


    private void verifyPresence() {
        String presence = this.mSecurtityPreferences.getStoredString(FimDeAnoConstant.PRESENCE_KEY);
        if ("".equals(presence)){
            this.mViewHolder.buttonConfirm.setText(R.string.presenca_nao_confirmada);
        } else if (presence.equals(FimDeAnoConstant.PRESENCE_YES)){
            this.mViewHolder.buttonConfirm.setText(R.string.presenca_confirmada);
        } else if (presence.equals(FimDeAnoConstant.PRESENCE_NO)) {
            this.mViewHolder.buttonConfirm.setText(R.string.presenca_negada);
        }
    }

    private int getDaysLeft (){
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        Calendar calendarLastDay = Calendar.getInstance();
        int lastDay = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return lastDay - today;
    }

    private static class ViewHolder{
        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;


    }
}

package com.example.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.festafimdeano.Constant.FimDeAnoConstant;
import com.example.festafimdeano.R;
import com.example.festafimdeano.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurtityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        this.mSecurtityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);
        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras!= null){
            String presence =  extras.getString(FimDeAnoConstant.PRESENCE_KEY);
            if (presence != null && presence.equals(FimDeAnoConstant.PRESENCE_YES)){
                this.mViewHolder.checkParticipate.setChecked(true);
            } else {
                this.mViewHolder.checkParticipate.setChecked(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.check_participate){
            if (this.mViewHolder.checkParticipate.isChecked()){
                this.mSecurtityPreferences.StoreString(FimDeAnoConstant.PRESENCE_KEY,FimDeAnoConstant.PRESENCE_YES);
            } else{
                this.mSecurtityPreferences.StoreString(FimDeAnoConstant.PRESENCE_KEY,FimDeAnoConstant.PRESENCE_NO);
            }
        }
    }

    private static class ViewHolder {
        CheckBox checkParticipate;
    }
}

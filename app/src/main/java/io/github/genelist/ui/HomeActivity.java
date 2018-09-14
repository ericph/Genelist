package io.github.genelist.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import io.github.genelist.R;
import io.github.genelist.util.User;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView ye = findViewById(R.id.yeImageView);

        User.getInstance().readSaveData(getApplicationContext());
    }

}

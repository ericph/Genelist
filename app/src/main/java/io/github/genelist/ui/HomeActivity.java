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

        User.getInstance().readSaveData(getApplicationContext());

        ImageView ye = findViewById(R.id.yeImageView);

        createListFragments(savedInstanceState);
    }

    private void createListFragments(Bundle savedInstanceState) {
        if (findViewById(R.id.list_fragment) != null) {
            if (savedInstanceState != null)
                return;
            MusicianListFragment malFragment = new MusicianListFragment();
            malFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.list_fragment, malFragment).commit();
        }
    }

}

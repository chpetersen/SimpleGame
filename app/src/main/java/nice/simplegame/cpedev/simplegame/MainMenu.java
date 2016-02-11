package nice.simplegame.cpedev.simplegame;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainMenu extends BaseActivity {

    @Bind(R.id.start_game_button)
    Button mStartGameButton;
    @Bind(R.id.main_menu_text)
    TextView mTextViewMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        ButterKnife.bind(this);

        Typeface tf = Typeface.createFromAsset(getAssets(), "dreamer.ttf");
        mTextViewMainMenu.setTypeface(tf);

        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Game.class);
                startActivity(intent);

            }
        });
    }
}

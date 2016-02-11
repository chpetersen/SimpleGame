package nice.simplegame.cpedev.simplegame;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Game extends BaseActivity {

    @Bind(R.id.main_game_rl)
    RelativeLayout mMainGameLayout;

    View mPauseButton;
    View mPauseMenu;
    GamePanel mGamePanel;

    View.OnClickListener continue_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPauseMenu.setVisibility(View.GONE);
            mPauseButton.setVisibility(View.VISIBLE);
            mGamePanel.pauseGame = false;
        }
    };

    View.OnClickListener goToMainMenu_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Game.this.finish();
        }
    };

    View.OnClickListener pauseClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPauseButton.setVisibility(View.GONE);
            mPauseMenu.setVisibility(View.VISIBLE);
            mGamePanel.pauseGame = true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        ButterKnife.bind(this);

        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);

        final int height = dm.heightPixels;
        final int width = dm.widthPixels;

        mGamePanel = new GamePanel(getApplicationContext(), this, width);
        mMainGameLayout.addView(mGamePanel);



        LayoutInflater layoutInflater = getLayoutInflater();
        mPauseButton = layoutInflater.inflate(R.layout.pause, null, false);
        mPauseButton.setX(width-250);
        mPauseButton.setY(0);

        mPauseButton.setOnClickListener(pauseClick);

        mMainGameLayout.addView(mPauseButton);

        mPauseButton.getLayoutParams().height += 250;
        mPauseButton.getLayoutParams().width += 250;

        mPauseMenu = layoutInflater.inflate(R.layout.pause_menu, null, false);
        mMainGameLayout.addView(mPauseMenu);
        mPauseMenu.setVisibility(View.GONE);

        Button continueGame = (Button) mPauseMenu.findViewById(R.id.continue_game_button);
        Button goToMainMenu = (Button) mPauseMenu.findViewById(R.id.back_to_main_menu);

        continueGame.setOnClickListener(continue_listener);
        goToMainMenu.setOnClickListener(goToMainMenu_listener);

    }

}

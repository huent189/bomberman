package bomberman;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoBar implements Initializable {
    private long lastTime;
    private long time;
    private int score;
    @FXML
    Label lbTime;
    @FXML
    Label lbScore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetScore();
        resetTime();
    }
    public void update(){
        long newTime = System.currentTimeMillis();
        time = time + lastTime - newTime;
        lastTime = newTime;
        if(time < 0){
            Game.getInstance().endGame();
            lbTime.setText("TIMEOUT");
        }
        lbTime.setText(time / 1000 + "");

    }

    public void addScore(int addScore){
        score += addScore;
        lbScore.setText(score + "");
    }

    public void resetTime(){
        time = 200000;
        lastTime = System.currentTimeMillis();
    }
    public void resetScore(){
        score = 0;
        lbScore.setText(score + "");
    }
}

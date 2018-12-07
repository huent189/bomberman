package bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Game game;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bomberman");
        game = Game.getInstance();
        game.start(primaryStage);
        primaryStage.show();
        new AnimationTimer()
        {
            private long lastUpdate = 0 ;
            public void handle(long currentNanoTime)
            {
                if(currentNanoTime - lastUpdate >= 50000000){
                    game.update();
                    lastUpdate = currentNanoTime;
                }

            }
        }.start();

    }
    public static void main(String[] args){
        launch(args);
    }
}

package bomberman;

import bomberman.Entity.Bomber;
import bomberman.Sound.SoundPlay;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Game {
    public enum GameStatus{
        END_GAME, ON_PLAYING, LOAD_NEW_LEVEL
    }
    private GameObjectManager goManager;
    private LevelLoader levelLoader;
    private GameScene gameScene;
    private Queue<KeyEvent> eventQueue = new LinkedList<>();
    private static Game instance;
    private int width, heigh;
    private InfoBar infoBar;
    private boolean endGame = false;
    private int level;
    private boolean changeLevel = false;
    private int delayFrame = 0;
    private GameStatus status;
    private Game(){

    }
    public void start(Stage primaryStage){
        level = 1;
        String levelSound="LEVEL_"+level+"_SOUND";
        levelLoader = new LevelLoader();
        levelLoader.loadLevelInfo(level);
        GameScene.GAMETILE_SIZE =((int) Screen.getPrimary().getVisualBounds().getWidth()) / width;
        goManager = new GameObjectManager(width, heigh);
        gameScene = new GameScene(goManager, width, heigh);
        levelLoader.loadGameObject(goManager);
        gameScene.setFocusTraversable(true);
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(eventQueue.isEmpty() || eventQueue.peek().getCode() != event.getCode()){
                    eventQueue.add(event);
                }
            }
        });
        VBox root = new VBox();

        infoBar = new InfoBar();
        Node infoNode = null;
        try {
            FXMLLoader infoLoader = new FXMLLoader(getClass().getResource("InfoBar.fxml"));
            infoLoader.setController(infoBar);
            infoNode = infoLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(infoNode);
        root.getChildren().add(gameScene);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        status = GameStatus.ON_PLAYING;
        if(status!=GameStatus.END_GAME)
        {
            SoundPlay.play(levelSound);
        }
    }

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }
    public GameObjectManager getGoManager() {
        return goManager;
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    public void update(){
        switch (status){
            case END_GAME:
                gameScene.drawText(0d, gameScene.getHeight() / 6,"GAMEOVER", "Press Enter to start new game");
                if(!eventQueue.isEmpty()  && eventQueue.poll().getCode() == KeyCode.ENTER){
                    reset();
                    status = GameStatus.ON_PLAYING;
                }
                break;
            case ON_PLAYING:
                gameScene.update();
                infoBar.update();
                break;
            case LOAD_NEW_LEVEL:
                if(delayFrame <= 0){
                    eventQueue = new LinkedList<>();
                    status = GameStatus.ON_PLAYING;
                    infoBar.resetTime();
                } else {
                    gameScene.drawText(0d, 0d,"LEVEL " + level);
                    delayFrame--;
                }
                break;
        }
    }

    public InfoBar getInfoBar() {
        return infoBar;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigh() {
        return heigh;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeigh(int heigh) {
        this.heigh = heigh;
    }

    public Queue<KeyEvent> getEventQueue() {
        return eventQueue;
    }

    public void endGame() {
        status = GameStatus.END_GAME;
    }

    public void loadNextLevel(){
        delayFrame = 50;
        level++;
        levelLoader.loadLevelInfo(level);
        levelLoader.loadGameObject(goManager);
        status = GameStatus.LOAD_NEW_LEVEL;
    }

    public void reset(){
        level = 1;
        levelLoader.loadLevelInfo(level);
        levelLoader.loadGameObject(goManager);
        eventQueue = new LinkedList<>();
        infoBar.resetScore();
        infoBar.resetTime();
    }
}

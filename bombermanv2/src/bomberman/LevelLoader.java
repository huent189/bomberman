package bomberman;

import bomberman.Entity.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelLoader {
    private char map[][];
    private int level;
    private int width;
    private int height;
    public void loadLevelInfo(int level){
        Scanner infoScaner = null;
        try {
            infoScaner = new Scanner(new File(getClass().getResource("/levels/Level" + level + ".txt").getFile()));
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file cấu hình màn chơi");
        }

        level = infoScaner.nextInt();
        height = infoScaner.nextInt();
        width = infoScaner.nextInt();
        infoScaner.nextLine();
        map = new char[height][width];
        for (int i = 0; i < height; i++){
            String line = infoScaner.nextLine();
            for (int j = 0; j < width; j++){
                map[i][j] = line.charAt(j);
            }
        }
        Game.getInstance().setHeigh(height);
        Game.getInstance().setWidth(width);
    }

    public void loadGameObject(GameObjectManager goManager){
        goManager.reset();
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                switch (map[y][x]){
                    case '#':
                        goManager.addObject(new Wall(x, y));
                        break;
                    case '*':
                        goManager.addObject(new Grass(x, y));
                        goManager.addObject(new Brick(x, y));
                        break;
                    case 'x':
                        goManager.addObject(new Grass(x, y));
                        goManager.addObject(new Portal(x, y));
                        goManager.addObject(new Brick(x, y));
                        break;
                    case 'p':
                        goManager.addObject(new Bomber(x * GameScene.GAMETILE_SIZE, y * GameScene.GAMETILE_SIZE));
                        goManager.addObject(new Grass(x, y));
                        break;
                    case '1':
                        goManager.addObject(new Balloon(x * GameScene.GAMETILE_SIZE, y * GameScene.GAMETILE_SIZE));
                        goManager.addObject(new Grass(x, y));
                        break;
                    case '2':
                        goManager.addObject(new Oneal(x * GameScene.GAMETILE_SIZE, y * GameScene.GAMETILE_SIZE));
                        goManager.addObject(new Grass(x, y));
                        break;
                    case 'b':
                        goManager.addObject(new Grass(x, y));
                        goManager.addObject(new BombItem(x, y));
                        goManager.addObject(new Brick(x, y));
                        break;
                    case 'f':
                        goManager.addObject(new Grass(x, y));
                        goManager.addObject(new FlameItem(x, y));
                        goManager.addObject(new Brick(x, y));
                        break;
                    case 's':
                        goManager.addObject(new Grass(x, y));
                        goManager.addObject(new SpeedItem(x, y));
                        goManager.addObject(new Brick(x, y));
                        break;
                    default:
                        goManager.addObject(new Grass(x, y));
                        break;
                }
            }
        }
    }
}

package tiles;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[] [];

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[60];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world_v1.txt");
    }
    public void getTileImage() {

        setup(10, "Grass", false);
        setup(11, "Grass_Bottom", false);
        setup(12, "Grass_Corner_LBottom", false);
        setup(13, "Grass_Corner_LTop", false);
        setup(14, "Grass_Corner_RBottom", false);
        setup(15, "Grass_Corner_RTop", false);
        setup(16, "Grass_Left", false);
        setup(17, "Grass_Right", false);
        setup(18, "Grass_Sand_RBottom_Corner", false);
        setup(19, "Grass_Sand_RTop_Corner", false);
        setup(20, "Grass_Sand_LBottom_Corner", false);
        setup(21, "Grass_Sand_LTop_Corner", false);
        setup(22, "Grass_Top", false);
        setup(23, "Sand", false);
        setup(24, "Sand_Top", false);
        setup(25, "Sand_Bottom", false);
        setup(26, "Sand_Left", false);
        setup(27, "Sand_Right", false);
        setup(28, "Sand_Corner_LBottom", false);
        setup(29, "Sand_Corner_RBottom", false);
        setup(30, "Sand_Corner_RTop", false);
        setup(31, "Sand_Corner_LTop", false);
        setup(32, "Dirt", false);
        setup(33, "Stone", false);
        setup(34, "Tree", true);
        setup(35, "Wall", false);
        setup(36, "Water", true);
        setup(37, "Water_Grass_LBottom_Corner", true);
        setup(38, "Water_Grass_LTop_Corner", true);
        setup(39, "Water_Grass_RBottom_Corner", true);
        setup(40, "Water_Grass_TopR", true);
        setup(41, "Concrete_Horizontal", false);
        setup(42, "Whole_Calcada", false);
        setup(43, "Street_CornerLU", false);
        setup(44, "Street_Horizontal", false);
        setup(45, "Street_Vertical", false);
        setup(46, "Calcada_Top", false);
        setup(47, "Calcada_Bottom", false);
        setup(48, "Concrete_CornerLU", false);
        setup(49, "Concrete_CornerLB", false);
        setup(50, "Concrete_CornerRU", false);
        setup(51, "Concrete_CornerRB", false);
        setup(52, "Concrete_Vertical", false);

    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxWorldCol) {

                    col = 0;
                    row++;
                }
            }

            br.close();

        } catch (Exception e) {

        }
    }


    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);

            }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {

                worldCol = 0;
                worldRow++;
            }
        }
    }
}


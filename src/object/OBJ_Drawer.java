package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class OBJ_Drawer extends SuperObject{

    GamePanel gp;

    public OBJ_Drawer(GamePanel gp) {

        this.gp = gp;

        name = "Drawer";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Drawer.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

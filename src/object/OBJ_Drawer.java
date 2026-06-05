package object;

import javax.imageio.ImageIO;

public class OBJ_Drawer extends SuperObject{

    public OBJ_Drawer() {

        name = "Drawer";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Drawer.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

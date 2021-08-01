package unsw.loopmania;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class VictoryController {
    @FXML 
    private ImageView restartGame;

    @FXML
    private ImageView quit;

     /**
     * facilitates switching to main game
     */
    private MenuSwitcher mainMenuSwitcher;

    public void setMainMenuSwitcher(MenuSwitcher mainMenuSwitcher){
        this.mainMenuSwitcher = mainMenuSwitcher;
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToMainMenu() throws IOException {
        mainMenuSwitcher.switchMenu();
    }
    
}

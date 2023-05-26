
package org.luismonterroso.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.luismonterroso.main.Principal;

public class ProgramadorController implements Initializable{
    private Principal escenarioPrincipal;
    @FXML private ImageView imgProgramador;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void menuPrincipal(){
        escenarioPrincipal.menuPrincipal();
    }
    
    public void cambiarImagen(){
        imgProgramador.setImage(new Image("/org/luismonterroso/image/negativo.jpg"));
    }
}

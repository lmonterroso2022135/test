/*
    Nombre: Luis Mario Monterroso Juarez
    Código Técnico: IN5BV
    Carné: 2022135
    Fecha de creación: 14/04/2023
    Modificación: 14/04/2023 22:30 hrs
    Modificación: 20/04/2023 17:00 hrs
    Modificacion: 27/04/2023 14:00 hrs
    Modificacion: 28/24/2023 13:00 hrs
    Modificacion: 28/24/2023 21:10 hrs
    Modificacion: 28/24/2023 13:11 hrs
*/
package org.luismonterroso.main;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.luismonterroso.controller.EmpresaController;
import org.luismonterroso.controller.MenuPrincipalController;
import org.luismonterroso.controller.PresupuestoController;

import org.luismonterroso.controller.ProductoController;
import org.luismonterroso.controller.ProgramadorController;
import org.luismonterroso.controller.TipoPlatoController;
import org.luismonterroso.controller.TipoEmpleadoController;

public class Principal extends Application {
    private final String PAQUETE_VISTA = "/org/luismonterroso/view/";
    private Stage escenarioPrincipal;
    private Scene escena;
    
    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("Tonny's Kinal 2023");
        escenarioPrincipal.getIcons().add(new Image("/org/luismonterroso/image/icono2.png"));
        
//        Parent root = FXMLLoader.load(getClass().getResource("/org/luismonterroso/view/MunuPrincipalView.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/org/luismonterroso/view/ProgramadorView.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/org/luismonterroso/view/EmpresaView.fxml"));
//
//        Scene escena = new Scene(root);
//        escenarioPrincipal.setScene(escena);
        
        menuPrincipal();
        
        escenarioPrincipal.show();
    }
    
    public void menuPrincipal(){
        try{
            MenuPrincipalController menu = (MenuPrincipalController)cambiarEscena("MenuPrincipalView.fxml",450,450);            
            menu.setEscenarioPrincipal(this);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ventanaProgramador(){
        try{
            ProgramadorController vProgramador = (ProgramadorController)cambiarEscena("ProgramadorView.fxml",590,358);
            vProgramador.setEscenarioPrincipal(this);
                    
        }catch(Exception e){
            e.printStackTrace(); 
        }
    }
    
 // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void ventanaEmpresa(){
        try{
            EmpresaController vEmpresa = (EmpresaController)cambiarEscena("EmpresaView.fxml",800,519);
            vEmpresa.setEscenarioPrincipal(this);
                    
        }catch(Exception e){
            e.printStackTrace();
        
        }
    
    }
    public void ventanaProducto(){
        try{
            ProductoController vProducto = (ProductoController)cambiarEscena("ProductoView.fxml",800,519);
            vProducto.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
    public void ventanaTipoPlato(){
        try{
            TipoPlatoController vTipoPlato = (TipoPlatoController) cambiarEscena("TipoPlatoView.fxml",700,519);
            vTipoPlato.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ventanaTipoEmpleado(){
        try{
            TipoEmpleadoController vTipoEmpleado = (TipoEmpleadoController) cambiarEscena("TipoEmpleadoView.fxml",700,519);
            vTipoEmpleado.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ventanaPresupuesto(){
        try{
            PresupuestoController vPresupuesto = (PresupuestoController) cambiarEscena("PresupuestoView.fxml",800,519);
            vPresupuesto.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        launch(args);
    }
    
    public Initializable cambiarEscena(String fxml, int ancho, int alto) throws Exception{
        Initializable resultado = null;
        FXMLLoader cargadorFXML= new FXMLLoader();
        InputStream archivo= Principal.class.getResourceAsStream(PAQUETE_VISTA+fxml);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA+fxml));
        escena = new Scene((AnchorPane)cargadorFXML.load(archivo),ancho,alto);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();
        resultado = (Initializable)cargadorFXML.getController();
        
        return resultado;
    }
    
}

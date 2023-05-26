
package org.luismonterroso.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.luismonterroso.bean.TipoEmpleado;
import org.luismonterroso.db.Conexion;
import org.luismonterroso.main.Principal;

public class TipoEmpleadoController implements Initializable{
    private Principal escenarioPrincipal;
    private ObservableList<TipoEmpleado> listaTipoEmpleado;
    private enum operaciones{GUARDAR, ELIMINAR, ACTUALIZAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    
    @FXML private TextField txtCodigoTipoEmpleado;
    @FXML private TextField txtDescripcionTipoEmpleado;
    
    @FXML private TableView tblTipoEmpleados;
    @FXML private TableColumn colCodigoTipoEmpleado;
    @FXML private TableColumn colDescripcionTipoEmpleado;
   
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private Button btnReporte;
    @FXML private Button btnEditar;
    
            
    @FXML private ImageView imgNuevo;
    @FXML private ImageView imgReporte;
    @FXML private ImageView imgEliminar;
    @FXML private ImageView imgEditar;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }
    public void cargarDatos(){
        tblTipoEmpleados.setItems(getTipoEmpleado());
        colCodigoTipoEmpleado.setCellValueFactory(new PropertyValueFactory<TipoEmpleado, Integer>("codigoTipoEmpleado"));
        colDescripcionTipoEmpleado.setCellValueFactory(new PropertyValueFactory<TipoEmpleado, String>("descripcionTipoEmpleado"));
    }
    
    public  ObservableList<TipoEmpleado> getTipoEmpleado(){
        ArrayList <TipoEmpleado> lista = new ArrayList <TipoEmpleado>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarTipoEmpleados");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()){
                lista.add(new TipoEmpleado(resultado.getInt("codigoTipoEmpleado"),
                            resultado.getString("descripcion")));
            }
            
        }catch( Exception e){
            e.printStackTrace();
        }
        return listaTipoEmpleado = FXCollections.observableArrayList(lista);
    }
    
    public void nuevo(){
        switch(tipoDeOperacion){
            case NINGUNO:
                activarControles();
                limpiarControles();
                btnNuevo.setText("Guardar");
                btnEliminar.setText("Cancelar");
                btnEditar.setDisable(true);
                btnReporte.setDisable(true);
                imgNuevo.setImage(new Image("/org/luismonterroso/image/aceptar.png"));
                imgEliminar.setImage(new Image("/org/luismonterroso/image/cancelar.png"));
                tipoDeOperacion = operaciones.GUARDAR; 
            break;
            case GUARDAR:
                guardar();
                limpiarControles();
                desactivarControles();
                btnNuevo.setText("  Nuevo");
                btnEliminar.setText("  Eliminar");
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                imgNuevo.setImage(new Image("/org/luismonterroso/image/nuevo.png"));
                imgEliminar.setImage(new Image("/org/luismonterroso/image/delete.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
            break;
        
        
        }
   
    }
    public void eliminar(){
        switch(tipoDeOperacion){
            case GUARDAR:
                limpiarControles();
                desactivarControles();
                btnNuevo.setText("   Nuevo");
                btnEliminar.setText(" Eliminar ");
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                imgNuevo.setImage(new Image("/org/luismonterroso/image/nuevo.png"));
                imgEliminar.setImage(new Image("/org/luismonterroso/image/delete.png"));
                tipoDeOperacion = operaciones.NINGUNO;
            break;
            default:
                if(tblTipoEmpleados.getSelectionModel().getSelectedItem() !=null){
                    int respuesta =JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el registro?? ", "Eliminar TipoPlato",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    
                    if(respuesta ==JOptionPane.YES_OPTION){
                        try{
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EliminarEmpresa(?)");
                            procedimiento.setInt(1, ((TipoEmpleado)tblTipoEmpleados.getSelectionModel().getSelectedItem()).getCodigoTipoEmpleado());
                            procedimiento.execute();
                            listaTipoEmpleado.remove(tblTipoEmpleados.getSelectionModel().getSelectedItem());
                            limpiarControles(); 
                        }catch(Exception e){
                         e.printStackTrace();
                         
                        }
                    }  
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento ");
                }
                seleccionarElemento();  
        }
    
    
    }
    
    
    
    
    
    
    
    
    
    
    public void guardar(){
        TipoEmpleado registro = new TipoEmpleado();
        registro.setDescripcionTipoEmpleado(txtDescripcionTipoEmpleado.getText());
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarTipoEmpleado(?)");
            procedimiento.setString(1, registro.getDescripcionTipoEmpleado());
            procedimiento.execute();
            listaTipoEmpleado.add(registro);
                    
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    
    
    
   // ///////////////////////////////////////////////////////////////////////// 
    public void seleccionarElemento(){
        txtCodigoTipoEmpleado.setText(String.valueOf(((TipoEmpleado)tblTipoEmpleados.getSelectionModel().getSelectedItem()).getCodigoTipoEmpleado()));
        txtDescripcionTipoEmpleado.setText(((TipoEmpleado)tblTipoEmpleados.getSelectionModel().getSelectedItem()).getDescripcionTipoEmpleado());
    }
    
    public void desactivarControles(){
        txtCodigoTipoEmpleado.setEditable(false);
        txtDescripcionTipoEmpleado.setEditable(false);
    }
    public void activarControles(){
        txtCodigoTipoEmpleado.setEditable(false);
        txtDescripcionTipoEmpleado.setEditable(true);
    }
    public void limpiarControles(){
        txtCodigoTipoEmpleado.clear();
        txtDescripcionTipoEmpleado.clear();
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
    
}

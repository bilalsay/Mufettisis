package dev.demo.Controller;

import dev.demo.Entity.AncestorEntity;
import dev.demo.Services.OptionalParamter;
import dev.demo.Services.SpecialEnums.ColorCategory;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CrudController extends Controller {

	@FXML
	public ScrollPane noteScrollPane;
	@FXML
    public Button backButton;
	@FXML
	public TableView<AncestorEntity> listTable;
	@FXML
    public Button tamamButton;
	@FXML
	public Button notTamamButton;
	@FXML
	public Button ansveredButton;
	@FXML
	public Button notAnsveredButton;
	@FXML
	public Button allButton;
	@FXML
	public ComboBox<ColorCategory> searchKategori;
	@FXML
	public TextField searchTF;
	@FXML
    public Button ekleButton;
	@FXML
    public Button guncelleButton;
    @FXML
    public Button silButton;
    @FXML
    public Button listPageSilFromListButton;
    @FXML
    public Button pageFirstButton;
    @FXML
    public Button pageOncekiButton;
    @FXML
    public Button pageSonrakiButton;
    @FXML
    public Button pageLastButton;
    @FXML
    public Button kaydetButton;
    
	public void listPage(AncestorEntity entity, OptionalParamter optionalParameterPasser) throws Exception {
		
	}
	
	public void addPage(AncestorEntity entity) throws Exception {
		
	}
	
	public void doAdd(AncestorEntity entity) {
		
	}
	
	public void showPage(AncestorEntity entity) throws Exception {
		
	}
	
	public void updatePage(AncestorEntity entity) throws Exception {
		
	}
	
	public void doUpdate(AncestorEntity entity, OptionalParamter optionalParameterPasser) {
		
	}
	
	public void doRemove(AncestorEntity entity) {
		
	}
}

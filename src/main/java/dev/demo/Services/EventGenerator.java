package dev.demo.Services;

import java.io.File;
import java.time.LocalDate;
import dev.demo.Controller.CrudController;
import dev.demo.Controller.IsemriController;
import dev.demo.Controller.IslemController;
import dev.demo.Controller.MukellefController;
import dev.demo.Controller.MulakatController;
import dev.demo.Controller.NoteController;
import dev.demo.Entity.*;
import dev.demo.Services.SpecialEnums.ColorCategory;
import dev.demo.Services.SpecialEnums.CrudTypeEnum;
import dev.demo.Services.SpecialEnums.TextEnum;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;

public class EventGenerator {

	public static void generateMukellefShowPageEvents(MukellefController mukellefController, Mukellef mukellef) {
		mukellefController.guncelleButton.setOnMouseClicked(event -> {
			try {
				Follower.stack.push(new StepBackwardContent(mukellef, CrudTypeEnum.SHOW, mukellefController, mukellefController));
				mukellefController.updatePage(mukellef);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		mukellefController.silButton.setOnMouseClicked(event -> {
			try {
				mukellefController.doRemove(mukellef);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		mukellefController.temsilciGuncelleButton.setOnMouseClicked(event -> {
			try {
				Follower.stack.push(new StepBackwardContent(mukellef, CrudTypeEnum.SHOW, mukellefController, mukellefController));
				mukellefController.updatePage(mukellef.getTemsilci());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		mukellefController.smmGuncelleButton.setOnMouseClicked(event -> {
			try {
				Follower.stack.push(new StepBackwardContent(mukellef, CrudTypeEnum.SHOW, mukellefController, mukellefController));
				mukellefController.updatePage(mukellef.getSmm());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		mukellefController.ymmGuncelleButton.setOnMouseClicked(event -> {
			try {
				Follower.stack.push(new StepBackwardContent(mukellef, CrudTypeEnum.SHOW, mukellefController, mukellefController));
				mukellefController.updatePage(mukellef.getYmm());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		mukellefController.ekleButton.setOnMouseClicked(event -> {
			try {
				IsemriController isemriController = (IsemriController) mukellefController.contentLoader
						.getControllerFactory().call(Class.forName("dev.demo.Controller.IsemriController"));
				Follower.stack.push(new StepBackwardContent(mukellef, CrudTypeEnum.SHOW, isemriController, mukellefController));
				isemriController.addPage(null);
				isemriController.vergiKimlikNoCB.setValue(mukellef);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		mukellefController.listIsemriButton.setOnMouseClicked(event -> {
			try {
				IsemriController isemriController = (IsemriController) mukellefController.contentLoader
						.getControllerFactory().call(Class.forName("dev.demo.Controller.IsemriController"));
				isemriController.setPageRequestAsDefault(0, "isemriYayinTarih", "id");
				Follower.stack.push(new StepBackwardContent(mukellef, CrudTypeEnum.SHOW, isemriController, mukellefController));
				isemriController.listPage(mukellef, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Follower.setStepBackward();
	}
	
	public static void generateAddPageEvents(CrudController controller, AncestorEntity entity) {
		controller.ekleButton.setOnMouseClicked(event -> {
			try {
				controller.doAdd(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		Follower.setStepBackward();
	}
	
	public static void generateUpdatePageEvents(CrudController controller, AncestorEntity entity) {
		controller.kaydetButton.setOnMouseClicked(event -> {
			try {
				controller.doUpdate(entity, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		Follower.setStepBackward();
	}

	public static void generateTemsilciUpdatePageEvents(MukellefController mukellefController, Temsilci temsilci) {
		mukellefController.kaydetButton.setOnMouseClicked(event -> {
			try {
				mukellefController.doUpdate(temsilci);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		Follower.setStepBackward();
	}
	
	public static void generateSmmUpdatePageEvents(MukellefController mukellefController, Smm smm) {
		mukellefController.kaydetButton.setOnMouseClicked(event -> {
			try {
				mukellefController.doUpdate(smm);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		Follower.setStepBackward();
	}

	public static void generateYmmUpdatePageEvents(MukellefController mukellefController, Ymm ymm) {
		mukellefController.kaydetButton.setOnMouseClicked(event -> {
			try {
				mukellefController.doUpdate(ymm);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		Follower.setStepBackward();
	}

	public static void generateCheckBoxEvents(MukellefController mukellefController) {

		mukellefController.faaliyetDurumCB.valueProperty().addListener(new ChangeListener<TextEnum>() {
			@Override
			public void changed(ObservableValue<? extends TextEnum> object, TextEnum previous, TextEnum current) {
				if (current.name().equals(TextEnum.TERK.name())) {
					mukellefController.terkTarihInput.setVisible(true);
					mukellefController.terkTarihInput.getChildren().add(0, mukellefController.terkTarihLabel);
					mukellefController.terkTarihDP.setValue(LocalDate.now());
					mukellefController.terkTarihInput.getChildren().add(1, mukellefController.terkTarihDP);
				} else {
					mukellefController.terkTarihInput.setVisible(false);
					mukellefController.terkTarihInput.getChildren().clear();
				}
			}
		});
	}

	public static void generateIsemriShowPageEvents(IsemriController isemriController, Isemri isemri) {
		isemriController.mukellefShowButton.setOnMouseClicked(event -> {
			try {
				MukellefController mukellefController = (MukellefController) isemriController.contentLoader
						.getControllerFactory().call(Class.forName("dev.demo.Controller.MukellefController"));
				mukellefController.setMain(isemriController.main);
				Follower.stack.push(new StepBackwardContent(isemri, CrudTypeEnum.SHOW, mukellefController, isemriController));
				mukellefController.showPage(isemri.getMukellef());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		isemriController.isemriGuncelleButton.setOnMouseClicked(event -> {
			try {
				Follower.stack.push(new StepBackwardContent(isemri, CrudTypeEnum.SHOW, isemriController, isemriController));
				isemriController.updatePage(isemri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		isemriController.silButton.setOnMouseClicked(event -> {
			try {
				isemriController.doRemove(isemri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		isemriController.raporAlButton.setOnMouseClicked(event -> {
			try {
				isemriController.printRapor(isemri);
			} catch (Exception e) {
				
				Notifier.passMessage(Alert.AlertType.ERROR, "Hata!", "Bir sorun oluştu.", "Rapor alınamadı.");
				File previousFile = new File(isemri.getIsemriNo() + "_R.pdf");
				previousFile.delete();
				File followingFile = new File(isemri.getIsemriNo() + "_Rapor.pdf");
				followingFile.delete();
				e.printStackTrace();
			}
		});
		
		isemriController.notlarListButton.setOnMouseClicked(event -> {
			try {
				NoteController noteController = (NoteController) isemriController.contentLoader
						.getControllerFactory().call(Class.forName("dev.demo.Controller.NoteController"));
				noteController.setMain(isemriController.main);
				noteController.setPageRequestAsDefault(0, "yayinTarih", "id");
				Follower.stack.push(new StepBackwardContent(isemri, CrudTypeEnum.SHOW, noteController, isemriController));
				noteController.listPage(isemri, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		isemriController.islemlerListButton.setOnMouseClicked(event -> {
			try {
				IslemController islemController = (IslemController) isemriController.contentLoader
						.getControllerFactory().call(Class.forName("dev.demo.Controller.IslemController"));
				islemController.setMain(isemriController.main);
				islemController.setPageRequestAsDefault(0, "yayinTarih", "id");
				Follower.stack.push(new StepBackwardContent(isemri, CrudTypeEnum.SHOW, islemController, isemriController));
				islemController.listPage(isemri, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		isemriController.mulakatListButton.setOnMouseClicked(event -> {
			try {
				MulakatController mulakatController = (MulakatController) isemriController.contentLoader
						.getControllerFactory().call(Class.forName("dev.demo.Controller.MulakatController"));
				mulakatController.setMain(isemriController.main);
				mulakatController.setPageRequestAsDefault(0, "yayinTarih", "id");
				Follower.stack.push(new StepBackwardContent(isemri, CrudTypeEnum.SHOW, mulakatController, isemriController));
				mulakatController.listPage(isemri, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Follower.setStepBackward();
	}
	
	public static void generateListPageEvents(CrudController controller, String searchText, AncestorEntity entity, String ... pageProperties) {

		controller.listTable.setRowFactory(tv -> {
			TableRow<AncestorEntity> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if ((!row.isEmpty()) && (event.getButton() == MouseButton.PRIMARY) && (event.getClickCount() == 2)) {
					try {
						Follower.stack.push(new StepBackwardContent(entity, CrudTypeEnum.LIST, controller, controller));
						controller.showPage(row.getItem());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return row;
		});

		controller.ekleButton.setOnMouseClicked(event -> {
			try {
				Follower.stack.push(new StepBackwardContent(entity, CrudTypeEnum.LIST, controller, controller));
				controller.addPage(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		controller.listPageSilFromListButton.setOnMouseClicked(event -> {
			try {
				int selectedIndex = controller.listTable.getSelectionModel().getSelectedIndex();
		        if (selectedIndex >= 0) {
		        		controller.doRemove(controller.listTable.getSelectionModel().getSelectedItem());
		        } else {
		            Notifier.passMessage(Alert.AlertType.WARNING, "Uyarı!", "Bir Satır Seçmediniz.", "Silmek için bir satır seçmelisiniz.");
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		controller.searchTF.setOnKeyPressed(new EventHandler<KeyEvent>(){
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            		try {
	            			controller.setPageRequestAsDefault(0, pageProperties);
						controller.listPage(entity, new OptionalParamter(null, null, controller.searchTF.getText()));
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	        }
	    });
		
		controller.allButton.setOnMouseClicked(event -> {
			try {
				controller.setPageRequestAsDefault(0, pageProperties);
				controller.listPage(entity, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});


		controller.pageFirstButton.setOnMouseClicked(event -> {
			if (!controller.page.isFirst()) {
				controller.setPageRequest(controller.getPageRequest().first());
			}
			try {
				controller.listPage(entity, new OptionalParamter(null, null, searchText));
			} catch (Exception e) {
				e.printStackTrace();
			}

		});

		controller.pageOncekiButton.setOnMouseClicked(event -> {
			if (controller.page.hasPrevious()) {
				controller.setPageRequest(controller.page.previousPageable());
			}
			try {
				controller.listPage(entity, new OptionalParamter(null, null, searchText));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		controller.pageSonrakiButton.setOnMouseClicked(event -> {
			if (controller.page.hasNext()) {
				controller.setPageRequest(controller.page.nextPageable());
			}
			try {
				controller.listPage(entity, new OptionalParamter(null, null, searchText));
			} catch (Exception e) {
				e.printStackTrace();
			}

		});

		controller.pageLastButton.setOnMouseClicked(event -> {
			if (!controller.page.isLast()) {
				controller.setPageRequestAsDefault(controller.page.getTotalPages() - 1, pageProperties);
			}
			try {
				controller.listPage(entity, new OptionalParamter(null, null, searchText));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		Follower.setStepBackward();
	}
	
	public static void generateNoteEvents(NoteController controller, AncestorEntity entity, ColorCategory category, String searchText) {
		controller.kaydetButton.setOnMouseClicked(event -> {
			try {
				controller.doAdd(entity);
				if (category != null || searchText != null) {
					controller.listPage(entity, null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		controller.allButton.setOnMouseClicked(event -> {
			try {
				controller.listPage(entity, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		controller.searchKategori.valueProperty().addListener(new ChangeListener<ColorCategory>() {
			@Override
			public void changed(ObservableValue<? extends ColorCategory> object, ColorCategory previous, ColorCategory current) {
				try {
					controller.listPage(entity, new OptionalParamter(null, current, searchText));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		controller.searchTF.setOnKeyPressed(new EventHandler<KeyEvent>(){
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            		try {
						controller.listPage(entity, new OptionalParamter(null, category, controller.searchTF.getText()));
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	        }
	    });
		
		Follower.setStepBackward();
	}
	
	public static void generateIslemEvents(IslemController controller, AncestorEntity entity, ColorCategory category, String searchText) {
		controller.kaydetButton.setOnMouseClicked(event -> {
			try {
				controller.doAdd(entity);
				if (category != null || searchText != null) {
					controller.listPage(entity, null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		controller.allButton.setOnMouseClicked(event -> {
			try {
				controller.listPage(entity, new OptionalParamter(0, null, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		controller.tamamButton.setOnMouseClicked(event -> {
			try {
				controller.listPage(entity, new OptionalParamter(1, null, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		controller.searchKategori.valueProperty().addListener(new ChangeListener<ColorCategory>() {
			@Override
			public void changed(ObservableValue<? extends ColorCategory> object, ColorCategory previous, ColorCategory current) {
				try {
					controller.listPage(entity, new OptionalParamter(0, current, searchText));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		controller.searchTF.setOnKeyPressed(new EventHandler<KeyEvent>(){
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            		try {
						controller.listPage(entity, new OptionalParamter(0, category, controller.searchTF.getText()));
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	        }
	    });
		
		Follower.setStepBackward();
	}
	
	public static void generateIslemEventsTamamPage(IslemController controller, AncestorEntity entity, ColorCategory category, String searchText) {
		
		controller.allButton.setOnMouseClicked(event -> {
			try {
				controller.listPage(entity, new OptionalParamter(1, null, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		controller.notTamamButton.setOnMouseClicked(event -> {
			try {
				controller.listPage(entity, new OptionalParamter(0, null, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		controller.searchKategori.valueProperty().addListener(new ChangeListener<ColorCategory>() {
			@Override
			public void changed(ObservableValue<? extends ColorCategory> object, ColorCategory previous, ColorCategory current) {
				try {
					controller.listPage(entity, new OptionalParamter(1, current, searchText));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		controller.searchTF.setOnKeyPressed(new EventHandler<KeyEvent>(){
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            		try {
						controller.listPage(entity, new OptionalParamter(1, category, controller.searchTF.getText()));
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	        }
	    });
		
		Follower.setStepBackward();
	}
	
	public static void generateMulakatEvents(MulakatController controller, AncestorEntity entity, int isItOk, ColorCategory category, String searchText) {
		controller.kaydetButton.setOnMouseClicked(event -> {
			try {
				controller.doAdd(entity);
				if (category != null || searchText != null) {
					controller.listPage(entity, null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		controller.allButton.setOnMouseClicked(event -> {
			try {
				controller.listPage(entity, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		controller.ansveredButton.setOnMouseClicked(event -> {
			try {
				controller.listPage(entity, new OptionalParamter(1, null, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		controller.notAnsveredButton.setOnMouseClicked(event -> {
			try {
				controller.listPage(entity, new OptionalParamter(0, null, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		controller.searchKategori.valueProperty().addListener(new ChangeListener<ColorCategory>() {
			@Override
			public void changed(ObservableValue<? extends ColorCategory> object, ColorCategory previous, ColorCategory current) {
				try {
					controller.listPage(entity, new OptionalParamter(isItOk, current, searchText));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		controller.searchTF.setOnKeyPressed(new EventHandler<KeyEvent>(){
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            		try {
						controller.listPage(entity, new OptionalParamter(isItOk, category, controller.searchTF.getText()));
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	        }
	    });
		
		Follower.setStepBackward();
	}
	
	public static void generateEventsTopBoxOfContainerBox(CrudController controller, AncestorEntity entity, ContainerBox containerBox) {
		TopBox topBox = containerBox.childOfTopBox; 
		topBox.editButton.setOnMouseClicked(event -> {
			   try {
				   	   topBox.uploadButton.setVisible(true);
				   	   topBox.editButton.setDisable(true);
					   TextArea editText = new TextArea(containerBox.textData.getText());
					   editText.setWrapText(true);
					   editText.setMinHeight(150);
					   editText.setMinWidth(590);
					   containerBox.textBox.getChildren().clear();
					   containerBox.textBox.getChildren().add(editText);
				       controller.noteScrollPane.vvalueProperty().unbind();
			   } catch (Exception e) {
				   e.printStackTrace();
			   }
		   });
		
			topBox.uploadButton.setOnMouseClicked(event -> {
			try {
				   topBox.editButton.setDisable(false);
				   topBox.uploadButton.setVisible(false);
				   containerBox.textData.setText(((TextArea)containerBox.textBox.getChildren().get(0)).getText());
				   controller.doUpdate(entity, new OptionalParamter(containerBox.textData.getText(), null, null));
				   containerBox.textBox.getChildren().clear();
				   containerBox.textBox.getChildren().add(containerBox.textData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		   
	}

}



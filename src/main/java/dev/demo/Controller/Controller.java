package dev.demo.Controller;

import dev.demo.Entity.AncestorEntity;
import dev.demo.Main;
import dev.demo.Services.PostingChecker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
/**
 * Created by bilalsay on 24/09/2017.
 */
public class Controller {

    protected static final int PAGESIZE = 14;

    protected Pageable pageRequest;

    public Page<? extends AncestorEntity> page;

    public Main main;

    public FXMLLoader contentLoader;

    public PostingChecker postingChecker = new PostingChecker();

    @FXML
    public Label title;
    
    protected void setLoader() {
        contentLoader = new FXMLLoader();
        contentLoader.setControllerFactory(main.springContext::getBean);
    }
    
    public Pageable getPageRequest() {
        return this.pageRequest;
    }

    public void setPageRequest(Pageable pageRequest) {
        this.pageRequest = pageRequest;
    }

    public void setPageRequestAsDefault(int defaultPage, String ... properties) {
        pageRequest = new PageRequest(defaultPage, PAGESIZE, Sort.Direction.DESC, properties);
    }

    
    public void setMain(Main main) {
        this.main = main;
    }
    
}

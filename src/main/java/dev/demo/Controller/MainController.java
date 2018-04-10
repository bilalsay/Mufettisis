package dev.demo.Controller;

import org.springframework.stereotype.Component;
import dev.demo.Services.StepBackwardContent;
import dev.demo.Services.Follower;
import dev.demo.Services.SpecialEnums.CrudTypeEnum;

@Component
public class MainController extends Controller {

    public void isEmriMainPage() throws Exception {
        setLoader();
        IsemriController isemriController = (IsemriController) contentLoader.getControllerFactory().call(Class.forName("dev.demo.Controller.IsemriController"));
        isemriController.setMain(main);
        isemriController.setPageRequestAsDefault(0, "isemriYayinTarih", "id");
        Follower.stack.clear();
        Follower.stack.push(new StepBackwardContent(null, CrudTypeEnum.LIST, isemriController, isemriController));
        isemriController.listPage(null, null);
    }

    public void mukellefMainPage() throws Exception {
        MukellefController mukellefController = (MukellefController) contentLoader.getControllerFactory().call(Class.forName("dev.demo.Controller.MukellefController"));
        mukellefController.setMain(main);
        mukellefController.setPageRequestAsDefault(0, "kayitTarih", "mukellefId");
        Follower.stack.clear();
        Follower.stack.push(new StepBackwardContent(null, CrudTypeEnum.LIST, mukellefController, mukellefController));
        mukellefController.listPage(null, null);
    }
}

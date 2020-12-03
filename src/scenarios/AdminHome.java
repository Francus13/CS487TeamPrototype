package scenarios;

import graphics.*;

import static graphics.Window.*;
import static handlers.MouseButton.leftClick;
import static handlers.States.PRESSED;
import static runner.Roomie.*;

public class AdminHome implements Scenario{
    private static RenderableTexture manageProfiles;
    private static RenderableTexture editCode;

    public AdminHome(){
        renderS();
    }

    public static void initRenderables(){
        manageProfiles = new RenderableTexture("Manage Profiles");
        manageProfiles.setCoords(20, 110);

        editCode = new RenderableTexture("Edit Code");
        editCode.setCoords(40 + manageProfiles.width(), 110);
    }

    private void renderS(){
        render(manageProfiles);
        render(editCode);
    }

    private void deRenderS(){
        deRender(manageProfiles);
        deRender(editCode);
    }

    public void update(){
        if (leftClick() == PRESSED){
            if (manageProfiles.isMouseOn()){
                deRenderS();
                setScenario(new AdminSearchingProfiles());
            }
            else if (editCode.isMouseOn()){
                deRenderS();
                setScenario(new EditCode());
            }
        }
    }
}

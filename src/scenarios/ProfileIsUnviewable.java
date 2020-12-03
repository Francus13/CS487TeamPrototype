package scenarios;

import graphics.*;

import static graphics.Color.RED;
import static graphics.Text.LEFT;
import static graphics.Window.*;
import static handlers.MouseButton.leftClick;
import static handlers.States.PRESSED;
import static runner.Roomie.*;

public class ProfileIsUnviewable implements Scenario{
    private static RenderableTexture goBack;
    private static Text unviewableText;

    private final int profile;

    public ProfileIsUnviewable(int profile){
        this.profile = profile;
        renderS();
    }

    public static void initRenderables(){
        goBack = new RenderableTexture("Go Back");
        goBack.setCoords(1536 - goBack.width(), 15);

        initText();
    }

    private static void initText(){
        unviewableText = new Text("Cannot Search for Roommates because Profile is Unviewable", lilFont, LEFT, RED);
        unviewableText.setCoords(20, 110);
    }

    private void renderS(){
        render(goBack);
        render(unviewableText);
    }

    private void deRenderS(){
        deRender(goBack);
        deRender(unviewableText);
    }

    public void update(){
        if (leftClick() == PRESSED){
            if (goBack.isMouseOn()){
                deRenderS();
                setScenario(new StudentHome(profile));
            }
        }
    }
}

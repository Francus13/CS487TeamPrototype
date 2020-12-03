package scenarios;

import graphics.*;

import java.util.ArrayList;

import static database.Database.searchForProfiles;
import static graphics.Color.RED;
import static graphics.Text.LEFT;
import static graphics.Window.*;
import static handlers.MouseButton.leftClick;
import static handlers.States.PRESSED;
import static runner.Roomie.*;

public class SearchingProfiles implements Scenario{
    private static RenderableTexture goBack;
    private static Text[] profileText;

    private final int profile;
    private final ArrayList<String> profilesToShow;

    public SearchingProfiles(int profile){
        this.profile = profile;
        profilesToShow = searchForProfiles(profile);
        renderS();
    }

    public static void initRenderables(){
        goBack = new RenderableTexture("Go Back");
        goBack.setCoords(1536 - goBack.width(), 15);
    }

    private void renderS(){
        render(goBack);

        profileText = new Text[profilesToShow.size()];
        for (int i = 0; i < profileText.length; i++){
            profileText[i] = new Text(profilesToShow.get(i), lilFont, LEFT, RED);

            if (i == 0)
                profileText[i].setCoords(20, 110);
            else
                profileText[i].setCoords(20, profileText[i-1].y() + profileText[i-1].height() + 20);

            render(profileText[i]);
        }
    }

    private void deRenderS(){
        deRender(goBack);
        for (Text text: profileText){
            deRender(text);
        }
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

package scenarios;

import graphics.*;

import static database.Database.isProfileViewable;
import static graphics.Window.*;
import static handlers.MouseButton.leftClick;
import static handlers.States.PRESSED;
import static runner.Roomie.*;

public class StudentHome implements Scenario{
    private static RenderableTexture editProfile;
    private static RenderableTexture searchForRoommates;

    private final int profile;

    public StudentHome(int profile){
        this.profile = profile;
        renderS();
    }

    public static void initRenderables(){
        editProfile = new RenderableTexture("Edit Profile");
        editProfile.setCoords(20, 110);

        searchForRoommates = new RenderableTexture("Search For Roommates");
        searchForRoommates.setCoords(40 + editProfile.width(), 110);
    }

    private void renderS(){
        render(editProfile);
        render(searchForRoommates);
    }

    private void deRenderS(){
        deRender(editProfile);
        deRender(searchForRoommates);
    }

    public void update(){
        if (leftClick() == PRESSED){
            if (editProfile.isMouseOn()){
                deRenderS();
                setScenario(new EditProfile(profile));
            }
            else if (searchForRoommates.isMouseOn()){
                deRenderS();

                if (isProfileViewable(profile))
                    setScenario(new SearchingProfiles(profile));
                else
                    setScenario(new ProfileIsUnviewable(profile));
            }
        }
    }
}

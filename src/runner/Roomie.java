package runner;

import static database.Database.initDatabase;
import static graphics.Font.*;
import static graphics.Window.deRender;
import static graphics.Window.render;
import static handlers.Keys.setKeysRepeated;
import static handlers.MouseButton.setLeftRepeated;
import static handlers.States.*;
import static handlers.MouseButton.leftClick;

import graphics.Font;
import graphics.RenderableTexture;
import scenarios.*;

public class Roomie {
    private static Scenario scenario;

    public static Font lilFont = TREBUCHET_MS_48;
    public static Font medFont = TREBUCHET_MS_60;

    public static RenderableTexture studentBackground;
    public static RenderableTexture adminBackground;


    public static void initRoomie(){
        initDatabase();
        initRenderables();

        render(studentBackground);
        scenario = new ChoosePrivilege();
    }

    public static void update() {
        scenario.update();

        if (leftClick() == PRESSED)
            setLeftRepeated();
        setKeysRepeated();
    }

    private static void initRenderables(){
        studentBackground = new RenderableTexture("Student Background");
        studentBackground.setCoords(0, 0);

        adminBackground = new RenderableTexture("Admin Background");
        adminBackground.setCoords(0, 0);

        ChoosePrivilege.initRenderables();

        StudentHome.initRenderables();
        EditProfile.initRenderables();
        ProfileIsUnviewable.initRenderables();
        SearchingProfiles.initRenderables();

        AdminHome.initRenderables();
        AdminSearchingProfiles.initRenderables();
        AdminViewProfile.initRenderables();
        EditCode.initRenderables();
    }

    public static void setScenario(Scenario s){
        scenario = s;
    }

    public static void setAdmin(){
        deRender(studentBackground);
        render(adminBackground);
    }
}

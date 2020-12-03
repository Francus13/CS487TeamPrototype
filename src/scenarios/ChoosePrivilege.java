package scenarios;

import graphics.*;

import static database.Database.signIntoProfile;
import static graphics.Renderable.CENTERED;
import static graphics.Text.CENTER;
import static graphics.Text.LEFT;
import static graphics.Window.*;
import static graphics.Color.RED;
import static handlers.MouseButton.leftClick;
import static handlers.States.PRESSED;
import static runner.Roomie.*;

public class ChoosePrivilege implements Scenario{
    private static Text chooseText;
    private static Text studentText;
    private static Text adminText;

    public ChoosePrivilege(){
        renderS();
    }

    public static void initRenderables(){
        initText();
    }

    private static void initText(){
        chooseText = new Text("Click On the Desired Prototype User", medFont, LEFT, RED);
        chooseText.setCoords(CENTERED, 110);

        studentText = new Text("Student", lilFont, LEFT, RED);
        studentText.setCoords(CENTERED, chooseText.y() + chooseText.height() + 20);

        adminText = new Text("Administrator", lilFont, CENTER, RED);
        adminText.setCoords(CENTERED, studentText.y() + studentText.height() + 20);
    }

    private void renderS(){
        render(chooseText);
        render(studentText);
        render(adminText);
    }

    private void deRenderS(){
        deRender(chooseText);
        deRender(studentText);
        deRender(adminText);
    }

    public void update(){
        if (leftClick() == PRESSED){
            if (studentText.isMouseOn()){
                deRenderS();
                setScenario(new StudentHome(signIntoProfile()));
            }
            else if (adminText.isMouseOn()){
                deRenderS();
                setAdmin();
                setScenario(new AdminHome());
            }
        }
    }
}

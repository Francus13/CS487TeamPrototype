package scenarios;

import graphics.*;

import static database.Database.*;
import static graphics.Color.RED;
import static graphics.Text.LEFT;
import static graphics.Window.*;
import static handlers.MouseButton.leftClick;
import static handlers.States.PRESSED;
import static runner.Roomie.*;

public class EditProfile implements Scenario{
    private static RenderableTexture saveChanges;

    private static Texture checkedBox;
    private static Texture uncheckedBox;
    private RenderableTexture[] checkBoxes;

    private static Text nameText;
    private static Text viewableText;
    private static Text roomText;
    private static Text onText;
    private static Text offText;
    private static Text cleanText;
    private static Text personalText;
    private static Text sportsText;
    private static Text musicText;
    private static Text gamesText;

    private final int profile;
    private final boolean[] classifiers;

    public EditProfile(int profile){
        this.profile = profile;
        classifiers = getProfileClassifiers(profile);
        renderS();
    }

    public static void initRenderables(){
        checkedBox = new Texture("res/Images/Checked Box.png");
        uncheckedBox = new Texture("res/Images/Unchecked Box.png");

        saveChanges = new RenderableTexture("Save Changes");
        saveChanges.setCoords(1536 - saveChanges.width(), 15);

        initText();
    }

    private static void initText(){
        roomText = new Text("Room", medFont, LEFT, RED);
        roomText.setCoords(20 , 110);

        onText = new Text("On Campus", lilFont, LEFT, RED);
        onText.setCoords(20 , roomText.y() + roomText.height() + 20);

        viewableText = new Text("Make Viewable", lilFont, LEFT, RED);
        viewableText.setCoords(1511 - checkedBox.width() - viewableText.width(), onText.y());

        offText = new Text("Off Campus", lilFont, LEFT, RED);
        offText.setCoords(20 , onText.y() + onText.height() + 20);

        cleanText = new Text("Clean", lilFont, LEFT, RED);
        cleanText.setCoords(20 , offText.y() + offText.height() + 20);

        personalText = new Text("Personal", medFont, LEFT, RED);
        personalText.setCoords(20 , cleanText.y() + cleanText.height() + 20);

        sportsText = new Text("Room", lilFont, LEFT, RED);
        sportsText.setCoords(20 , personalText.y() + personalText.height() + 20);

        musicText = new Text("Room", lilFont, LEFT, RED);
        musicText.setCoords(20 , sportsText.y() + sportsText.height() + 20);

        gamesText = new Text("Room", lilFont, LEFT, RED);
        gamesText.setCoords(20 , musicText.y() + musicText.height() + 20);
    }

    private void renderS(){
        checkBoxes = new RenderableTexture[classifiers.length];
        for (int i = 0; i < checkBoxes.length; i++){
            if (classifiers[i]){
                checkBoxes[i] = new RenderableTexture(checkedBox);
            }
            else {
                checkBoxes[i] = new RenderableTexture(uncheckedBox);
            }

            setCheckBoxCoords(i);
            render(checkBoxes[i]);
        }

        nameText = new Text(getProfileName(profile), medFont, LEFT, RED);
        nameText.setCoords(1531 - nameText.width(), roomText.y());

        render(nameText);
        render(saveChanges);
        render(viewableText);
        render(roomText);
        render(onText);
        render(offText);
        render(cleanText);
        render(personalText);
        render(sportsText);
        render(musicText);
        render(gamesText);
    }

    private void deRenderS(){
        deRender(saveChanges);
        deRender(nameText);
        deRender(viewableText);
        deRender(roomText);
        deRender(onText);
        deRender(offText);
        deRender(cleanText);
        deRender(personalText);
        deRender(sportsText);
        deRender(musicText);
        deRender(gamesText);

        for (RenderableTexture checkBox: checkBoxes){
            deRender(checkBox);
        }
    }

    private void setCheckBoxCoords(int index){
        RenderableTexture checkBox = checkBoxes[index];

        switch (index){
            case 0:
                checkBox.setCoords(1531 - checkBox.width(), viewableText.y());
                break;
            case 1:
                checkBox.setCoords(40 + onText.width(), onText.y());
                break;
            case 2:
                checkBox.setCoords(40 + offText.width(), offText.y());
                break;
            case 3:
                checkBox.setCoords(40 + cleanText.width(), cleanText.y());
                break;
            case 4:
                checkBox.setCoords(40 + sportsText.width(), sportsText.y());
                break;
            case 5:
                checkBox.setCoords(40 + musicText.width(), musicText.y());
                break;
            case 6:
                checkBox.setCoords(40 + gamesText.width(), gamesText.y());
                break;
        }
    }

    public void update(){
        if (leftClick() == PRESSED){
            if (saveChanges.isMouseOn()){
                deRenderS();
                setProfileClassifiers(profile, classifiers);
                setScenario(new StudentHome(profile));
            }
            else {
                for (int i = 0; i < checkBoxes.length; i++){
                    if (checkBoxes[i].isMouseOn()){
                        deRender(checkBoxes[i]);

                        if (classifiers[i]){
                            classifiers[i] = false;
                            checkBoxes[i] = new RenderableTexture(uncheckedBox);
                        }
                        else {
                            classifiers[i] = true;
                            checkBoxes[i] = new RenderableTexture(checkedBox);
                        }
                        setCheckBoxCoords(i);

                        render(checkBoxes[i]);
                        break;
                    }
                }
            }
        }
    }
}

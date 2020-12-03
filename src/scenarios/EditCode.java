package scenarios;

import graphics.*;

import java.util.ArrayList;

import static database.Database.*;
import static graphics.Color.RED;
import static graphics.Text.LEFT;
import static graphics.Window.*;
import static handlers.MouseButton.leftClick;
import static handlers.States.PRESSED;
import static runner.Roomie.*;

public class EditCode implements Scenario{
    private static RenderableTexture saveChanges;
    private static RenderableTexture fileSelector;
    private static Texture fileSelectionTexture;
    private static RenderableTexture[] fileSelections;
    private static RenderableTexture fileDataBox;

    private static Text currentFileText;
    private static Text[] fileNameListText;
    private static Text fileDataText;

    private boolean state;
    private static final boolean NOT_SELECTING_FILE = false;
    private static final boolean SELECTING_FILE = true;

    private int currentFileIndex;
    private String fileData;
    private final String[] fileNames;
    private final ArrayList<Integer> changedFileIndices;
    private final ArrayList<String> changedData;

    public EditCode(){
        fileNames = getFileNames();
        changedFileIndices = new ArrayList<>();
        changedData = new ArrayList<>();

        renderAll();

        fileSelections = new RenderableTexture[fileNames.length];
        for (int i = 0; i < fileSelections.length; i++){
            RenderableTexture fileSelection = new RenderableTexture(fileSelectionTexture);
            if (i == 0)
                fileSelection.setCoords(fileSelector.x(), fileSelector.y() + fileSelector.height());
            else
                fileSelection.setCoords(fileSelector.x(), fileSelections[i - 1].y() + fileSelection.height());
            fileSelections[i] = fileSelection;
        }

        fileNameListText = new Text[fileNames.length];
        for (int i = 0; i < fileNameListText.length; i++){
            Text fileText = new Text(fileNames[i], lilFont, LEFT, RED);
            if (i == 0)
                fileText.setCoords(currentFileText.x(), currentFileText.y() + fileSelectionTexture.height());
            else
                fileText.setCoords(currentFileText.x(), fileNameListText[i-1].y() + fileSelectionTexture.height());
            fileNameListText[i] = fileText;
        }

        currentFileIndex = -1;
        state = false;
    }

    public static void initRenderables(){
        saveChanges = new RenderableTexture("Save Changes");
        saveChanges.setCoords(1536 - saveChanges.width(), 15);

        fileSelector = new RenderableTexture("File Selector");
        fileSelector.setCoords(20, 110);

        fileSelectionTexture = new Texture("res/Images/File Selection.png");

        fileDataBox = new RenderableTexture("File Data Box");
        fileDataBox.setCoords(20, fileSelector.y() + fileSelector.height() + 20);

        initText();
    }

    private static void initText(){
        currentFileText = new Text("", lilFont, LEFT, RED);
        currentFileText.setCoords(fileSelector.x() + 20,
                fileSelector.y() + (fileSelector.height() - currentFileText.height())/2);

        fileDataText = new Text("", lilFont, LEFT, RED, fileDataBox.width(), 700);
        fileDataText.setCoords(fileDataBox.x() + 20,
                fileDataBox.y() + currentFileText.y() - fileSelector.y());
    }

    private void renderAll(){
        render(saveChanges);
        render(fileSelector);
        render(fileDataBox);
        render(currentFileText);
        render(fileDataText);
    }

    private void deRenderAll(){
        deRender(saveChanges);
        deRender(fileSelector);
        deRender(fileDataBox);
        deRender(currentFileText);
        deRender(fileDataText);
    }

    private void renderSelections(){
        for (RenderableTexture fileSelection: fileSelections)
            render(fileSelection);
        for (Text fileNameText: fileNameListText)
            render(fileNameText);
    }

    private void deRenderSelections(){
        for (RenderableTexture fileSelection: fileSelections)
            deRender(fileSelection);
        for (Text fileNameText: fileNameListText)
            deRender(fileNameText);
    }

    public void update(){
        if (leftClick() == PRESSED){
            if (state == SELECTING_FILE){
                for (int i = 0; i < fileSelections.length; i++){
                    if (fileSelections[i].isMouseOn()){
                        saveTempFileChanges();
                        setCurrentFile(i);
                        break;
                    }
                }

                deRenderSelections();
                state = NOT_SELECTING_FILE;
            }

            else if (saveChanges.isMouseOn()){
                saveTempFileChanges();
                setFileDatum(changedFileIndices, changedData);
                deRenderAll();
                setScenario(new AdminHome());
            }

            else if (fileSelector.isMouseOn()){
                renderSelections();
                state = SELECTING_FILE;
            }
        }
    }

    private void saveTempFileChanges(){
        if (currentFileIndex != -1){
            if (changedFileIndices.contains(currentFileIndex))
                changedData.set(changedFileIndices.indexOf(currentFileIndex), fileData);
            else {
                changedFileIndices.add(currentFileIndex);
                changedData.add(fileData);
            }
        }
    }

    private void setCurrentFile(int fileIndex){
        currentFileIndex = fileIndex;
        fileData = getFileData(fileIndex);
        currentFileText.updateText(fileNames[fileIndex]);
        fileDataText.updateText(fileData);
    }
}

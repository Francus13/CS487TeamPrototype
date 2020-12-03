package runner;

import static graphics.Font.initFonts;
import static graphics.Window.initWindow;
import static graphics.Window.renderScreen;
import static runner.Roomie.initRoomie;
import static runner.Timer.getTime;
import static org.lwjgl.glfw.GLFW.*;

import graphics.Window;
import handlers.ErrorHandler;
import handlers.States;

public class Driver {
    public static final double FPSInverse = 1.0/60;
    private static boolean running = false;

    private void init() {
        glfwSetErrorCallback(new ErrorHandler());
        running = true;
        States.init();
        initWindow(1551, 738);
        initFonts();
        initRoomie();
    }

    private void run(){
        init();
        double unprocessed = 0;
        double time = getTime();

        while (running){
            double time2 = getTime();
            unprocessed += time2 - time;
            time = time2;

            if (unprocessed >= FPSInverse) {

                while (unprocessed >= FPSInverse) {
                    unprocessed -= FPSInverse;
                    Roomie.update();

                    glfwPollEvents();
                }

                if (renderScreen())
                    stopRunning();
            }
        }

        glfwTerminate();
    }

    public static void stopRunning() {running = false;}

    public static void main(String[] args) {
        new Driver().run();
    }
}

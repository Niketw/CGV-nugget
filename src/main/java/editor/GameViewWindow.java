package editor;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiWindowFlags;
import jade.MouseListener;
import jade.Window;
import observers.EventSystem;
import observers.events.Event;
import observers.events.EventType;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.glfwGetWindowContentScale;

public class GameViewWindow {

    private boolean isPlaying = false;
    private boolean windowIsHovered;
    private float lastScale = 1.0f;
    private float[] xScale = new float[1];
    private float[] yScale = new float[1];

    public void imgui() {
        ImGui.begin("Game Viewport", ImGuiWindowFlags.NoScrollbar | ImGuiWindowFlags.NoScrollWithMouse
                        | ImGuiWindowFlags.MenuBar);

        ImGui.beginMenuBar();
        if (ImGui.menuItem("Play", "", isPlaying, !isPlaying)) {
            isPlaying = true;
            EventSystem.notify(null, new Event(EventType.GameEngineStartPlay));
        }
        if (ImGui.menuItem("Stop", "", !isPlaying, isPlaying)) {
            isPlaying = false;
            EventSystem.notify(null, new Event(EventType.GameEngineStopPlay));
        }
        ImGui.endMenuBar();

        // Get current content scale
        glfwGetWindowContentScale(Window.getWindow(), xScale, yScale);
        float currentScale = Math.max(xScale[0], yScale[0]);
        
        // Special handling for 1920x1200 resolution
        if (Window.getWidth() == 1920 && Window.getHeight() == 1200) {
            // Adjust scale for the 16:10 aspect ratio
            float aspectRatioFactor = (16.0f / 10.0f) / (16.0f / 9.0f);
            currentScale *= aspectRatioFactor;
        }
        
        // Only update when scale changes
        if (currentScale != lastScale) {
            lastScale = currentScale;
        }

        ImGui.setCursorPos(ImGui.getCursorPosX(), ImGui.getCursorPosY());
        ImVec2 windowSize = getLargestSizeForViewport();
        ImVec2 windowPos = getCenteredPositionForViewport(windowSize);
        ImGui.setCursorPos(windowPos.x, windowPos.y);

        int textureId = Window.getFramebuffer().getTextureId();
        ImGui.imageButton(textureId, windowSize.x, windowSize.y, 0, 1, 1, 0);
        windowIsHovered = ImGui.isItemHovered();

        // Scale-aware mouse position handling
        Vector2f scaledPos = new Vector2f(
            windowPos.x * lastScale, 
            windowPos.y * lastScale
        );
        Vector2f scaledSize = new Vector2f(
            windowSize.x * lastScale, 
            windowSize.y * lastScale
        );
        
        MouseListener.setGameViewportPos(new Vector2f(windowPos.x, windowPos.y));
        MouseListener.setGameViewportSize(new Vector2f(windowSize.x, windowSize.y));

        ImGui.end();
    }

    public boolean getWantCaptureMouse() {
        return windowIsHovered;
    }

    private ImVec2 getLargestSizeForViewport() {
        ImVec2 windowSize = new ImVec2();
        ImGui.getContentRegionAvail(windowSize);

        float aspectWidth = windowSize.x;
        float aspectHeight = aspectWidth / Window.getTargetAspectRatio();
        if (aspectHeight > windowSize.y) {
            // We must switch to pillarbox mode
            aspectHeight = windowSize.y;
            aspectWidth = aspectHeight * Window.getTargetAspectRatio();
        }

        return new ImVec2(aspectWidth, aspectHeight);
    }

    private ImVec2 getCenteredPositionForViewport(ImVec2 aspectSize) {
        ImVec2 windowSize = new ImVec2();
        ImGui.getContentRegionAvail(windowSize);

        float viewportX = (windowSize.x / 2.0f) - (aspectSize.x / 2.0f);
        float viewportY = (windowSize.y / 2.0f) - (aspectSize.y / 2.0f);

        return new ImVec2(viewportX + ImGui.getCursorPosX(), viewportY + ImGui.getCursorPosY());
    }
    
    public float getCurrentScale() {
        return lastScale;
    }
}

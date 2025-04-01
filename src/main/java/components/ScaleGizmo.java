package components;

import editor.PropertiesWindow;
import jade.MouseListener;
import org.joml.Vector2f;

public class ScaleGizmo extends Gizmo {
    public ScaleGizmo(Sprite scaleSprite, PropertiesWindow propertiesWindow) {
        super(scaleSprite, propertiesWindow);
    }

    @Override
    public void editorUpdate(float dt) {
        if (activeGameObject != null) {
            // Get scaled mouse delta
            Vector2f previousPos = new Vector2f(
                MouseListener.getScaledWorld().x + MouseListener.getWorldDx(),
                MouseListener.getScaledWorld().y + MouseListener.getWorldDy()
            );
            Vector2f currentPos = new Vector2f(MouseListener.getScaledWorld());
            Vector2f delta = new Vector2f(currentPos).sub(previousPos);
            
            if (xAxisActive && !yAxisActive) {
                activeGameObject.transform.scale.x += delta.x;
            } else if (yAxisActive) {
                activeGameObject.transform.scale.y += delta.y;
            }
        }

        super.editorUpdate(dt);
    }
}

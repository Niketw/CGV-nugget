package components;

import editor.PropertiesWindow;
import nugget.MouseListener;
import org.joml.Vector2f;

public class TranslateGizmo extends Gizmo {

    public TranslateGizmo(Sprite arrowSprite, PropertiesWindow propertiesWindow) {
        super(arrowSprite, propertiesWindow);
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
                activeGameObject.transform.position.x += delta.x;
            } else if (yAxisActive) {
                activeGameObject.transform.position.y += delta.y;
            }
        }

        super.editorUpdate(dt);
    }
}

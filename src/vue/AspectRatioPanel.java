package vue;

import javax.swing.*;
import java.awt.*;

public class AspectRatioPanel extends JPanel {
    private final double aspectRatio;
    private final Component child;

    public AspectRatioPanel(double aspectRatio, Component child) {
        this.aspectRatio = aspectRatio;
        this.child = child;
        setLayout(null);
        add(child);
    }

    @Override
    public void doLayout() {
        int width = getWidth();
        int height = getHeight();

        int drawWidth = width;
        int drawHeight = (int) (drawWidth / aspectRatio);

        if (drawHeight > height) {
            drawHeight = height;
            drawWidth = (int) (drawHeight * aspectRatio);
        }

        int x = (width - drawWidth) / 2;
        int y = (height - drawHeight) / 2;

        child.setBounds(x, y, drawWidth, drawHeight);
    }

}

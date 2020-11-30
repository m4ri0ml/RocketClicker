package me.MarioML.RocketClicker.handler;

import me.MarioML.RocketClicker.Rocket;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

public class MouseHandler implements NativeMouseListener {
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {}

    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        if (nativeMouseEvent.getButton() == 1 && !Rocket.getInstance().isSkipNext()) {
            Rocket.getInstance().setEnabled(true);
        }
    }

    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        if (nativeMouseEvent.getButton() == 1 && !Rocket.getInstance().isSkipNext()) {
            Rocket.getInstance().setEnabled(false);
        } else {
            Rocket.getInstance().setSkipNext(false);
        }
    }
}

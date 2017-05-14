package net.colinjohnson.game.desktop;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] arg) {

		// set option dialog to native look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] options = { "Host", "Join", "Single Player" };
		int c = JOptionPane.showOptionDialog(null, "How would you like to play?", "Host/Join",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Metric");
		if (c == 0) {
			JOptionPane.showMessageDialog(null, "To host please download the dedicated server software.");
		} else if (c == 1) {
			String d = JOptionPane.showInputDialog("Enter Server ip");
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.fullscreen = false;
		config.vSyncEnabled = true;
		config.title = "\"Squar DOT Jar\"";
		config.resizable = false;
		// new LwjglApplication(new MainClass(), config);
	}
}

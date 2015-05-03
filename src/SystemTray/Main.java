package SystemTray;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
*
* @author David Corbin
* https://github.com/daconex
*
*/

public class Main {
	
	 public static void main(String[] args) {
		 
		 // Check if SystemTray is supported
		 if (!SystemTray.isSupported()) {
			 System.out.println("System tray not supported.");
			 return;
		 }
		 
		 // Create SystemTray
		 final SystemTray systemTray = SystemTray.getSystemTray();
		 // Set icon image an tooltip
		 final TrayIcon trayIcon = new TrayIcon(getImage("twitter.png"),
"SystemTray is working!");
		 
		 // Allow resizing of icon
		 trayIcon.setImageAutoSize(true);

		 MouseAdapter mouseAdapter = new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 System.out.println("You clicked the icon");

				 // This will display small popup message from System Tray
				 trayIcon.displayMessage("Java SystemTray Example","Simple Java example usage of SystemTray", TrayIcon.MessageType.INFO);
			 }
		 };
 
		 trayIcon.addMouseListener(mouseAdapter);
 
		 try {
			 systemTray.add(trayIcon);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
 
	 }
	 
	 // Method to return path to image
	 public static Image getImage(String path) {
		 ImageIcon icon = new ImageIcon(path, "omt");
		 return icon.getImage();
	 }

}

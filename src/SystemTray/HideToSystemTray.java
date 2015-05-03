package SystemTray;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author David Corbin
 * https://github.com/daconex
 *
 */

public class HideToSystemTray extends JFrame {
    TrayIcon trayIcon;
    SystemTray tray;
    
    // Constructor
    HideToSystemTray() {
    	/*
        try{
            System.out.println("setting look and feel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){
            System.out.println("Unable to set LookAndFeel");
        }
        */
    	
        if(SystemTray.isSupported()){
        	
        	// Get system tray from operating system
            tray = SystemTray.getSystemTray();

            // Get icon for taskbar
            Image image = Toolkit.getDefaultToolkit().getImage("twitter.png");
            
            // When exit is clicked from popup menu
            ActionListener exitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            };
            
            // Menu above taskbar icon
            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem = new MenuItem("Exit");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);
            defaultItem = new MenuItem("Open");
            
            // When open is clicked from popup menu
            defaultItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                    setExtendedState(JFrame.NORMAL);
                }
            });
            popup.add(defaultItem);
            trayIcon = new TrayIcon(image, "SystemTray example", popup);
            trayIcon.setImageAutoSize(true);
        } else{
            System.out.println("SystemTray not supported");
        }
        
        // Detect when the window is minimized
        addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState()==ICONIFIED){
                	// Try to add icon to tray
                    try {
                        tray.add(trayIcon);
                        setVisible(false);
                    } catch (AWTException ex) {
                        System.out.println("Unable to add to tray");
                        System.exit(1);
                    }
                }
                
                if (e.getNewState()==7) {
                    try{
                    	tray.add(trayIcon);
                    	setVisible(false);
                    } catch(AWTException ex){
                    	System.out.println("Unable to add to system tray");
                    	System.exit(1);
                    }
                }
                
                if(e.getNewState()==MAXIMIZED_BOTH){
                    tray.remove(trayIcon);
                    setVisible(true);
                }
                
                if(e.getNewState()==NORMAL){
                    tray.remove(trayIcon);
                    setVisible(true);
                }
            }
        });
        // Set window icon
        setIconImage(Toolkit.getDefaultToolkit().getImage("twitter.png"));

        setVisible(true);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args){
        new HideToSystemTray();
    }
}

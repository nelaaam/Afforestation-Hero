package game.afhero.torf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Window {
	private static int width, height;
	private static Image iconimage;
	private static String title;
	private static STATE gamestate;
	
	private static JFrame frame;
	private JButton tempButton;
	private JDialog newDialog;
	private JPanel newPanel;
	private JTextArea textArea;
	
	//game components
	private BufferedImageLoader loader = new BufferedImageLoader();
	private BufferedImage menuSheet = loader.loadImage("/menuSheet.png"), button;
	private SpriteSheet ms = new SpriteSheet(menuSheet);
	
	private int locX;
	private int locY;
	public Window() {};
	public Window(int WIDTH, int HEIGHT, Image ICONIMAGE , String TITLE, STATE GAMESTATE) {
		audioPlayer.getMusic("game_music").setVolume(1.0f);
		setGameState(GAMESTATE);
		if (GAMESTATE == STATE.Startup) {
			width = WIDTH;
			height = HEIGHT;
			iconimage = ICONIMAGE;
			title = TITLE;
			setGameState(GAMESTATE);
			// frame instance and settings
			frame = new JFrame(TITLE);
	        frame.setIconImage(ICONIMAGE);
	        frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
	        frame.setMaximumSize(new Dimension(WIDTH,HEIGHT));
	        frame.setMinimumSize(new Dimension(WIDTH,HEIGHT));
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);
	        frame.setLocationRelativeTo(null);
	        frame.addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	            	//insert save function here
	                System.out.println("Window was closed");
	                e.getWindow().dispose();
	            }
	        });
	        setGameState(STATE.MainMenu);
		}
		
		if (gamestate == STATE.MainMenu) {
			//set menu window
			frame.setContentPane(new JLabel(new ImageIcon(new ImageIcon(loader.loadImage("/mainMenu.jpg")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_FAST))));
	    	frame.revalidate();
	    	frame.repaint();
	    	setMainMenu();
	    	
		} else if (gamestate == STATE.LevelMenu) {
			//set level map window
			frame.getContentPane().removeAll();
			frame.revalidate();
			frame.setContentPane(new JLabel(new ImageIcon(new ImageIcon(loader.loadImage("/levelMap.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_FAST))));
			frame.revalidate();
			frame.repaint();
			
			new LevelMap(WIDTH, HEIGHT, ICONIMAGE,GAMESTATE,frame);
			
			button = ms.grabImage(4,1,85,75);
    		tempButton = new JButton(new ImageIcon(button));
    		button = ms.grabImage(4,2,85,75);
    		tempButton.setPressedIcon(new ImageIcon(button));
	        
	        setButton(tempButton,85,75);
	        tempButton.setLocation(820,10);
	        tempButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e){
	            	audioPlayer.getSound("button_sound").play();
	             	new Window(width,height,iconimage,title,STATE.MainMenu);
	            }
	        });
	        
	        frame.add(tempButton);
			
		} else if (gamestate == STATE.GamePage) {
			frame.getContentPane().removeAll();
			frame.revalidate();
			frame.setContentPane(new JLabel(new ImageIcon(new ImageIcon(loader.loadImage("/gamePage.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH))));
			frame.revalidate();
			
			button = ms.grabImage(4,1,85,75);
    		tempButton = new JButton(new ImageIcon(button));
    		button = ms.grabImage(4,2,85,75);
    		tempButton.setPressedIcon(new ImageIcon(button));
	        
	        setButton(tempButton,85,75);
	        tempButton.setLocation(820,10);
	        tempButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e){
	            	audioPlayer.getSound("button_sound").play();
	            	setGameState(STATE.Paused);
	            	newDialog = new JDialog(frame, "Confirm Exit Level");
	            	newPanel = new JPanel();
	            	setNewDialog(250,100,newDialog);
	            	JLabel ConfirmDialog = new JLabel ("You will lose all game progress.");
	                ConfirmDialog.setFont(new Font("Calibri",Font.PLAIN,15));
	                ConfirmDialog.setForeground(Color.BLACK);
	                newPanel.add(ConfirmDialog, BorderLayout.PAGE_START);
	         		newPanel.setOpaque(true);
	                newDialog.add(newPanel);
	                JButton ExitLevel = new JButton("Exit");
	                JButton CancelExit = new JButton("Cancel");
	                CancelExit.addActionListener(new ActionListener() {
	                	public void actionPerformed(ActionEvent e) {
	                		setGameState(STATE.GameStarted);
	                		newPanel.removeAll();
	                		newPanel.revalidate();
	                    	newDialog.dispose();
	                	}
	                });
	                ExitLevel.addActionListener(new ActionListener() {
	                	public void actionPerformed(ActionEvent e) {
	                		setGameState(STATE.LevelMenu);
	                		newDialog.dispose();
	                     	new Window(width,height,iconimage,title,STATE.LevelMenu);
	                	}
	                });
	                newPanel.add(ExitLevel,BorderLayout.PAGE_END);
	                newPanel.add(CancelExit, BorderLayout.PAGE_END);
	            	newDialog.setVisible(true);

	            }
	        });
	        
	        
	        frame.add(tempButton);
	        setGameState(STATE.GamePage);
	        new GamePlay(width, height, frame,STATE.GamePage,iconimage);
		}
        	

       
        frame.setVisible(true);
        frame.setLayout(null);
        
        
	}
	public void setMainMenu() {
		
		locY = 525;
		locX = 315;
		
		for (int y = 1, x = 1; x < 4;locX+=95,x++) {
			button = ms.grabImage(x,y,85,75);
    		tempButton = new JButton(new ImageIcon(button));
    		button = ms.grabImage(x,y+1,85,75);
    		tempButton.setPressedIcon(new ImageIcon(button));
    		
    		if (x == 1) {
    			// Help Button
    			tempButton.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e){
        				newDialog = new JDialog(frame, "Game Help" , true);
        				audioPlayer.getSound("button_sound").play();
        				setNewDialog(260,215,newDialog);
        				setTextArea(getHelpContent(),5,20);
        				setPanelArea();
        				newDialog.add(newPanel);
        				newPanel.add(textArea);
    	                newDialog.setVisible(true);
        			}
        		});
    		} else if(x == 2) {
    			// Play Button
    			tempButton.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e){
    					audioPlayer.getSound("button_sound").play();
                     	setGameState(STATE.LevelMenu);
                     	frame.getContentPane().removeAll();
                     	frame.revalidate();
                     	frame.repaint();
                     	new Window(width,height,iconimage,"Afforestation Hero - Level Map",STATE.LevelMenu);
    				}
        		});
    		} else if(x == 3) {
    			// About Button 
    			tempButton.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e){
        				audioPlayer.getSound("button_sound").play();
        				newDialog = new JDialog(frame, "Game About" , true);
        				setNewDialog(320,200,newDialog);
        				setTextArea(getAboutContent(),10,25);
        				setPanelArea();
        				newDialog.add(newPanel);
        				newPanel.add(textArea);
    	                newDialog.setVisible(true);
        			}
        		});
    		}
    		tempButton.setLocation(locX,locY);
    		
    		setButton(tempButton, 85, 75);
    		frame.add(tempButton);
		}
	}
	public void setButton(JButton tempButton, int sizeX, int sizeY) {
		tempButton.setBorder(null);
        tempButton.setContentAreaFilled(false);
        tempButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tempButton.setSize(sizeX,sizeY);
	}
	public void setGameState(STATE GS) {
		gamestate = GS;
	}
	public void setNewDialog(int sizeX, int sizeY, JDialog newDialog) {
		newDialog.setResizable(false);
		newDialog.setSize(sizeX, sizeY);
		newDialog.setLocationRelativeTo(frame);
		newDialog.setIconImage(iconimage);
	}
	public void setTextArea(String content, int row, int column) {
		textArea = new JTextArea(row,column);
		textArea.setText(content);
		textArea.setFont(new Font("Calibri", Font.PLAIN, 16));
		textArea.setForeground(Color.WHITE);
	    textArea.setWrapStyleWord(true);
	    textArea.setLineWrap(true);
	    textArea.setOpaque(false);
	    textArea.setEditable(false);
	    textArea.setFocusable(false);
	}
	public void setPanelArea() {
		newPanel = new JPanel();
		newPanel.setBackground(new Color(88,160,44));
	}
	public STATE getGameState() {
		return gamestate;
	}
	public String getHelpContent() {
		String helpContent = "Afforestation Hero: True or False is a simple game of true or false composed of 25 different levels." + "\n\n" +"To win each level, simply answer the set of questions correctly, within the time limit and with the given chances for incorrect answers.";
		return helpContent;
	}
	public String getAboutContent() {
		String aboutContent = "GAME CREATOR: KANELA MARTINO" + "\n\n" + "Afforestation Hero is based on the MDG 7: Environment Sustainability." + "\n\n" + "Afforestation Hero: True or False v1.0" + "\n" +"Copyright. 2017";
		return aboutContent;
	}
}

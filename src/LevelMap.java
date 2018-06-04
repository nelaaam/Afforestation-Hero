package game.afhero.torf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelMap extends Window{
	//UI BACK COMPONENTS
	private JFrame frame;
	private JButton tempButton,lockedButton;
	private JDialog newDialog;
	private JPanel dialogPanel, buttonPanel, infoPanel, aScorePanel, scorePanel;
	private JLabel level, goal, lives, aScore, score, LevelComplete, LevelLocked;
	private JButton confirm, cancel;
	
	//UI IMPORTS
	private BufferedImageLoader loader = new BufferedImageLoader();
	private BufferedImage levelSheet = loader.loadImage("/levelSheet.png"), button;
	private SpriteSheet ls = new SpriteSheet(levelSheet);
	
	private GamePlay gamePlay = new GamePlay();

	private int[][] levelMap = 		{{1,2,3,4,5},
									{6,7,8,9,10},
									{11,12,13,14,15},
									{16,17,18,19,20},
									{21,22,23,24,25}};
	
	public LevelMap(int WIDTH, int HEIGHT, Image ICONIMAGE, STATE GAMESTATE, JFrame frame) {
		this.frame = frame;
		//frame component instances
		dialogPanel = new JPanel();
		buttonPanel = new JPanel();
		infoPanel = new JPanel();
		aScorePanel = new JPanel();
		scorePanel = new JPanel();
		confirm = new JButton("Confirm");
		cancel = new JButton("Cancel");
		level = new JLabel();
		goal = new JLabel();
		lives = new JLabel();
		aScore = new JLabel(); 
		score = new JLabel();
		
		LevelComplete = new JLabel("COMPLETED");
		LevelLocked = new JLabel("LEVEL LOCKED");
		level = new JLabel("LEVEL " + String.valueOf(gamePlay.getLevel()));
		goal = new JLabel(String.valueOf(gamePlay.getLevelQuestion()) + " QUESTIONS");
		lives = new JLabel(String.valueOf(gamePlay.getLevelLife()) + " LIVES");
		aScore = new JLabel(String.valueOf(gamePlay.getAPercent()));
		score = new JLabel(String.valueOf(gamePlay.getPoints()));
		
		infoPanel.setBounds(669,185,210,130);
		infoPanel.setOpaque(false);
		
		aScorePanel.setBounds(669,365,210,110);
		aScorePanel.setOpaque(false);
		aScorePanel.add(aScore,BorderLayout.CENTER);
		
		scorePanel.setBounds(669,520,210,110);
		scorePanel.setOpaque(false);
		scorePanel.add(score,BorderLayout.CENTER);
		
		gamePlay.setJLabel(LevelLocked, 30);
		gamePlay.setJLabel(LevelComplete, 30);
		gamePlay.setJLabel(level, 28);
		gamePlay.setJLabel(goal, 28);
		gamePlay.setJLabel(lives, 28);
		gamePlay.setJLabel(aScore, 80);
		
		if(gamePlay.getPoints() > 999) {
			gamePlay.setJLabel(score, 65);
		} else if (gamePlay.getPoints() > 9999) {
			gamePlay.setJLabel(score, 50);
		} else if (gamePlay.getPoints() < 1000) {
			gamePlay.setJLabel(score, 80);
		}
		

		frame.add(infoPanel);
		frame.add(aScorePanel);
		frame.add(scorePanel);
		
		
		//setActions
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				audioPlayer.getSound("button_sound").play();
				newDialog.dispose();
				new Window(WIDTH, HEIGHT , ICONIMAGE, "AFFORESTATON HERO", STATE.GamePage);
			}
			
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				audioPlayer.getSound("button_sound").play();
				newDialog.dispose();
			}
		});
		
		
		buttonPanel.add(confirm);
		buttonPanel.add(cancel);
		//frame component properties
		setPanel(dialogPanel,200,60);
		setPanel(buttonPanel,200,30);
		//componentInit();
		setMapButton();
		//setMapPanel();
	}
	public void setMapButton() {
		for (int y = 0, locY = 50; y <5; y++, locY = locY + 120) {
			for (int x = 0,locX = 30; x < 5; x++, locX = locX + 120 ) {
				button = ls.grabImage(x+1,y+1,110,110);
				tempButton = new JButton(new ImageIcon(button));
				setButton(tempButton, 110, 110);
				tempButton.setLocation(locX,locY);
			    tempButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						audioPlayer.getSound("button_sound").play();
						newDialog = new JDialog(frame, "Play A-Hero" , true);
			    		setNewDialog(200,90,newDialog);
			    		dialogPanel.add(new JLabel("Confirm to Play Level"), BorderLayout.CENTER);
			    		newDialog.add(dialogPanel, BorderLayout.PAGE_START);
			    		newDialog.add(buttonPanel, BorderLayout.PAGE_END);
			    		newDialog.setVisible(true);
			            }
			        });
			    
			    frame.add(tempButton);
			    if (levelMap[y][x] == gamePlay.getLevel()) {
			    	tempButton.addMouseListener(new MouseAdapter() {
			    		public void mouseEntered(MouseEvent e) {
				    		 mouseHovers(1);
				 	    }
				 	    public void mouseExited(MouseEvent e) {
				 	    	 mouseExits();
				 	    }
			    	});
			    } else if(levelMap[y][x] > gamePlay.getLevel()) {
			    	lockedButton = new JButton(new ImageIcon(loader.loadImage("/0.png")));
			    	lockedButton.addMouseListener(new MouseAdapter() {
			    		public void mouseEntered(MouseEvent e) {
				    		 mouseHovers(2);
				 	    }
				 	    public void mouseExited(MouseEvent e) {
				 	    	 mouseExits();
				 	    }
			    	});
			    	setButton(lockedButton, 110, 110);
					tempButton.add(lockedButton);
			    } else if (levelMap[y][x] < gamePlay.getLevel()) {
			    	lockedButton = new JButton(new ImageIcon(loader.loadImage("/00.png")));
			    	setButton(lockedButton, 110, 110);
					lockedButton.addMouseListener(new MouseAdapter() {
			    		public void mouseEntered(MouseEvent e) {
				    		 mouseHovers(3);
				 	    }
				 	    public void mouseExited(MouseEvent e) {
				 	    	 mouseExits();
				 	    }
			    	});
					tempButton.add(lockedButton);
			    }
			 	    
			}
		}
	}
	
	public void mouseHovers(int type) {
		switch(type) {
		case 1:
			infoPanel.add(level);
			infoPanel.add(goal);
			infoPanel.add(lives);
			gamePlay.panelRevalidation(infoPanel);
			break;
		case 2:
			gamePlay.panelRevalidation(infoPanel);
			infoPanel.add(LevelLocked);
			break;
		case 3:
			gamePlay.panelRevalidation(infoPanel);
			infoPanel.add(LevelComplete);
			break;
		}
	}
	
	public void mouseExits() {
		gamePlay.panelValidation(infoPanel);
		infoPanel.repaint();
	}
	public void setPanel(JPanel panel,int sizeX, int sizeY) {
		panel.setSize(sizeX, sizeY);
		panel.setBackground(Color.WHITE);
		panel.setOpaque(true);
	}
}

package game.afhero.torf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class GamePlay {
	private int width, height;
	private Image iconimage;
	
	//GAME OBJECTS
	private static int Score, Goal, Lives, Level,Points, APercent, Counter, LevelQuestion,LevelLife;
	
	//GAME COUNTERS
	private final int TotalQuestions = 375;
	private double APercentD;
	private static boolean levelWon;
	private int timeCounter = 10, tempCounter = 10;
	
	
	//OBJECT INITS
	private JFrame frame;
	private JLabel Correct, Incorrect;
	private JLabel timer,score,life,goal,level,levelwon;
	private JPanel timerPanel, scorePanel, lifePanel, goalPanel, questionPanel, buttonPanel, dialogPanel, levelPanel, answerPanel;
	private JButton Continue,ContinueLevel,TRUE,FALSE;
	private JDialog d;
	private JTextArea textArea;
	//CLASS INCLUSION
	private Color myGreen = new Color(88,160,44);
	private Window window = new Window();
	private GameContent gameContent = new GameContent();
	private BufferedImageLoader loader = new BufferedImageLoader();
	public Timer counter;
	
	public GamePlay() {
		if (getLevel() == 0) {
			setLevel(1);
			setLevelLife(3);
			setLives(getLevelLife());
			setLevelQuestion(3);
		}
	}
	public GamePlay(int WIDTH, int HEIGHT, JFrame frame, STATE GAMESTATE, Image ICONIMAGE) {
		audioPlayer.getMusic("game_music").setVolume(0.3f);
		
		counter = new Timer(1000,new ActionListener() {
			public void actionPerformed(ActionEvent E) {
				
				frame.add(timerPanel);
				Counter--;
				timer = new JLabel(String.valueOf(Counter) + " sec");
				setJLabel(timer,28);
				if(Counter == timeCounter - 1 && window.getGameState() == STATE.GamePage) {
					audioPlayer.getSound("timer_sound").play();
					timeCounter--;
					timer.setText(String.valueOf(Counter) + " sec");
					timerPanel.removeAll();
					timerPanel.revalidate();
					timerPanel.repaint();
					timerPanel.add(timer);
				} else if (window.getGameState() == STATE.Paused){
					tempCounter = Counter;
					counter.stop();
					setTimer(100);	
					window.setGameState(STATE.Waiting);
					counter.start();
				} else if (window.getGameState() == STATE.LevelMenu){
					counter.stop();
					setTimer(10);
					timerPanel.removeAll();
					timerPanel.revalidate();
				}
				if (window.getGameState() == STATE.GameStarted) {
					window.setGameState(STATE.GamePage);
					if (tempCounter == timeCounter-1) {
						Counter = tempCounter+1;
					} else 
						Counter = tempCounter;
					
					counter.start();
				}
					
				if (Counter == 0) {
					audioPlayer.getSound("wrong_Answer").play();
					counter.stop();
					JDialog d = new JDialog(frame, "Out of Time", true);
					setFailedGame();
					setDialog(d,300,100);
				}
			}
		});
		this.frame = frame;
		this.width = WIDTH;
		this.height = HEIGHT;
		this.iconimage = ICONIMAGE;
		componentInit(frame);
		resetPanels();
		
		//Initialize Goal and Timer
		setGoal(0);
		setTimer(10);
		setLevelWon(false);
		
		startGame(frame);
	}
	public void componentInit(JFrame frame){
		//Button Instantiation
		TRUE = new JButton(new ImageIcon(loader.loadImage("/trueButton.png")));
		FALSE = new JButton(new ImageIcon(loader.loadImage("/falseButton.png")));
		Continue = new JButton("Continue");
		ContinueLevel = new JButton("Back");
		
		
		//Button Setting
		setGameButton(TRUE);
		setGameButton(FALSE);
		TRUE.setLocation(225,540);
		FALSE.setLocation(525,540);
		
		//Button Actions
		TRUE.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		audioPlayer.getSound("button_sound").play();
        		buttonAction(true);
        	}
        });
		
		
		FALSE.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		audioPlayer.getSound("button_sound").play();
        		buttonAction(false);
        	}
        });
		
		Continue.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		audioPlayer.getSound("button_sound").play();
        		resetPanels();
        		d.dispose();
        		startGame(frame);
        	}
        });
		ContinueLevel.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		audioPlayer.getSound("button_sound").play();
        		resetPanels();
        		d.dispose();
        		new Window(width,height,iconimage,"AFFORESTATION HERO: TRUE OR FALSE",STATE.LevelMenu);
        	}
        });
		
		levelwon = new JLabel("Level Won!");
        levelwon.setFont(new Font("Calibri", Font.BOLD, 20));
        levelwon.setForeground(Color.WHITE);
     
		
		//Panel Instantiation
		answerPanel = new JPanel();
		timerPanel = new JPanel();
		scorePanel = new JPanel();
		lifePanel = new JPanel();
		goalPanel = new JPanel();
		levelPanel = new JPanel();
		questionPanel = new JPanel();
		dialogPanel = new JPanel();
		buttonPanel = new JPanel();
		
		//Panel Setting (for debugging)
		timerPanel.setOpaque(false);
		scorePanel.setOpaque(false);
		lifePanel.setOpaque(false);
		goalPanel.setOpaque(false);
		levelPanel.setOpaque(false);
		questionPanel.setOpaque(false);
		
		//Other Panel Settings
		answerPanel.setBackground(myGreen);
		dialogPanel.setBackground(myGreen);
		buttonPanel.setBackground(myGreen);
		
		
		levelPanel.setBounds(332,115,255,55);
		questionPanel.setBounds(85, 220, 740, 270);
		timerPanel.setBounds(92,30,75,40);
		scorePanel.setBounds(240,30,125,40);
		lifePanel.setBounds(455,30,75,40);
		goalPanel.setBounds(600,30,165,40);
		
		
		//Label Instantiation
		Correct = new JLabel("CORRECT!");
		Incorrect = new JLabel("INCORRECT!");
		
		//Adding components to frame
		frame.add(timerPanel);
		frame.add(scorePanel);
		frame.add(lifePanel);
		frame.add(goalPanel);
		frame.add(levelPanel);
		frame.add(questionPanel);
		frame.add(FALSE);
		frame.add(TRUE);
		
	}
	
	public void setGoal(int G){
		Goal = G;
	}
	public void setLives(int Li){
		Lives = Li;
	}
	public void setLevel(int Le){
		Level = Le;
	}
	public void setPoints(int P) {
		Points = P;
	}
	public void setScore(int S) {
		Score = S;
	}
	public void setAPercent() {
		this.APercentD = ((double) getLevelQuestion()/(double)TotalQuestions)*100;
		if (APercent == 0) {
			APercent = 1;
		} else if(this.APercentD - APercent < 0.5) {
			APercent =  ((int) this.APercentD) + APercent;
		} else if(this.APercentD - APercent >= 0.5) {
			APercent = ((int) this.APercentD + 1) + APercent;
		}
	}
	public void setTimer(int C) {
		Counter = C;
	}
	public void setLevelQuestion(int lQ) {
		LevelQuestion = lQ;
	}
	public void setLevelLife(int lL) {
		LevelLife = lL;
	}
	public void setLevelWon(boolean lW) {
		levelWon = lW;
	}
	public int getGoal() {
		return Goal;
	}
	public int getLives() {
		return Lives;
	}
	public int getLevel() {
		return Level;
	}
	public int getPoints() {
		return Points;
	}
	public int getScore() {
		return Score;
	}
	public int getAPercent() {
		return APercent;
	}
	public int getLevelQuestion() {
		return LevelQuestion;
	}
	public int getLevelLife() {
		return LevelLife;
	}
	public boolean getBoolean() {
		return levelWon;
	}
	public void setGamePanel() {
		timer = new JLabel(String.valueOf(Counter) + " sec");
		score = new JLabel(String.valueOf(Score) + " pts");
		life = new JLabel(String.valueOf(Lives) + "/" + String.valueOf(LevelLife));
		goal = new JLabel(String.valueOf(Goal) + "/" + String.valueOf(LevelQuestion) + " goals ");
		level = new JLabel("LEVEL " + String.valueOf(Level));
		
		//set panel content settings
		setJLabel(timer,28);
		setJLabel(score,28);
		setJLabel(life,28);
		setJLabel(goal,28);
		setJLabel(Correct,20);
		setJLabel(Incorrect,20);
		level.setFont(new Font("Calibri", Font.BOLD, 40));
		level.setForeground(Color.BLACK);
	}
	
	public void setGameButton(JButton tempButton) {
		tempButton.setBorder(null);
        tempButton.setContentAreaFilled(false);
        tempButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tempButton.setSize(175,115);
	}
	public void setPanelContent() {
		panelRevalidation(timerPanel);
		panelRevalidation(scorePanel);
		panelRevalidation(lifePanel);
		panelRevalidation(goalPanel);
		panelRevalidation(levelPanel);
		timerPanel.add(timer, BorderLayout.CENTER);
		scorePanel.add(score, BorderLayout.CENTER);
		lifePanel.add(life, BorderLayout.CENTER);
		goalPanel.add(goal, BorderLayout.CENTER);
		levelPanel.add(level, BorderLayout.CENTER);
	}
	public void setJLabel(JLabel J, int fSize) {
		J.setFont(new Font("Calibri", Font.BOLD, fSize));
		J.setForeground(Color.WHITE);
	}
	
	
	public void setDialog(JDialog d, int sizeX, int sizeY) {
		this.d = d;
		d.setSize(sizeX,sizeY);
        d.setLocationRelativeTo(frame);
        d.setResizable(false);
        d.setIconImage(iconimage);
        if(!getBoolean()) {
        	buttonPanel.add(Continue, BorderLayout.CENTER);
        }
        else if (getBoolean()) {
        	buttonPanel.add(ContinueLevel, BorderLayout.CENTER);
        }
        d.add(dialogPanel, BorderLayout.PAGE_START);
        d.add(answerPanel, BorderLayout.CENTER);
        d.add(buttonPanel, BorderLayout.PAGE_END);
        d.setVisible(true);
	}
	public void panelValidation(JPanel p) {
		p.removeAll();
		p.revalidate();
	}
	public void panelRevalidation(JPanel p) {
		p.revalidate();
		p.repaint();
	}
	
	public void startGame(JFrame frame){
		setTimer(10);
		setTimeCounter();
		setGamePanel();
		setPanelContent();
		textArea = new JTextArea(5, 20);
		if (Goal < LevelQuestion && Lives > 0) {
			String Q = gameContent.getQuestion();
			textArea.setText(Q);
            counter.start();
		}else if(Lives == 0 ) {
			audioPlayer.getSound("level_failed").play();
			setFailedGame();
			
			setDialog(d,300,100);
			new Window(width, height , iconimage, "AFFORESTATON HERO", STATE.LevelMenu);
		}else if (Goal == LevelQuestion) {
			setNextGame();
	        panelValidation(dialogPanel);
	        audioPlayer.getSound("level_complete").play();
	        
	   
	        dialogPanel.add(levelwon, BorderLayout.CENTER);
			d = new JDialog(frame, "Level Won!", true);
			setDialog(d,300,100);
			new Window(width, height , iconimage, "AFFORESTATON HERO", STATE.LevelMenu);
		} 
		textArea.setForeground(Color.BLACK);
		setTextArea(textArea,40,Font.BOLD);
		questionPanel.add(textArea, BorderLayout.CENTER);
	}
	private void setTimeCounter() {
		timeCounter = 10;
	}
	public void setTextArea(JTextArea textArea, int fSize, int fontStyle) {
		textArea.setFont(new Font("Calibri", fontStyle, fSize));
	    textArea.setWrapStyleWord(true);
	    textArea.setLineWrap(true);
	    textArea.setOpaque(false);
	    textArea.setEditable(false);
	    textArea.setFocusable(false);
	}
	public void setNextGame() {
		setAPercent();
		setLevelWon(true);
		
		//Question Setting
		LevelQuestion = getLevelQuestion() + 1;
        setLevelQuestion(LevelQuestion);
        
        //LevelSetting
        Level = getLevel() + 1;
        setLevel(Level);
        
        //Life Setting
        if (Level < 6) {
			setLevelLife(5);
		} else if (getLevel() > 5 && (getLevel())%2 == 0) {
			setLevelLife(LevelLife+1);		
		} else if (getLevel() > 5 && (getLevel())%2 > 0) {
		}
        setLives(getLevelLife());
        
        //Points Setting
        setPoints(getScore());
        
	}
	public void setFailedGame() {
		setLevelWon(false);
		JLabel Failed = new JLabel();
		Failed.setForeground(Color.WHITE);
		Failed.setFont(new Font("Calibri", Font.PLAIN, 18));
		if (Lives == 0) {
			setLives(getLevelLife());
	        setPoints(getScore());
	        Failed.setText("Out of Lives!");
			dialogPanel.add(Failed, BorderLayout.CENTER);
		}else if (Counter == 0) {
			setLives(Lives--);
			setPoints(Points-=10);
			setScore(getPoints());
			Failed.setText("Out of Time!");
			dialogPanel.add(Failed, BorderLayout.CENTER);		
		}
		
	}
	public void resetPanels() {
		panelValidation(questionPanel);
		panelValidation(dialogPanel);
		panelValidation(buttonPanel);
		panelValidation(timerPanel);
		panelValidation(scorePanel);
		panelValidation(goalPanel);
		panelValidation(lifePanel);
		panelValidation(levelPanel);
		panelValidation(answerPanel);
	}
	public void correctAnswer() {
		Goal++;
		Score+=100;
	}
	public void wrongAnswer() {
		Lives--;
		Score-=10;
	}
	public void buttonAction(boolean buttonTest) {
		counter.stop();
		textArea = new JTextArea(5,20);
		textArea.setForeground(Color.WHITE);
		setTextArea(textArea,18,Font.PLAIN);
		textArea.setText(gameContent.TrueContent[gameContent.getQNumber()]);
		d = new JDialog(frame, "TRUE OR FALSE", true);
		if(buttonTest) {
			if(gameContent.getAnswerFlag() == true) {
				audioPlayer.getSound("correct_Answer").play();;
				correctAnswer();
				dialogPanel.add(Correct,BorderLayout.CENTER);
				setDialog(d,300,100);
			} else if(gameContent.getAnswerFlag() == false) {
				audioPlayer.getSound("wrong_Answer").play();;
				wrongAnswer();
				dialogPanel.add(Incorrect,BorderLayout.PAGE_START);
				answerPanel.add(textArea, BorderLayout.CENTER);
				setDialog(d,300,200);
			}
		} else {
			if(gameContent.getAnswerFlag() == false) {
				audioPlayer.getSound("correct_Answer").play();;
				correctAnswer();
				dialogPanel.add(Correct,BorderLayout.PAGE_START);
				answerPanel.add(textArea, BorderLayout.CENTER);
				setDialog(d,300,200);
			} else if(gameContent.getAnswerFlag() == true) {
				audioPlayer.getSound("wrong_Answer").play();;
				wrongAnswer();
				dialogPanel.add(Incorrect,BorderLayout.CENTER);
				setDialog(d,300,100);
			}
		}
	}
}

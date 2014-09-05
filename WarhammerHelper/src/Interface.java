
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Interface extends JFrame implements ActionListener{
	
	public int[][] assaultHitChart=new int[10][10];
	public int[][] rangedHitChart=new int[2][5];
	public int[][] woundChart=new int[10][10];
	JButton melee,ranged,wound,saves;
	JTextField attackerInput,defenderInput,attackQuantityInput,rangedBS,shooterNum,strengthInput,toughnessInput,woundQuantityInput,
			saveValue,saveQuantity;
	int attackerWS,defenderWS,hitQuantity,shooterBS,strength,toughness,numOfSaves,saveTarget,passQuantity;
	JLabel toHitNum,attackResults,rangedToHit,rangedNumOfHit,toWoundNum,woundResults,savePass,saveFail;
	
	public  Interface(){
		JFrame frame = new JFrame("Main Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2,2));
		melee = new JButton ("Melee Combat Calclutaions");
		ranged= new JButton ("Ranged Combat Calculations");
		wound= new JButton ("To Wound Calculations");
		saves= new JButton ("Roll your saves");
		melee.addActionListener(this);
		ranged.addActionListener(this);
		wound.addActionListener(this);
		saves.addActionListener(this);
		frame.getContentPane().add(melee, BorderLayout.CENTER);
		frame.getContentPane().add(ranged, BorderLayout.CENTER);
		frame.getContentPane().add(wound, BorderLayout.CENTER);
		frame.getContentPane().add(saves, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		for (int i=0; i<10;i++){
			for (int j=0; j<10; j++){
				if(i<j){
					assaultHitChart[i][j]=3;
				}
				else if(i>1&&j==0){
					assaultHitChart[i][j]=5;
				}
				else if(i>3&&j==1){
					assaultHitChart[i][j]=5;
				}
				else if(i>5&&j==2){
					assaultHitChart[i][j]=5;
				}
				else if(i>7&&j==3){
					assaultHitChart[i][j]=5;
				}
				else
					assaultHitChart[i][j]=4;
			}
		}
		rangedHitChart[0][0]=1;
		rangedHitChart[0][1]=2;
		rangedHitChart[0][2]=3;
		rangedHitChart[0][3]=4;
		rangedHitChart[0][4]=5;
		rangedHitChart[1][0]=6;
		rangedHitChart[1][1]=5;
		rangedHitChart[1][2]=4;
		rangedHitChart[1][3]=3;
		rangedHitChart[1][4]=2;
		for (int i=0;i<10;i++){
			for (int j=0;j<10;j++){
				if(j<(i-1)){
					woundChart[i][j]=2;
				}
				else if(j==(i-1)){
					woundChart[i][j]=3;
				}
				else if(j==i){
					woundChart[i][j]=4;
				}
				else if(j==(i+1)){
					woundChart[i][j]=5;
				}
				else if(j==(i+2)||j==(i+3)){
					woundChart[i][j]=6;
				}
				else
					woundChart[i][j]=7;
			}
		}
	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		String s="";
		if(source==melee){
			JFrame meleePanel = new JFrame("Melee Panel");
			meleePanel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			JLabel toHitLabel=new JLabel("To Hit Value");
			toHitNum= new JLabel("");
			JLabel attackLabel= new JLabel("Attacker's WS");
			JLabel defenderLabel= new JLabel("Defender's WS");
			JLabel attackQuantityLabel= new JLabel("Number of attackers");
			JLabel attackResultLabel= new JLabel("Number of Hits");
			attackResults= new JLabel("");
			attackerInput= new JTextField();
			defenderInput= new JTextField();
			attackQuantityInput= new JTextField();
			attackerInput.addActionListener(this);
			defenderInput.addActionListener(this);
			attackQuantityInput.addActionListener(this);
			toHitNum.setHorizontalAlignment(JLabel.CENTER);
			toHitLabel.setHorizontalAlignment(JLabel.CENTER);
			GridLayout newLayout= new GridLayout(2,4);
			meleePanel.setLayout(newLayout);
			meleePanel.getContentPane().add(attackLabel, BorderLayout.CENTER);
			meleePanel.getContentPane().add(defenderLabel, BorderLayout.CENTER);
			meleePanel.getContentPane().add(toHitLabel, BorderLayout.CENTER);
			meleePanel.getContentPane().add(attackQuantityLabel, BorderLayout.CENTER);
			meleePanel.getContentPane().add(attackResultLabel, BorderLayout.CENTER);
			meleePanel.getContentPane().add(attackerInput, BorderLayout.CENTER);
			meleePanel.getContentPane().add(defenderInput, BorderLayout.CENTER);
			meleePanel.getContentPane().add(toHitNum, BorderLayout.CENTER);
			meleePanel.getContentPane().add(attackQuantityInput, BorderLayout.CENTER);
			meleePanel.getContentPane().add(attackResults, BorderLayout.CENTER);
			meleePanel.pack();
			meleePanel.setVisible(true);
			toHitNum.setText(s);
		}
		else if(source==attackerInput){	
			attackerWS=Integer.parseInt(attackerInput.getText());
			if(defenderWS>=1&&attackerWS>=1){
				s=s+assaultHitChart[defenderWS-1][attackerWS-1];	
			}
			else
				s=s+0;
			toHitNum.setText(s);
		}
		else if(source==defenderInput){	
			defenderWS=Integer.parseInt(defenderInput.getText());
			if(defenderWS>=1&&attackerWS>=1){
				s=s+assaultHitChart[defenderWS-1][attackerWS-1];	
			}
			else
				s=s+0;
			toHitNum.setText(s);
		}
		else if(source==attackQuantityInput){
			if(defenderWS>=1&&attackerWS>=1){
				s=s+assaultHitChart[defenderWS-1][attackerWS-1];
				for(int i=0;i<Integer.parseInt(attackQuantityInput.getText());i++){
					int diceRoll=rollDice();
					System.out.println(""+diceRoll);
					if(diceRoll>=assaultHitChart[defenderWS-1][attackerWS-1]){
						hitQuantity++;
					}
				}
				s=""+hitQuantity;
				attackResults.setText(s);
				hitQuantity=0;
			}
		}
		else if(source==ranged){
			JFrame rangedPanel= new JFrame("Ranged Combat Time");
			rangedPanel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			JLabel bsLabel= new JLabel("What is your BS?");
			JLabel rangedHitLabel= new JLabel("To hit value");
			JLabel rangedAttackQuantLabel= new JLabel("How many shots?");
			JLabel rangedHitQuantLabel= new JLabel("Number of Hits");
			shooterNum=new JTextField("");
			rangedBS=new JTextField("");
			shooterNum.addActionListener(this);
			rangedBS.addActionListener(this);
			rangedToHit=new JLabel("");
			rangedNumOfHit= new JLabel("");
			GridLayout newLayout= new GridLayout(2,4);
			rangedPanel.setLayout(newLayout);
			rangedPanel.getContentPane().add(bsLabel, BorderLayout.CENTER);
			rangedPanel.getContentPane().add(rangedHitLabel, BorderLayout.CENTER);
			rangedPanel.getContentPane().add(rangedAttackQuantLabel, BorderLayout.CENTER);
			rangedPanel.getContentPane().add(rangedHitQuantLabel, BorderLayout.CENTER);
			rangedPanel.getContentPane().add(rangedBS, BorderLayout.CENTER);
			rangedPanel.getContentPane().add(rangedToHit, BorderLayout.CENTER);
			rangedPanel.getContentPane().add(shooterNum, BorderLayout.CENTER);
			rangedPanel.getContentPane().add(rangedNumOfHit, BorderLayout.CENTER);
			rangedPanel.pack();
			rangedPanel.setVisible(true);
		}
		else if(source==rangedBS){
			shooterBS=Integer.parseInt(rangedBS.getText());
			s=""+rangedHitChart[1][shooterBS-1];
			rangedToHit.setText(s);
		}
		else if(source==shooterNum){
			if(shooterBS>0){
				for(int i=0;i<Integer.parseInt(shooterNum.getText());i++){
					int diceRoll=rollDice();
					System.out.println(""+diceRoll);
					if(diceRoll>=rangedHitChart[1][shooterBS-1]){
						hitQuantity++;
					}
				}
				s=""+hitQuantity;
				rangedNumOfHit.setText(s);
				hitQuantity=0;
			}
		}
		else if(source==wound){
			JFrame woundPanel = new JFrame("Wound Panel");
			woundPanel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			
			JLabel strengthLabel=new JLabel("Strength");
			JLabel toughnessLabel= new JLabel("Toughness");
			JLabel toWoundLabel= new JLabel("to-wound Number");
			JLabel woundQuantityLabel= new JLabel("Number of Attacks");
			JLabel woundResultLabel= new JLabel("Number of Wounds");
			
			
			toWoundNum= new JLabel("");
			woundResults= new JLabel("");
			strengthInput= new JTextField();
			toughnessInput= new JTextField();
			woundQuantityInput= new JTextField();
			
			
			strengthInput.addActionListener(this);
			toughnessInput.addActionListener(this);
			woundQuantityInput.addActionListener(this);
			
			
			toWoundNum.setHorizontalAlignment(JLabel.CENTER);
			toWoundLabel.setHorizontalAlignment(JLabel.CENTER);
			
			GridLayout newLayout= new GridLayout(2,4);
			woundPanel.setLayout(newLayout);
			woundPanel.getContentPane().add(strengthLabel, BorderLayout.CENTER);
			woundPanel.getContentPane().add(toughnessLabel, BorderLayout.CENTER);
			woundPanel.getContentPane().add(toWoundLabel, BorderLayout.CENTER);
			woundPanel.getContentPane().add(woundQuantityLabel, BorderLayout.CENTER);
			woundPanel.getContentPane().add(woundResultLabel, BorderLayout.CENTER);
			woundPanel.getContentPane().add(strengthInput, BorderLayout.CENTER);
			woundPanel.getContentPane().add(toughnessInput, BorderLayout.CENTER);
			woundPanel.getContentPane().add(toWoundNum, BorderLayout.CENTER);
			woundPanel.getContentPane().add(woundQuantityInput, BorderLayout.CENTER);
			woundPanel.getContentPane().add(woundResults, BorderLayout.CENTER);
			woundPanel.pack();
			woundPanel.setVisible(true);
			toWoundNum.setText(s);
		}
		else if(source==strengthInput){	
			strength=Integer.parseInt(strengthInput.getText());
			if(toughness>=1&&strength>=1){
				s=s+assaultHitChart[toughness-1][strength-1];	
			}
			else
				s=s+0;
			toWoundNum.setText(s);
		}
		else if(source==toughnessInput){	
			toughness=Integer.parseInt(toughnessInput.getText());
			if(toughness>=1&&strength>=1){
				s=s+assaultHitChart[toughness-1][strength-1];	
				System.out.println("strength is "+strength+" toughness is "+toughness);
			}
			else
				s=s+0;
			toWoundNum.setText(s);
		}
		else if(source==woundQuantityInput){
			if(toughness>=1&&strength>=1){
				s=s+assaultHitChart[toughness-1][strength-1];
				for(int i=0;i<Integer.parseInt(woundQuantityInput.getText());i++){
					int diceRoll=rollDice();
					System.out.println(""+diceRoll);
					if(diceRoll>=woundChart[strength-1][toughness-1]){
						hitQuantity++;
					}
				}
				s=""+hitQuantity;
				woundResults.setText(s);
				hitQuantity=0;
			}
		}
		else if(source==saves){
			JFrame savesPanel= new JFrame("Ranged Combat Time");
			savesPanel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			JLabel saveLabel= new JLabel("What is your save?");
			JLabel saveQuantityLabel= new JLabel("How many saves?");
			JLabel saveFailLabel= new JLabel("Number of casualties");
			JLabel saveSucceedLabel= new JLabel("Number of successes");
			
			
			saveValue=new JTextField("");
			saveQuantity=new JTextField("");
			savePass=new JLabel("");
			saveFail= new JLabel("");
			
			saveValue.addActionListener(this);
			saveQuantity.addActionListener(this);
			
			GridLayout newLayout= new GridLayout(2,4);
			savesPanel.setLayout(newLayout);
			savesPanel.getContentPane().add(saveLabel, BorderLayout.CENTER);
			savesPanel.getContentPane().add(saveQuantityLabel, BorderLayout.CENTER);
			savesPanel.getContentPane().add(saveFailLabel, BorderLayout.CENTER);
			savesPanel.getContentPane().add(saveSucceedLabel, BorderLayout.CENTER);
			savesPanel.getContentPane().add(saveValue, BorderLayout.CENTER);
			savesPanel.getContentPane().add(saveQuantity, BorderLayout.CENTER);
			savesPanel.getContentPane().add(saveFail, BorderLayout.CENTER);
			savesPanel.getContentPane().add(savePass, BorderLayout.CENTER);
			savesPanel.pack();
			savesPanel.setVisible(true);
		}
		
		else if(source==saveValue){
			saveTarget=Integer.parseInt(saveValue.getText());
		}
		else if(source==saveQuantity){
			numOfSaves=Integer.parseInt(saveQuantity.getText());
			if(numOfSaves>=1&&saveTarget>=1){
				for(int i=0;i<Integer.parseInt(saveQuantity.getText());i++){
					int diceRoll=rollDice();
					System.out.println(""+diceRoll+" "+saveTarget);
					if(diceRoll>=saveTarget){
						passQuantity++;
					}
				}
				s=""+passQuantity;
				savePass.setText(s);
				System.out.println(passQuantity);
				s=""+((numOfSaves-passQuantity));
				saveFail.setText(s);
				System.out.println(numOfSaves);
				passQuantity=0;
			}
			else
				s=s+0;
		}
	}
	
	
	public int rollDice(){
		double seed=Math.random();
		if(seed<.166){
			return 1;
		}
		else if(seed>.166&&seed<.332){
			return 2;
		}
		else if(seed>.332&&seed<.5){
			return 3;
		}
		else if(seed>.5&&seed<.666){
			return 4;
		}
		else if(seed>.666&&seed<.835){
			return 5;
		}
		else
			return 6;
	}
	
}

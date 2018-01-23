import java.awt.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.Random;
import java.io.*;
import java.io.IOException;
import java.io.File;

public class LeFinal {
	
    // list of words
	public static String[] words = new String[]{"time", "year", "people", "way", "day", "man", "thing", "woman", "life", "child", "world", "school", "state", "family", "student", "group", "country", "crowd", 
			"problem", "hand", "part", "place", "week", "company", "system", "program", "question", "work", "number", "night", "point", "home", "water", "room", "mom", "area", 
			"money", "fact", "month", "lot", "right", "study", "book", "eye", "job", "word", "side", "kind", "head", "house"};
	
    // scramble the array containing the words questions do not appear in the same order
	public static void ScrambleArray(String[] array){
		
		Random rand  = new Random();
		
		for (int i = 0; i < array.length; i++){
			int newpos = rand.nextInt(array.length);
			String temp = array[i];
			array[i] = array[newpos];
			array[newpos] = temp;
		}
		
	}
	
    // scramble the letters of each word to create the question
	public static String Shuffle(String text){
		
		char[] letters = text.toCharArray();
		
		Random rand  = new Random();
		
		for (int i = 0; i < letters.length; i++){
			int newpos = rand.nextInt(letters.length);
			char temp = letters[i];
			letters[i] = letters[newpos];
			letters[newpos] = temp;
		}
		
		return new String(letters);
	}
	
    // welcome info panel
	public static void Welcome(){
		
	String name = JOptionPane.showInputDialog(null,"Hello! What's your name?","Welcome!",JOptionPane.QUESTION_MESSAGE);
			
			int a = JOptionPane.showConfirmDialog(null,"This is a word scramble game.\n"
					+ "You just have to figure out what word is shown from the scrambled letters.\n"
					+ "Want to give it a go?","How to play",JOptionPane.YES_NO_OPTION);
			
			if (a == 0){
				String ex1 = JOptionPane.showInputDialog(null,"What's this word? \n"
						+ "ATC","Example",JOptionPane.QUESTION_MESSAGE);
				
				if (ex1.equalsIgnoreCase("cat")){
					JOptionPane.showMessageDialog(null,"YAY "+name+" ! YOU DID IT!","WOO",JOptionPane.INFORMATION_MESSAGE);
				}
				
				else
					JOptionPane.showMessageDialog(null, "Nice try! The answer was cat.");
			}
			
			if (a != 0){
				JOptionPane.showMessageDialog(null,"Goodbye!");
				System.exit(0);
			}
			
}
	
    // exit info display
	public static void ExitMsg(){
		
		JOptionPane.showMessageDialog(null, "You are going to exit.");
		JOptionPane.showMessageDialog(null, "Thanks for playing!");
		System.exit(0);
		
	}
	
    // pop-up panel with list of words to provide hints
	public static void WordList() {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		JPanel centerpanel = new JPanel();
		centerpanel.setLayout(new GridLayout(1,5));
		
		JScrollPane scrollpane1;
		String sortedwords1[] = {"day", "eye", "job", "lot", 
				"man", "mom", "way", "area", "book", 
				"fact"};
		JList list1 = new JList (sortedwords1);
		scrollpane1 = new JScrollPane(list1);
		centerpanel.add(scrollpane1);
		
		JScrollPane scrollpane2;
		String sortedwords2[] = {"hand", "head", "home", "house",
				"kind", "life", "part", "room", "side", "time"};
		JList list2 = new JList (sortedwords2);
		scrollpane2 = new JScrollPane(list2);
		centerpanel.add(scrollpane2);
		
		JScrollPane scrollpane3;
		String sortedwords3[] = {"week", "word", "work", "year", "child", 
				"crowd", "group", "money", "month", "night"};
		JList list3 = new JList (sortedwords3);
		scrollpane3 = new JScrollPane(list3);
		centerpanel.add(scrollpane3);
		
		JScrollPane scrollpane4;
		String sortedwords4[] = {"place", "point", "right", "state", 
				"study", "thing", "water", "woman", "world", "family"};
		JList list4 = new JList (sortedwords4);
		scrollpane4 = new JScrollPane(list4);
		centerpanel.add(scrollpane4);
		
		JScrollPane scrollpane5;
		String sortedwords5[] = {"number", "people", "school", "system", 
				"company", "country", "problem", "program", "student", "question"};
		JList list5 = new JList (sortedwords5);
		scrollpane5 = new JScrollPane(list5);
		centerpanel.add(scrollpane5);
		
		frame.add(new JLabel("Here are the list of words. Use this if you get stuck. \n"
				+ "The list is sorted by word length."),BorderLayout.NORTH);
		frame.add(centerpanel,BorderLayout.CENTER);
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {

		int highscore = 0;
        
        // if highscore file is present, read the highscore
		if (new File ("highscore.txt").isFile()){

			String filename = "highscore.txt";
			try {
				FileReader filereader = new FileReader(filename);
				BufferedReader bufferedreader = new BufferedReader (filereader);
				highscore = Integer.parseInt(bufferedreader.readLine());
				bufferedreader.close();
			} 
			catch(FileNotFoundException ex){
				JOptionPane.showMessageDialog(null, 
				"Cannot open highscore file.", 
				"Oops!", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			catch(IOException ex){
				JOptionPane.showMessageDialog(null, 
				"Error reading file.", 
				"Oops!", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}
		// if highscore file not present, create file
		else {
			String filename = "highscore.txt";
			try {
				FileWriter filewriter = new FileWriter(filename);
				BufferedWriter bufferedwriter = new BufferedWriter (filewriter);
				bufferedwriter.write("0");
				bufferedwriter.close();
			} 
			catch(IOException ex){
				JOptionPane.showMessageDialog(null, 
				"Error creating highscore file.", 
				"Oops!", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}

		Welcome();
		
		ScrambleArray(words);
		
		JOptionPane.showMessageDialog(null, "Let's start!");
		
		WordList();
        
        int correct = 0;
        int skipped = 0;
        int wrong = 0;
        
		for (int i = 0; i < words.length; i++){
			
			String scrambled = Shuffle(words[i]);
			
			String ans = JOptionPane.showInputDialog(null, "What's this word? Leave it blank and press OK to skip.\n"
					+ scrambled,"Question #"+(i+1),JOptionPane.QUESTION_MESSAGE);
			
			if (ans.equalsIgnoreCase(words[i])){
				int a = JOptionPane.showConfirmDialog(null,"That is correct! Continue?", "Good Job!",
						JOptionPane.YES_NO_OPTION);
				correct++;
				if (a == JOptionPane.NO_OPTION){
					ExitMsg();
				}
			}
			
			if (!(ans.equalsIgnoreCase(words[i])) && ans.length() > 0){
				int a = JOptionPane.showConfirmDialog(null,"The correct answer was: "+words[i]+". Continue?", "Oops!",
						JOptionPane.YES_NO_OPTION);
				wrong++;
				if (a == JOptionPane.NO_OPTION){
					ExitMsg();
				}
			}	
		}

		skipped = 50 - correct - wrong;

		String correctS = Integer.toString(correct);
		String wrongS = Integer.toString(wrong);
		String skippedS = Integer.toString(skipped);
        
        // update highscore if new highscore has been set
		if (correct > highscore) {
			String filename = "highscore.txt";
			highscore = correct;
			try {
				FileWriter filewriter = new FileWriter(filename);
				BufferedWriter bufferedwriter = new BufferedWriter (filewriter);
				bufferedwriter.write(correctS);
				bufferedwriter.close();
			} 
			catch(IOException ex){
				JOptionPane.showMessageDialog(null, 
				"Error creating highscore file.", 
				"Oops!", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}

		String highscoreS = Integer.toString(highscore);

		
		JOptionPane.showMessageDialog(null, 
			"You've reached the end! Thanks for playing! You got "+correctS+" correct, "+wrongS+" incorrect, and skipped "+skippedS+". The high score is "+highscoreS+".", 
			"The end.", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
}

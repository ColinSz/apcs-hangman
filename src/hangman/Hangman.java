/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import javax.swing.JOptionPane;
import java.util.Random;

/**
 *
 * @author 009224
 */
public class Hangman extends BasicGame {
    
    private char[] lettersActual = {'_', '_', '_', '_', '_'};
    private boolean[] letters = {false, false, false, false, false};
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private String choseWord = "rhymeloirelunarmailsbasisfilchwieldvisitleadsbrats"
            + "signsdietswavesdecoyraspybesetethosatomsproofswill"
            + "embedplopsadmitlunchwaifsglaredronewimpylionspound"
            + "unlitbosonswinesaltsgullsleechhexedgaudyodiumpixie"
            + "knockfleaselfinshoalsibylteaseepochmowertestydusty";
    private String word = "a";
    private int guessesLeft = 5;
    
    
    public Hangman(){ 
        //reinitializeGame();
        JOptionPane.showMessageDialog(null, "Welcome to Hangman! Are you ready to play?");
    }
    
    @Override
    public void playManyGames(){   
        do {    
            playOneGame();
        }while (JOptionPane.showConfirmDialog (null, "Do you want to play Hangman again?")
                         == JOptionPane.YES_OPTION);
     }  //======================
    
    @Override
     public void playOneGame(){    
          reinitializeGame();
          while (shouldContinue()) {    
               updateStatus(askUsersToGuess());
          }
          showFinalStatus(guessesLeft != 0);
     }  //======================

    public void reinitializeGame(){
        Random myRandom = new Random();
        int letterStart = myRandom.nextInt(50)*5;
        System.out.println("" + letterStart);
        word = choseWord.substring(letterStart, letterStart+5);
        System.out.println("reinitializing game");
        for(boolean change: letters)
            change = false;
        lettersActual[0] = '_';
        lettersActual[1] = '_';
        lettersActual[2] = '_';
        lettersActual[3] = '_';
        lettersActual[4] = '_';
        guessesLeft = 5;
        alphabet = "abcdefghijklmnopqrstuvwxyz";
    }
    
     public char askUsersToGuess() {    
         char letterGuessed = ' ';
         do{
             try{
                letterGuessed = JOptionPane.showInputDialog
                    ("Guess the secret word: " + lettersActual[0] + " " + 
                    lettersActual[1] + " " + lettersActual[2] + " " + 
                    lettersActual[3] + " " + lettersActual[4] + 
                    "\nDont guess something you've already guessed!\nHere's what's available!     " + alphabet).charAt(0);
                        
             }
             catch(NullPointerException e){
               System.exit(0);
             }
             catch(StringIndexOutOfBoundsException e){
                System.out.println("threw that indexoutofboundsexception");
                JOptionPane.showMessageDialog(null, "Put in something real!\nYou have " + guessesLeft + " guesses left!");
                letterGuessed = ' ';
             }
             System.out.println(letterGuessedValid(letterGuessed) + "");
             if((letterGuessed != ' ') && letterGuessedValid(letterGuessed))
                 JOptionPane.showMessageDialog(null, "You already used that letter, " + letterGuessed
                         + ". Try one of these: " + alphabet);
         } while((letterGuessed == ' ') && letterGuessedValid(letterGuessed));
         
         System.out.println("Exited guessing loop");
         
         return letterGuessed;
     }  //======================

     private boolean letterGuessedValid(char checkThis){
         return (!alphabet.contains(String.valueOf(checkThis)));
     }
     
     public void updateStatus(char letterGuessed){
         int index = 0;
         System.out.println("letter         " + letterGuessed);
         while(index != -1) {
            index = alphabet.indexOf(String.valueOf(letterGuessed));
            //System.out.println(index);
            if(index != -1){
                alphabet = alphabet.substring(0, index) + alphabet.substring(index+1, alphabet.length());
                System.out.println(alphabet);
            }
            if((word.indexOf(String.valueOf(letterGuessed))) != -1){
                String restOfWord = word;
                String restOfWordMissing = "";
                int spot = 0;
                while(spot < restOfWord.length()) {
                    
                    spot = restOfWord.indexOf(String.valueOf(letterGuessed)); //+ restOfWordMissing.length();
                    if(spot >= 0)
                       spot += restOfWordMissing.length(); 
                    //System.out.println("spot                " + spot);
                    restOfWord = word.substring(spot+1, word.length());
                    restOfWordMissing = word.substring(0, spot+1);
                    //System.out.println("restOfWord          " + restOfWord);
                    //System.out.println("restOfWordMissing   " + restOfWordMissing);
                    if(spot < 0)
                        spot = restOfWord.length() + 2;
                    else {
                        letters[spot] = true;
                        lettersActual[spot] = word.charAt(spot);
                    }
                    //System.out.println(spot);
                }
            }
            else {
                if(letterGuessed != ' ') 
                    guessesLeft--;
                    JOptionPane.showMessageDialog(null, "Nope, sorry!\nThe letter you guessed, " + letterGuessed + ", doesn't belong."
                         + "\nHere's the letters you have left: " + alphabet + "."
                        + "\nYou have " + guessesLeft + " guesses left! Good luck!");
                    index = -1;
            }
         }
     }
     
     @Override
     public boolean shouldContinue(){
         return !((guessesLeft == 0)||(letters[0] && letters[1] && letters[2] && letters[3] && letters[4]));
     }  //======================

     public void showFinalStatus(boolean success){    
         if(success)
            JOptionPane.showMessageDialog (null,
                         "That was right.  \nCongratulations! The word was " + word + ".");
         else
             JOptionPane.showMessageDialog(null, "You ran out of guesses! The word was " + word + ".");
     }  //======================
    
}


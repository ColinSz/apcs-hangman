package hangman; 

import javax.swing.JOptionPane;

public class BasicGame extends Object
{
     private String itsSecretWord = "duck";
     private String itsUsersWord = "none";


     public void playManyGames()  
     {    do
          {    playOneGame();
          }while (JOptionPane.showConfirmDialog (null, "again?")
                         == JOptionPane.YES_OPTION);
     }  //======================


     public void playOneGame()  
     {    askUsersFirstChoice();
          while (shouldContinue())
          {    showUpdatedStatus();
               askUsersNextChoice();
          }
          showFinalStatus();
     }  //======================


     public void askUsersFirstChoice()  
     {    itsUsersWord = JOptionPane.showInputDialog
                         ("Guess the secret word:");
     }  //======================


     public void askUsersNextChoice()  
     {    askUsersFirstChoice(); // no need to write the coding again
     }  //======================


     public boolean shouldContinue()  
     {    return ! itsSecretWord.equals (itsUsersWord);
     }  //======================


     public void showUpdatedStatus()  
     {    JOptionPane.showMessageDialog (null,
                         "That was wrong.  Hint:  It quacks.");
     }  //======================


     public void showFinalStatus()  
     {    JOptionPane.showMessageDialog (null,
                         "That was right.  \nCongratulations.");
     }  //======================
}

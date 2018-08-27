import java.io.FileInputStream;
import javazoom.jl.player.*;

public class MusicPlayer extends Thread{         
   String currentSong;
   boolean open;
    public  void run(){
        while(open==false){
        try {
         System.out.println(currentSong);
         FileInputStream file=new FileInputStream("C:/Users/Dani/Desktop/Apps/music/net/"+currentSong); //selects a music file
         Player player=new Player(file);                                                                
         player.play();                                                                                 //starts playing it
         open=true;
	}catch(Exception e) {
	 System.out.println("Couldn't open file: "+e);
	}
    }      
            }
        
    }

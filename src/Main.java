import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.DefaultListModel;
import java.util.Random;

public class Main extends javax.swing.JFrame implements MouseListener {

    public static String songChosen;
    
    int arrayCapacity=5;    //dynamic array
    int songCount=0;        //number of songs in the folder
    int songNum=0;          //the number of the song currently playing
    int randomSongCount=0;  //chooses a random song
    
    MusicPlayer[] song=new MusicPlayer[arrayCapacity];      //the array which holds references to the songs
    DefaultListModel player=new DefaultListModel();         //creating the format for the playlist
    public Main() {
        initComponents();                                                            //initialises the swing components
    
        random.setEnabled(false);                                                    //doesn't enable the RANDOM button until a song is selected
        File folder = new File("C:\\Users\\Dani\\Desktop\\Apps\\music\\net");        //!!!CHOOSE THE FOLDER WHERE YOU MUSIC FILES ARE!!! -points to the folder where the music files are
        File[] listOfFiles = folder.listFiles();                                     //
        songCount=listOfFiles.length;                                                //
        for (int i = 0; i < listOfFiles.length; i++) {                               //copies all the names of the music files into the playlist
           if (listOfFiles[i].isFile()) {                                            //
              player.addElement(listOfFiles[i].getName());                           //
                 }                                                                   //
          }
        
        playList.setModel(player);                                                   //finally creates the playlist
        panel2.setVisible(true);
        playList.addMouseListener(this);                                             //enables the playlist to respond to mouse clicks                   
       
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playList = new javax.swing.JList<>();
        random = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 500));
        setPreferredSize(new java.awt.Dimension(600, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        panel2.setPreferredSize(new java.awt.Dimension(600, 500));
        panel2.setLayout(null);

        playList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(playList);

        panel2.add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 600, 310);

        random.setText("RANDOM");
        random.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomActionPerformed(evt);
            }
        });
        panel2.add(random);
        random.setBounds(240, 360, 110, 30);

        getContentPane().add(panel2);
        panel2.setBounds(0, 0, 600, 500);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void randomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomActionPerformed
    Random dice=new Random();
    randomSongCount=dice.nextInt(songCount);                               //chooses a random song number out of the available current songs in the folder
    songChosen=player.elementAt(randomSongCount).toString();               //assigns the random chosen song to the songChosen variable
    playList.setSelectedIndex(randomSongCount);                            //highlights the randomly selected song
    System.out.println(songChosen);                                        //
    song[songNum].stop();                                                  //stop the last song thread
    enlargeArray();                                                        //enlarge array if needed
    songNum++;                                                             //
    song[songNum]=new MusicPlayer();                                       //create a new song thread
    song[songNum].currentSong=songChosen;                                  //change the song playing
    song[songNum].start();                                                 //start the selected thread
    }//GEN-LAST:event_randomActionPerformed
    
    public void enlargeArray(){                                            //a method that checks if the array needs enlargement
        if(songNum==arrayCapacity-2){                                      //checks if the current song gets close to the limits of the array
            arrayCapacity=arrayCapacity*2;                                 //
                                                                           //
            MusicPlayer[] moreSongs=new MusicPlayer[arrayCapacity];        //if it does, change the current array to a new one twice the size
            System.arraycopy(song, 0, moreSongs, 0, song.length);          //
            song=moreSongs;                                                //
            System.out.println("Array enlarged");                          //
        }
    }
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panel2;
    public javax.swing.JList<String> playList;
    private javax.swing.JButton random;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mousePressed(MouseEvent me) {
        try{
       song[songNum].stop();                                    //
       enlargeArray();                                          //when the mouse is pressed stop the current thread,
       songNum++;                                               //check if the size of the array is ok
        }catch(Exception e){                                    //
            System.out.println("Couldn't stop thread: "+e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
       
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        song[songNum]=new MusicPlayer();    
        songChosen=playList.getSelectedValue();    //
        System.out.println("Song chosen");         //Start playing the selected song
        System.out.println(songChosen);            //
        song[songNum].currentSong=songChosen;      //
        song[songNum].start();
        random.setEnabled(true);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

}

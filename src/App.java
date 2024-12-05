
import dominio.Album;
import dominio.Artista;
import dominio.Musica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class App {

    public static int indiceMusicaAtual = 0;
    public static void main(String[] args) throws Exception {

        Musica musica1 = new Musica();
        // Playlist.adicionarMusica(musica1);
        musica1.setNome("Californication");
        musica1.setDuracao(120);
        musica1.setArquivoAudio("aula-poo-playmusic/assets/Red-Hot-Chili-Peppers-Californication.wav");
        musica1.setGenero("Rock");

        Musica musica2 = new Musica();
        // Playlist.adicionarMusica(musica2);
        musica2.setNome("Otherside");
        musica2.setDuracao(120);
        musica2.setArquivoAudio("aula-poo-playmusic/assets/Red-Hot-Chili-Peppers-Otherside.wav");
        musica2.setGenero("Rock");

        Musica musica3 = new Musica();
        // Playlist.adicionarMusica(musica3);
        musica3.setNome("In the end");
        musica3.setDuracao(120);
        musica3.setArquivoAudio("aula-poo-playmusic/assets/In-The-End-Linkin-Park.wav");
        musica3.setGenero("Rock");

        Album album1 = new Album();
        album1.setNome("Primeiro album");
        album1.setAno(2000);
        album1.addMusica(musica1);
        album1.addMusica(musica2);
        album1.addMusica(musica3);

        Artista redHot = new Artista();
        redHot.setNome("Red Hot Chili Peppers");
        redHot.addAlbum(album1);

        System.out.println("Abrindo o PlayMusic");
        redHot.getAlbuns().get(0).getMusicas().get(0).getArquivoAudio();

        AudioPlayer player = new AudioPlayer();
        player.loadAudio(redHot.getAlbuns().get(0).getMusicas().get(0).getArquivoAudio()); // Carregue o áudio ao
                                                                                           // iniciar

        // Cria o botão Play/Stop e configura sua ação
        JButton playStopButton = new JButton("Play");

        JButton previousButton = new JButton("Previous");

        JButton nextButton = new JButton("Next");
        
        playStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!player.isPlaying) {
                    player.playAudio();
                    playStopButton.setText("Stop");
                } else {
                    player.stopAudio();
                    playStopButton.setText("Play");
                }
            }
        });


        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                player.stopAudio();

                if (indiceMusicaAtual > 0) {

                    indiceMusicaAtual --;

                } else {
                    indiceMusicaAtual = redHot.getAlbuns().get(0).getMusicas().size() - 1;
                }

                Musica previousMusica = redHot.getAlbuns().get(0).getMusicas().get(indiceMusicaAtual);
                player.loadAudio(previousMusica.getArquivoAudio());
                player.playAudio();
            }
        });


        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                player.stopAudio();

        
                if (indiceMusicaAtual < redHot.getAlbuns().get(0).getMusicas().size() - 1) {

                    indiceMusicaAtual ++;

                } else {
                    indiceMusicaAtual = 0;
                    
                }


                Musica nextMusica = redHot.getAlbuns().get(0).getMusicas().get(indiceMusicaAtual);
                player.loadAudio(nextMusica.getArquivoAudio());
                player.playAudio();
            }
        });

        ImageIcon icon = new ImageIcon("aula-poo-playmusic/assets/music.png");
        // Exibe um JOptionPane com o botão Play/Stop
        JOptionPane.showOptionDialog(
                null,
                redHot.getAlbuns().get(0).getMusicas().get(0).getNome(),
                "PlayMusic",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                icon,
                new Object[] { previousButton, playStopButton, nextButton}, playStopButton);

        // Fecha o clip de áudio ao encerrar o programa
        if (player.audioClip != null) {
            player.audioClip.close();
        }

    }
}

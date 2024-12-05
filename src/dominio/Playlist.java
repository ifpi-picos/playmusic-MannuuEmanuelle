package dominio;

import java.util.List;

public class Playlist {
  private String nome;
  private static List<Musica> musicas;


    public int indexMusicaAtual = 0;
  
    public String getNome() {
      return nome;
    }
  
    
    public List<Musica> getMusicas() {
      return musicas;
    }
    public void setMusicas(List<Musica> musicas) {
      this.musicas = musicas;
    }
  
    public static void adicionarAlbum(Album album){
      musicas.addAll(album.getMusicas());
    }

    public static void adicionarMusica(Musica musica){
      musicas.add(musica);
  }

    public static void removerMusica(Musica musica){
      musicas.remove(musica);
    }

}

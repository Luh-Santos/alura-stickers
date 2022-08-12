import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // ctrl + . para criar as variáveis automáticas
        // shift + alt + o para importar tudo

        // Fazer uma conexão HTTP

        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        // String url = "https://api.nasa.gov/planetary/apod?api_key=MfEoQeV0j3b6HDj0kMbMOYanJkliH5BQ9tmJp0NE&start_date=2022-06-12&end_date=2022-06-14";


        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // Exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);


        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.print("\n");
        }
        // System.out.println("\u001b[32m Alura + Java\u001b[m");
    }
}

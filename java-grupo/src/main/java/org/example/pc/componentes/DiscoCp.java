package org.example.pc.componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import org.example.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DiscoCp extends Componente {

    private Integer idDadoFixo;

    private Integer fkTipoComponente;
    private String nomeCampo;
    private String valorCampo;
    private String descricao;


    public DiscoCp(Integer fkMaquina) {
        super(fkMaquina);
    }

    public DiscoCp(Integer idDadoFixo, Integer fkMaquina, Integer fkTipoComponente, String nomeCampo, String valorCampo, String descricao) {
        super(fkMaquina);
        this.idDadoFixo = idDadoFixo;
        this.fkTipoComponente =  fkTipoComponente;
        this.nomeCampo = nomeCampo;
        this.valorCampo =  valorCampo;
        this.descricao =  descricao;
    }

    @Override
    public void buscarInfosFixos() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        Integer qtdDiscosDisco = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();


        String queryDisco2 = """
                     INSERT INTO dadosFixos VALUES
                    (null, %d, 5, 'Quantidade de disco no computador', '%s', 'Quantidade de disco no computador');
                """ .formatted(
                fkMaquina,
                qtdDiscosDisco);

        con.executarQuery(queryDisco2);



        for (int i = 0; i < qtdDiscosDisco; i++) {


            //            DISCO
            String nomeDisco = looca.getGrupoDeDiscos().getDiscos().get(i).getNome();
            Long tamanhoDisco = (looca.getGrupoDeDiscos().getDiscos().get(i).getTamanho() / 1000000000);

            String queryDisco = """
                        INSERT INTO dadosFixos VALUES
                                                (null, %d, 5, 'Nome do disco', '%s', 'Nome do disco'),
                                                (null, %d, 5, 'tamanho do disco', '%s', 'tamanho do disco')
                    """.formatted(
                    fkMaquina,
                    nomeDisco,
                    fkMaquina,
                    tamanhoDisco
            );

            con.executarQuery(queryDisco);
        }



    }

    @Override
    public void buscarInfosVariaveis() {


        try {
            Conexao conexao = new Conexao();
            JdbcTemplate con = conexao.getConexaoDoBanco();
            String queryFk = """
                    select * from dadosFixos where fkMaquina = %d and fkTipoComponente = 5 and nomeCampo = 'Nome do disco'""".formatted(fkMaquina);
            System.out.println(queryFk);
            con.query(queryFk, (resultSet) -> {
                String fk2 = resultSet.getString("idDadosFixos");
                System.out.println(fk2);
                Looca looca = new Looca();

                Long leiturasDisco = (looca.getGrupoDeDiscos().getDiscos().get(0).getLeituras());
                Long escritarDisco = (looca.getGrupoDeDiscos().getDiscos().get(0).getEscritas());

                var queryDisco = """
                            iNSERT INTO dadosTempoReal VALUES  (null, %s, %d, 5, current_timestamp(),'Leituras', '%s'),
                                                               (null, %s, %d, 5, current_timestamp(),'Leituras', '%s');
                                   """.formatted(
                        fk2,
                        fkMaquina,
                        leiturasDisco,
                        fk2,
                        fkMaquina,
                        escritarDisco
                );
//                System.out.println(queryDisco);
                conexao.executarQuery(queryDisco);



                while (resultSet.next()){
                    String fk = resultSet.getString("valorCampo");

                    for (Disco discoAtual : looca.getGrupoDeDiscos().getDiscos()){
                        if (discoAtual.getNome().equals(fk)){

                            var queryDisco2 = """
                                    iNSERT INTO dadosTempoReal VALUES  (null, %d, %d, 5, current_timestamp(),'Leituras', '%s'),
                                                                       (null, %d, %d, 5, current_timestamp(),'Leituras', '%s');
                                           """.formatted(
                                    fk,
                                    fkMaquina,
                                    leiturasDisco,
                                    fk,
                                    fkMaquina,
                                    escritarDisco
                            );
                            conexao.executarQuery(queryDisco2);
                            break;
                        }
                    };
                }


            });

        }catch (Exception erro){
            System.out.println(erro);
        }



    }

    @Override
    public void atualizarFixos() {
        Looca looca = new Looca();
        Conexao con = new Conexao();

        Integer qtdDiscosDisco = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();
        for (int i = 0; i < qtdDiscosDisco; i++) {
            String nomeDisco = looca.getGrupoDeDiscos().getDiscos().get(i).getNome();
            Long tamanhoDisco = (looca.getGrupoDeDiscos().getDiscos().get(i).getTamanho() / 1000000000);

            String sql6 = """
                
                UPDATE dadosFixos SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'tamanho do disco';
                """.formatted(
                    tamanhoDisco,
                    fkMaquina,
                    5);

            con.executarQuery(sql6);

            String sql7 = """
                
                UPDATE dadosFixos SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Nome do disco';
                """.formatted(
                    nomeDisco,
                    fkMaquina,
                    5);

            con.executarQuery(sql7);
        }

        String sql8 = """
                
                UPDATE dadosFixos SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Quantidade de disco no computador';
                """.formatted(
                qtdDiscosDisco,
                fkMaquina,
                5);

        con.executarQuery(sql8);


    }
}
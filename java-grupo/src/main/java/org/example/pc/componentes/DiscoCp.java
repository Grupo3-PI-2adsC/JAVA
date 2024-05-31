package org.example.pc.componentes;

import com.github.britooo.looca.api.core.Looca;
import org.example.Conexao;

public class DiscoCp extends Componente {
    public DiscoCp(Integer fkMaquina) {
        super(fkMaquina);
    }

    @Override
    public void buscarInfosFixos() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        Integer qtdDiscosDisco = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();


        String queryDisco2 = """
                     INSERT INTO componentes VALUES
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
                        INSERT INTO componentes VALUES
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

        Looca looca = new Looca();
        Conexao con = new Conexao();

        Long leiturasDisco = (looca.getGrupoDeDiscos().getDiscos().get(0).getLeituras());
        Long escritarDisco = (looca.getGrupoDeDiscos().getDiscos().get(0).getEscritas());

        var queryDisco= """
                    iNSERT INTO dadosTempoReal VALUES  (null, %d, 5, current_timestamp(),'Leituras', '%s'),
                                                       (null, %d, 5, current_timestamp(),'Leituras', '%s');
                           """.formatted(
                fkMaquina,
                leiturasDisco,
                fkMaquina,
                escritarDisco
        );


        con.executarQuery(queryDisco);

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
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'tamanho do disco';
                """.formatted(
                    tamanhoDisco,
                    fkMaquina,
                    5);

            con.executarQuery(sql6);

            String sql7 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Nome do disco';
                """.formatted(
                    nomeDisco,
                    fkMaquina,
                    5);

            con.executarQuery(sql7);
        }

        String sql8 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Quantidade de disco no computador';
                """.formatted(
                qtdDiscosDisco,
                fkMaquina,
                5);

        con.executarQuery(sql8);


    }
}

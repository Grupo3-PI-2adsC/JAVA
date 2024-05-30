package org.example;

import com.github.britooo.looca.api.core.Looca;

public class DiscoCp extends Componente{
    public DiscoCp(Integer fkMaquina) {
        super(fkMaquina);
    }

    @Override
    public void buscarInfosFixos() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        Integer qtdDiscosDisco = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();
        for (int i = 0; i < qtdDiscosDisco; i++) {


            //            DISCO
            String nomeDisco = looca.getGrupoDeDiscos().getDiscos().get(i).getNome();
            Long tamanhoDisco = (looca.getGrupoDeDiscos().getDiscos().get(i).getTamanho() / 1000000000);

            String queryDisco = """
                        INSERT INTO componentes VALUES
                                                (16, %d, 5, 'qtdDiscos', '%s', 'Quantidade de disco no computador'),
                                                (17, %d, 5, 'nome', '%s', 'Nome do disco'),
                                                (18, %d, 5, 'tamanho', '%s', 'tamanho do disco')
                    """.formatted(
                    fkMaquina,
                    qtdDiscosDisco,
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
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'qtdVolumes';
                """.formatted(
                    tamanhoDisco,
                    fkMaquina,
                    5);

            con.executarQuery(sql6);

            String sql7 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'tamanho';
                """.formatted(
                    nomeDisco,
                    fkMaquina,
                    5);

            con.executarQuery(sql7);
        }

        String sql8 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nome';
                """.formatted(
                qtdDiscosDisco,
                fkMaquina,
                5);

        con.executarQuery(sql8);


    }
}

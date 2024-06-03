package org.example.pc.componentes;

import com.github.britooo.looca.api.core.Looca;
import org.example.Conexao;

public class VolumeCp extends Componente {

    public VolumeCp(Integer fkMaquina) {
        super(fkMaquina);
    }

    @Override
    public void buscarInfosFixos() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        Integer qtdVolumesVolume = looca.getGrupoDeDiscos().getQuantidadeDeVolumes();

        String queryVolume2 = """
                            INSERT INTO dadosFixos VALUES
                                (null, %d, 6, 'qtdVolumes', '%s', 'quantidade de volumes no computador');
                """.formatted(
                fkMaquina,
                qtdVolumesVolume);

        con.executarQuery(queryVolume2);

        for (int i = 1; i < qtdVolumesVolume; i++) {

            //            VOLUME
            String nomeVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getNome();
            String UUIDVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getUUID();
            Long totalVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getTotal();
            Long disponivelVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getDisponivel();
            String tipoVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getTipo();

            String queryVolume = """
                            INSERT INTO dadosFixos VALUES
                                                    (null, %d, 6, 'UUID do volume', '%s', 'UUID do volume'),
                                                    (null, %d, 6, 'nome do volume', '%s', 'nome do volume'),
                                                    (null, %d, 6, 'tamanho total do volume', '%s', 'tamanho total do volume'),
                                                    (null, %d, 6, 'tamanho disponivel do volume', '%s', 'tamanho disponivel do volume'),
                                                    (null, %d, 6, 'tipo do volume', '%s', 'tipo do volume')
                        """.formatted(
                    fkMaquina,
                    UUIDVolume,
                    fkMaquina,
                    nomeVolume,
                    fkMaquina,
                    totalVolume,
                    fkMaquina,
                    disponivelVolume,
                    fkMaquina,
                    tipoVolume
            );

            con.executarQuery(queryVolume);
        }
    }

    @Override
    public void buscarInfosVariaveis() {

    }

    @Override
    public void atualizarFixos() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        Integer qtdVolumesVolume = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();

        String sql = """
                                
                UPDATE dadosFixos SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'quantidade de volumes no computador';
                """.formatted(
                qtdVolumesVolume,
                fkMaquina,
                6);
        con.executarQuery(sql);

        for (int i = 1; i < qtdVolumesVolume; i++) {
            String nomeVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getNome();
            String UUIDVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getUUID();
            Long totalVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getTotal();
            Long disponivelVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getDisponivel();
            String tipoVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getTipo();



            String sql1 = """
                                    
                    UPDATE dadosFixos SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'tipo do volume';
                    """.formatted(
                    tipoVolume,
                    fkMaquina,
                    6);
            con.executarQuery(sql1);


            String sql2 = """
                    
                    UPDATE dadosFixos SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'tamanho total do volume';
                    """.formatted(
                    totalVolume,
                    fkMaquina,
                    6);

            con.executarQuery(sql2);

            String sql3 = """
                
                UPDATE dadosFixos SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'tamanho disponivel do volume';
                """.formatted(
                    disponivelVolume,
                    fkMaquina,
                    6);

            con.executarQuery(sql3);

            String sql4 = """
                
                UPDATE dadosFixos SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nome do volume';
                """.formatted(
                    nomeVolume,
                    fkMaquina,
                    6);

            con.executarQuery(sql4);

            String sql5 = """
                
                UPDATE dadosFixos SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'UUID do volume';
                """.formatted(
                    UUIDVolume,
                    fkMaquina,
                    6);

            con.executarQuery(sql5);
        }



    }
}
package org.example;

import com.github.britooo.looca.api.core.Looca;

public class VolumeCp extends Componente{

    public VolumeCp(Integer fkMaquina) {
        super(fkMaquina);
    }

    @Override
    public void buscarInfosFixos() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        Integer qtdVolumesVolume = looca.getGrupoDeDiscos().getQuantidadeDeVolumes();
        for (int i = 1; i < qtdVolumesVolume; i++) {

            //            VOLUME
            String nomeVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getNome();
            String UUIDVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getUUID();
            Long totalVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getTotal();
            Long disponivelVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getDisponivel();
            String tipoVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getTipo();

            String queryVolume = """
                            INSERT INTO componentes VALUES
                                                    (19, %d, 6, 'qtdVolumes', '%s', 'quantidade de volumes no computador'),
                                                    (20, %d, 6, 'UUID', '%s', 'UUID do volume'),
                                                    (21, %d, 6, 'nome', '%s', 'nome do volume'),
                                                    (22, %d, 6, 'total', '%s', 'tamanho total do volume'),
                                                    (23, %d, 6, 'disponivel', '%s', 'tamanho disponivel do volume'),
                                                    (24, %d, 6, 'tipo', '%s', 'tipo do volume')
                        """.formatted(
                    fkMaquina,
                    qtdVolumesVolume,
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
        for (int i = 1; i < qtdVolumesVolume; i++) {
            String nomeVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getNome();
            String UUIDVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getUUID();
            Long totalVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getTotal();
            Long disponivelVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getDisponivel();
            String tipoVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getTipo();

            String sql1 = """
                                    
                    UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'tipo';
                    """.formatted(
                    tipoVolume,
                    fkMaquina,
                    6);
            con.executarQuery(sql1);


            String sql2 = """
                    
                    UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'disponivel';
                    """.formatted(
                    totalVolume,
                    fkMaquina,
                    6);

            con.executarQuery(sql2);

            String sql3 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'total';
                """.formatted(
                    tipoVolume,
                    fkMaquina,
                    6);

            con.executarQuery(sql3);

            String sql4 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nome';
                """.formatted(
                    nomeVolume,
                    fkMaquina,
                    6);

            con.executarQuery(sql4);

            String sql5 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'UUID';
                """.formatted(
                    UUIDVolume,
                    fkMaquina,
                    6);

            con.executarQuery(sql5);
        }



    }
}

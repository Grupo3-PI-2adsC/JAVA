package org.example;

import com.github.britooo.looca.api.core.Looca;

import java.util.List;

public class RedeCp extends Componente{

    public RedeCp(Integer fkMaquina) {
        super(fkMaquina);
    }

    @Override
    public void buscarInfosVariaveis() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        Long emUsoMemoria = (looca.getMemoria().getEmUso() / 1000000000);

        var queryMemoria= """
                    iNSERT INTO dadosTempoReal VALUES  (null, %d, 2, current_timestamp(),'emUso', '%s');
                           """.formatted(
                fkMaquina,
                emUsoMemoria
        );

        con.executarQuery(queryMemoria);

    }

    @Override
    public void buscarInfosFixos() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        Long totalMemoria = (looca.getMemoria().getTotal() / 1000000000);

        String queryMemoria = """
                    INSERT INTO componentes VALUES
                                            (3, %d, 2, 'total', '%s', 'total de memoria do computador')
                """.formatted(
                fkMaquina,
                totalMemoria
        );

        con.executarQuery(queryMemoria);

    }

    @Override
    public void atualizarFixos() {

        Looca looca = new Looca();
        Conexao con =  new Conexao();

        String nomeRede = looca.getRede().getParametros().getNomeDeDominio();
        String nomeExibicaoRede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getNomeExibicao();
        List enderecoIPv4Rede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoIpv4();
        List enderecoIPv6Rede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoIpv6();
        String enderecoMACRede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoMac();
        String hostnameRede = looca.getRede().getParametros().getHostName();
        List servidoresDNSRede = looca.getRede().getParametros().getServidoresDns();



        String sql9 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'qtdDiscos';
                """.formatted(
                servidoresDNSRede,
                fkMaquina,
                4);

        con.executarQuery(sql9);

        String sql10 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'servidoresDNS';
                """.formatted(
                hostnameRede,
                fkMaquina,
                4);

        con.executarQuery(sql10);

        String sql11 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'hostname';
                """.formatted(
                enderecoMACRede,
                fkMaquina,
                4);

        con.executarQuery(sql11);

        String sql12 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'enderecoMAC';
                """.formatted(
                enderecoIPv6Rede,
                fkMaquina,
                4);

        con.executarQuery(sql12);

        String sql13 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'enderecoIPv6';
                """.formatted(
                enderecoIPv4Rede,
                fkMaquina,
                4);

        con.executarQuery(sql13);

        String sql14 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'enderecoIPv4';
                """.formatted(
                nomeExibicaoRede,
                fkMaquina,
                4);

        con.executarQuery(sql14);

        String sql15 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nomeExebicao';
                """.formatted(
                nomeExibicaoRede,
                fkMaquina,
                4);

        con.executarQuery(sql15);

        String sql16 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nome';
                """.formatted(
                nomeRede,
                fkMaquina,
                4);

        con.executarQuery(sql16);

    }


}

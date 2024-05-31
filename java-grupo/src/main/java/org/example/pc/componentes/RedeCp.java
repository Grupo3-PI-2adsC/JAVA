package org.example.pc.componentes;

import com.github.britooo.looca.api.core.Looca;
import org.example.Conexao;

import java.util.List;

public class RedeCp extends Componente {

    public RedeCp(Integer fkMaquina) {
        super(fkMaquina);
    }

    @Override
    public void buscarInfosFixos() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        String nomeRede = looca.getRede().getParametros().getNomeDeDominio();
        String nomeExibicaoRede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getNomeExibicao();
        List enderecoIPv4Rede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoIpv4();
        List enderecoIPv6Rede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoIpv6();
        String enderecoMACRede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoMac();
        String hostnameRede = looca.getRede().getParametros().getHostName();
        List servidoresDNSRede = looca.getRede().getParametros().getServidoresDns();

        String queryRede = """
                    INSERT INTO componentes VALUES
                                            (null, %d, 4, 'Nome da rede', '%s', 'Nome da rede'),
                                            (null, %d, 4, 'Nome de exibição da rede', '%s', 'Nome de exibição da rede'),
                                            (null, %d, 4, 'Endereço IPv4 da rede', '%s', 'Endereço IPv4 da rede'),
                                            (null, %d, 4, 'Endereço IPv6 da rede', '%s', 'Endereço IPv6 da rede'),
                                            (null, %d, 4, 'Endereço MAC da rede', '%s', 'Endereço MAC da rede'),
                                            (null, %d, 4, 'hostname da rede', '%s', 'hostname da rede'),
                                            (null, %d, 4, 'servidores DNS da rede', '%s', 'servidores DNS da rede')
                """.formatted(
                fkMaquina,
                nomeRede,
                fkMaquina,
                nomeExibicaoRede,
                fkMaquina,
                enderecoIPv4Rede,
                fkMaquina,
                enderecoIPv6Rede,
                fkMaquina,
                enderecoMACRede,
                fkMaquina,
                hostnameRede,
                fkMaquina,
                servidoresDNSRede
        );

        con.executarQuery(queryRede);

    }


    @Override
    public void buscarInfosVariaveis() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        Long pacotesRecebidosRede = (looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getPacotesRecebidos());
        Long pacotesEnviadosRede = (looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getPacotesEnviados());

        var queryRede= """
                    iNSERT INTO dadosTempoReal VALUES  (null, %d, 4, current_timestamp(),'Pacotes recebidos', '%s'),
                                                       (null, %d, 4, current_timestamp(),'Pacotes enviados', '%s');
                           """.formatted(
                fkMaquina,
                pacotesRecebidosRede,
                fkMaquina,
                pacotesEnviadosRede
        );
        con.executarQuery(queryRede);

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
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'servidores DNS da rede';
                """.formatted(
                servidoresDNSRede,
                fkMaquina,
                4);

        con.executarQuery(sql9);

        String sql10 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'hostname da rede';
                """.formatted(
                hostnameRede,
                fkMaquina,
                4);

        con.executarQuery(sql10);

        String sql11 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Endereço MAC da rede';
                """.formatted(
                enderecoMACRede,
                fkMaquina,
                4);

        con.executarQuery(sql11);

        String sql12 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Endereço IPv6 da rede';
                """.formatted(
                enderecoIPv6Rede,
                fkMaquina,
                4);

        con.executarQuery(sql12);

        String sql13 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Endereço IPv4 da rede';
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
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Nome de exibição da rede';
                """.formatted(
                nomeExibicaoRede,
                fkMaquina,
                4);

        con.executarQuery(sql15);

        String sql16 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Nome da rede';
                """.formatted(
                nomeRede,
                fkMaquina,
                4);

        con.executarQuery(sql16);

    }


}

package org.example.pc.componentes;

import com.github.britooo.looca.api.core.Looca;
import org.example.Conexao;

public class ProcessadorCp extends Componente {
    public ProcessadorCp(Integer fkMaquina) {
        super(fkMaquina);
    }

    @Override
    public void buscarInfosFixos() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        String nomeProcessador = looca.getProcessador().getNome();
        String potenciaProcessador = nomeProcessador.substring(nomeProcessador.indexOf("@") + 2, nomeProcessador.lastIndexOf("G"));
        Integer nmrPacotesFisicosProcessador = looca.getProcessador().getNumeroPacotesFisicos();
        Integer nmrCpusFisicosProcessador = looca.getProcessador().getNumeroCpusFisicas();
        Integer nmrCpusLogicasProcessador = looca.getProcessador().getNumeroCpusLogicas();

        String queryProcessador = """
                    INSERT INTO componentes VALUES
                                            (null, %d, 3, 'Nome do processador', '%s', 'Nome do processador'),
                                            (null, %d, 3, 'Numero de pacotes físicos do processador', '%s', 'Numero de pacotes físicos do processador'),
                                            (null, %d, 3, 'Potencia do processador', '%s', 'Potencia do processador'),
                                            (null, %d, 3, 'Numero de CPUs físicas do processador', '%s', 'Numero de CPUs físicas do processador'),
                                            (null, %d, 3, 'Numero de CPUs Logicas do processador', '%s', 'Numero de CPUs Logicas do processador')
                """.formatted(
                fkMaquina,
                nomeProcessador,
                fkMaquina,
                nmrPacotesFisicosProcessador,
                fkMaquina,
                potenciaProcessador,
                fkMaquina,
                nmrCpusFisicosProcessador,
                fkMaquina,
                nmrCpusLogicasProcessador
        );

        con.executarQuery(queryProcessador);
    }

    @Override
    public void buscarInfosVariaveis() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        Double emUsoProcessador = (looca.getProcessador().getUso());

        var queryProcessador= """
                    iNSERT INTO dadosTempoReal VALUES  (null, %d, 3, current_timestamp(),'emUso', '%s');
                           """.formatted(
                fkMaquina,
                emUsoProcessador
        );

        con.executarQuery(queryProcessador);

    }

    @Override
    public void atualizarFixos() {

        Looca looca =  new Looca();
        Conexao con =  new Conexao();

        String nomeProcessador = looca.getProcessador().getNome();
        Integer nmrPacotesFisicosProcessador = looca.getProcessador().getNumeroPacotesFisicos();
        String potenciaProcessador = nomeProcessador.substring(nomeProcessador.indexOf("@") + 2, nomeProcessador.lastIndexOf("G"));
        Integer nmrCpusFisicosProcessador = looca.getProcessador().getNumeroCpusFisicas();
        Integer nmrCpusLogicasProcessador = looca.getProcessador().getNumeroCpusLogicas();



        String sql17 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Numero de CPUs Logicas do processador';
                """.formatted(
                nmrCpusLogicasProcessador,
                fkMaquina,
                3);

        con.executarQuery(sql17);

        String sql18 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Numero de CPUs físicas do processador';
                """.formatted(
                nmrCpusFisicosProcessador,
                fkMaquina,
                3);

        con.executarQuery(sql18);

        String sql = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Potencia do processador';
                """.formatted(
                potenciaProcessador,
                fkMaquina,
                3);

        con.executarQuery(sql);

        String sql19 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Numero de pacotes físicos do processador';
                """.formatted(
                nmrPacotesFisicosProcessador,
                fkMaquina,
                3);

        con.executarQuery(sql19);


        String sql20 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'Nome do Processador';
                """.formatted(
                nomeProcessador,
                fkMaquina,
                3);

        con.executarQuery(sql20);

    }
}

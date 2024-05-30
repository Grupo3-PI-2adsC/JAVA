package org.example;

import com.github.britooo.looca.api.core.Looca;

public class ProcessadorCp extends Componente{
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
                                            (4, %d, 3, 'nome', '%s', 'Nome do Processador'),
                                            (5, %d, 3, 'nmrPacotesFisicos', '%s', 'Numero de pacotes físicos do processador'),
                                            (7, %d, 3, 'potencia', '%s', 'Potencia do processador'),
                                            (8, %d, 3, 'nmrCpusFisicos', '%s', 'Numero de CPUs físicas do processador'),
                                            (9, %d, 3, 'nmrCpusLogicas', '%s', 'Numero de CPUs Logicas do processador')
                """.formatted(
                fkMaquina,
                nomeProcessador,
                fkMaquina,
                potenciaProcessador,
                fkMaquina,
                nmrPacotesFisicosProcessador,
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
        Integer nmrCpusFisicosProcessador = looca.getProcessador().getNumeroCpusFisicas();
        Integer nmrCpusLogicasProcessador = looca.getProcessador().getNumeroCpusLogicas();



        String sql17 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nmrCpusLogicas';
                """.formatted(
                nmrCpusLogicasProcessador,
                fkMaquina,
                3);

        con.executarQuery(sql17);

        String sql18 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nmrCpusFisicos';
                """.formatted(
                nmrCpusFisicosProcessador,
                fkMaquina,
                3);

        con.executarQuery(sql18);

        String sql19 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nmrPacotesFisicos';
                """.formatted(
                nmrPacotesFisicosProcessador,
                fkMaquina,
                3);

        con.executarQuery(sql19);


        String sql20 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nome';
                """.formatted(
                nomeProcessador,
                fkMaquina,
                3);

        con.executarQuery(sql20);

    }
}

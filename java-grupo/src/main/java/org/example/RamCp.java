package org.example;
import com.github.britooo.looca.api.core.Looca;

public class RamCp extends Componente{

//    private Memoria memoria;

    public RamCp(Integer fkMaquina) {
        super(fkMaquina);
//        this.memoria = new Memoria();
    }
//
//    public Memoria getMemoria() {
//        return memoria;
//    }

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
    public void atualizarFixos() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        Long totalMemoria = (looca.getMemoria().getTotal() / 1000000000);


        String sql21 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'total';
                """.formatted(
                totalMemoria,
                fkMaquina,
                2);

        con.executarQuery(sql21);

    }
}

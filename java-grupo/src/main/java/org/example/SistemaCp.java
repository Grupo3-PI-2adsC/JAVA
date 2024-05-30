package org.example;

import com.github.britooo.looca.api.core.Looca;

import java.time.Instant;

public class SistemaCp extends Componente{
    public SistemaCp(Integer fkMaquina) {
        super(fkMaquina);
    }

    @Override
    public void buscarInfosFixos() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        //            SISTEMA
        String modeloSistema = looca.getRede().getParametros().getHostName();
        Instant inicializadoSistema = looca.getSistema().getInicializado();

        String querySistema = """
                    INSERT INTO componentes VALUES
                                            (1, %d, 1, 'modelo', '%s', null),
                                            (2, %d, 1, 'inicializado', '%s', null);
                """.formatted(
                fkMaquina,
                modeloSistema,
                fkMaquina,
                inicializadoSistema
        );

        con.executarQuery(querySistema);
    }

    @Override
    public void buscarInfosVariaveis() {

    }

    @Override
    public void atualizarFixos() {

        Looca looca = new Looca();
        Conexao con = new Conexao();

        String modeloSistema = looca.getRede().getParametros().getHostName();
        Instant inicializadoSistema = looca.getSistema().getInicializado();


        String sql = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'modelo';
        
                """.formatted(
                modeloSistema,
                fkMaquina,
                1

        );

        con.executarQuery(sql);

        String sql22 = """
                
                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'inicializado';
                """.formatted(
                inicializadoSistema,
                fkMaquina,
                1);

        con.executarQuery(sql22);

    }
}

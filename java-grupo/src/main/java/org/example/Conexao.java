package org.example;
import com.github.britooo.looca.api.core.Looca;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.h2.engine.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Driver;
public class Conexao {
    private JdbcTemplate conexaoDoBanco;

    public JdbcTemplate getConexaoDoBanco() {
        return conexaoDoBanco;
    }

    private Usuario userAtual = null;

    public Conexao() {
        BasicDataSource dataSource = new BasicDataSource();

        String url = "jdbc:mysql://localhost:3306/netmed";
        String username = "root";
        String password = "Matt@123";
        /*
             Exemplo de driverClassName:
                com.mysql.cj.jdbc.Driver <- EXEMPLO PARA MYSQL
                com.microsoft.sqlserver.jdbc.SQLServerDriver <- EXEMPLO PARA SQL SERVER
        */
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        /*
             Exemplo de string de conexões:
                jdbc:mysql://localhost:3306/mydb <- EXEMPLO PARA MYSQL
                jdbc:sqlserver://localhost:1433;database=mydb <- EXEMPLO PARA SQL SERVER
        */
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        conexaoDoBanco = new JdbcTemplate(dataSource);
    }



    public Usuario buscarCredenciais(String email, String senha){
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
//        RowMapper<Usuario> userTesteRowMapper = (rs, rowNum) -> new Usuario(rs.getInt("id"),
//                rs.getString("email"), rs.getString("senha"));)
        RowMapper<Usuario> usuarioRowMapper = (resultSet, i) ->new Usuario(resultSet.getInt("idUsuario"), resultSet.getString("tipoUsuario"), resultSet.getString("nome"), resultSet.getString("email"), resultSet.getString("senha"), resultSet.getInt("fkEmpresa"));

        String sql = "select * from usuario where email = '%s' and senha = '%s';".formatted(email,senha);

        System.out.println("""
                    Executando a query:
                    %s
                    ...........................................""".formatted(sql));

        userAtual = con.queryForObject(sql,
                usuarioRowMapper);

        Boolean retornoValidar = true;

        return userAtual;
    }

    public void computadorExiste(Integer vez){

        System.out.println("""
                            Verificando se o computador já existe na nossa base de dados
                            .............................................................""");

        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        Looca teste = new Looca();
        String hostname = teste.getRede().getParametros().getHostName();
        Integer arquitetura = teste.getSistema().getArquitetura();
        RowMapper<Computador> computadorRowMapper = (resultSet, i) ->new Computador(resultSet.getInt("idMaquina"), resultSet.getString("hostname"),
                resultSet.getBoolean("ativo"), resultSet.getInt("fkEmpresa"));

        String sql = "select * from maquina where hostName = '%s';".formatted(hostname);

        try {

            System.out.println("""
                    Executando a query:
                    %s
                    ...........................................""".formatted(sql));

        Computador computadorMonitorado = con.queryForObject(sql,
                computadorRowMapper);
//        System.out.println(computadorMonitorado + " asdasd");
            System.out.println("""
                    A Maquina em execução existe na base de dados
                    ...............................................""");

            String sqlAtivo = "update maquina set ativo = 1 where idMaquina = %d;".formatted(computadorMonitorado.getIdMaquina());
            con.execute(sqlAtivo);
//            Computador já tem cadastrado os componentes
            if (vez == 1){
                computadorMonitorado.atualizarFixo();
                computadorMonitorado.buscarInfos(1);
            }else {
//                o computador acabou de ser cadastrado e ainda não possui componente
                computadorMonitorado.buscarInfos(0);
            }
        }catch (Exception erro) {
            System.out.println(erro);
            if (erro.getCause() == null) {

                System.out.println("""
                                    Computador não existe. Vamos cadastralo agora
                                    ..............................................""");

                String sqlMaquina = "INSERT INTO maquina VALUES (null, '%s', %b, %d, %b, %d);".formatted(
                        hostname,
                        true,
                        arquitetura,
                        false,
                        userAtual.getFkEmpresa()
                );

                try {
                    con.execute(sqlMaquina);
                    System.out.println("""
                                        Computador Cadastrado com sucesso
                                        ...................................""");

                    this.computadorExiste(0);
                } catch (Exception erro2) {
                    System.out.println("""
                                          Erro: %s
                                          Causa do erro: %s
                                         .....................""".formatted(erro2, erro2.getCause()));
                }
            }
        }

    }

    public void executarQuery(String query){

        System.out.println("""
                            Executando query:
                            %s
                            ........................""".formatted(query));

        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        con.execute(query);
    }

}

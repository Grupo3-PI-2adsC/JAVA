package org.example.pc;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.processador.Processador;
import org.example.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Computador {
    private Integer idMaquina;
    private String hostname;
    private Boolean ativo;
    private Integer empresa;
    private List<Componente> listaComponentes;
    private RedeCp rede;
    private DiscoCp disco;
    private ProcessadorCp processador;
    private RamCp ram;
    private VolumeCp volume;
    private SistemaCp sistema;

//    private SystemInfo system = new SystemInfo();
//    private OperatingSystem os = system.getOperatingSystem();
//    private HardwareAbstractionLayer hal = system.getHardware();
//    private NetworkIF networkIfs;
//    private SystemInfo si;
//    private HWDiskStore hwDiskStore;
//    private OSFileStore osFileStore;
//    private Sistema sistema;
//    private Memoria memoria;
//    private Processador processador;
//    private ProcessadorCacheLoader processadorCacheLoader;
//    private RedeInterface redeInterface;
//    private RedeParametros redeParametros;
//    private DiscoGrupo grupoDeDiscos;
//    private Disco disco;
//    private Volume volume;
//    private ServicoGrupo servicoGrupo;
//    private ProcessoGrupo processoGrupo;

    public Computador(Integer idMaquina, String hostname, Boolean ativo, Integer empresa) {
        this.idMaquina = idMaquina;
        this.hostname = hostname;
        this.ativo = ativo;
        this.empresa = empresa;
        this.listaComponentes =  new ArrayList<>();
        this.disco       = new DiscoCp(idMaquina);
        this.rede        = new RedeCp(idMaquina);
        this.processador = new ProcessadorCp(idMaquina);
        this.ram         = new RamCp(idMaquina);
        this.volume      = new VolumeCp(idMaquina);
        this.sistema     = new SistemaCp(idMaquina);
        listaComponentes.add(disco);
        listaComponentes.add(processador);
        listaComponentes.add(ram);
        listaComponentes.add(rede);
        listaComponentes.add(sistema);
        listaComponentes.add(volume);

//        this.si = new SystemInfo();
//        this.networkIfs = hal.getNetworkIFs().get(0);
//        this.hwDiskStore = hal.getDiskStores().get(0);
//        this.osFileStore = os.getFileSystem().getFileStores().get(0);
//
//        //Fixos
//        this.sistema = new Sistema();
//        this.redeParametros = new RedeParametros(si);
//
//        //Variaveis
//        this.memoria = new Memoria();
//        this.processadorCacheLoader = new ProcessadorCacheLoader();
//        this.servicoGrupo = new ServicoGrupo();
//
//        //duplo
//        this.processador = new Processador();
//        this.redeInterface = new RedeInterface(networkIfs);
//        this.grupoDeDiscos = new DiscoGrupo(); // volume, disco,
//        this.processoGrupo = new ProcessoGrupo();
//
//        this.disco = new Disco(hwDiskStore);
//        this.volume = new Volume(osFileStore);
    }

//    public Sistema getSistema() {
//        return sistema;
//    }
//
//    public Memoria getMemoria() {
//        return memoria;
//    }
//
//    public Processador getProcessador() {
//        return processador;
//    }
//
//    public ProcessadorCacheLoader getProcessadorCacheLoader() {
//        return processadorCacheLoader;
//    }
//
//    public RedeInterface getRedeInterface() {
//        return redeInterface;
//    }
//
//    public RedeParametros getRedeParametros() {
//        return redeParametros;
//    }
//
//    public DiscoGrupo getGrupoDeDiscos() {
//        return grupoDeDiscos;
//    }
//
//    public Disco getDisco() {
//        return disco;
//    }
//
//    public Volume getVolume() {
//        return volume;
//    }
//
//    public ServicoGrupo getServicoGrupo() {
//        return servicoGrupo;
//    }
//
//    public ProcessoGrupo getProcessoGrupo() {
//        return processoGrupo;
//    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public String getHostname() {
        return hostname;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public List<Componente> getListaComponentes() {
        return listaComponentes;
    }

    public void buscarInfos(Integer primeiro) {
        if (primeiro == 0) {
            for (Componente componenteAtual : listaComponentes) {
                componenteAtual.buscarInfosFixos();
            }
        }

        try {
            TimeUnit.SECONDS.sleep(10);
//            System.out.println("asldnaoçsndfçlasndopansdlkjahs dklahsb d");

            System.out.println("""
                    pegando dados em tempo real
                    ...............................""");
            for (Componente componenteAtual : listaComponentes) {
                componenteAtual.buscarInfosVariaveis();
            }
            buscarInfos(1);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

//    public void cadastrar(){
//        System.out.println("pegando dados em tempo real");
//
//        this.redeInterface = new RedeInterface(networkIfs);
//        this.networkIfs = hal.getNetworkIFs().get(0);
//
//        Conexao con = new Conexao();
//
//
//        Looca looca = new Looca();
////
//////        SISTEMA
////        String tempoAtividadeSistema = Conversor.formatarSegundosDecorridos(looca.getSistema().getTempoDeAtividade());
////
////        var querySistema = """
////                    iNSERT INTO dadosTempoReal VALUES  (null, %d, 1, current_timestamp(),'tempoAtividade', '%s');
////                           """.formatted(
////                                   idMaquina,
////                tempoAtividadeSistema
////        );
////
////        con.executarQuery(querySistema);
////
//
//////        MEMORIA
////        Long emUsoMemoria = (looca.getMemoria().getEmUso() / 1000000000);
////
////        var queryMemoria= """
////                    iNSERT INTO dadosTempoReal VALUES  (null, %d, 2, current_timestamp(),'emUso', '%s');
////                           """.formatted(
////                idMaquina,
////                emUsoMemoria
////        );
////
////        con.executarQuery(queryMemoria);
//
//
////        //        PROCESSADOR
////        Double emUsoProcessador = (looca.getProcessador().getUso());
////
////        var queryProcessador= """
////                    iNSERT INTO dadosTempoReal VALUES  (null, %d, 3, current_timestamp(),'emUso', '%s');
////                           """.formatted(
////                idMaquina,
////                emUsoProcessador
////        );
////
////        con.executarQuery(queryProcessador);
//
//
////        //        REDE
////        Long pacotesRecebidosRede = (looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getPacotesRecebidos());
////        Long pacotesEnviadosRede = (looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getPacotesEnviados());
////
////        var queryRede= """
////                    iNSERT INTO dadosTempoReal VALUES  (null, %d, 4, current_timestamp(),'Pacotes recebidos', '%s'),
////                                                       (null, %d, 4, current_timestamp(),'Pacotes enviados', '%s');
////                           """.formatted(
////                idMaquina,
////                pacotesRecebidosRede,
////                idMaquina,
////                pacotesEnviadosRede
////        );
////
////
////        con.executarQuery(queryRede);
//
//
//
////        //        DISCO
////        Long leiturasDisco = (looca.getGrupoDeDiscos().getDiscos().get(0).getLeituras());
////        Long escritarDisco = (looca.getGrupoDeDiscos().getDiscos().get(0).getEscritas());
////
////        var queryDisco= """
////                    iNSERT INTO dadosTempoReal VALUES  (null, %d, 5, current_timestamp(),'Leituras', '%s'),
////                                                       (null, %d, 5, current_timestamp(),'Leituras', '%s');
////                           """.formatted(
////                idMaquina,
////                leiturasDisco,
////                idMaquina,
////                escritarDisco
////        );
////
////
////        con.executarQuery(queryDisco);
//
//
//
//    }

//    public void cadastrarPrimeiro() {
//
//        Looca looca = new Looca();
//
//        System.out.println("""
//                Cadastrando Componentes fixos do computador
//                ............................................""");
//
//        Conexao con = new Conexao();
//
////        //            SISTEMA
////        String modeloSistema = looca.getRede().getParametros().getHostName();
////        Instant inicializadoSistema = looca.getSistema().getInicializado();
////
////        String querySistema = """
////                    INSERT INTO componentes VALUES
////                                            (1, %d, 1, 'modelo', '%s', null),
////                                            (2, %d, 1, 'inicializado', '%s', null);
////                """.formatted(
////                idMaquina,
////                modeloSistema,
////                idMaquina,
////                inicializadoSistema
////        );
////
////        con.executarQuery(querySistema);
//
//
//////            Memoria
////        Long totalMemoria = (looca.getMemoria().getTotal() / 1000000000);
////
////        String queryMemoria = """
////                    INSERT INTO componentes VALUES
////                                            (3, %d, 2, 'total', '%s', 'total de memoria do computador')
////                """.formatted(
////                idMaquina,
////                totalMemoria
////        );
////
////        con.executarQuery(queryMemoria);
//
//
//////            Processador
////        String nomeProcessador = looca.getProcessador().getNome();
////        String potenciaProcessador = nomeProcessador.substring(nomeProcessador.indexOf("@") + 2, nomeProcessador.lastIndexOf("G"));
////        Integer nmrPacotesFisicosProcessador = looca.getProcessador().getNumeroPacotesFisicos();
////        Integer nmrCpusFisicosProcessador = looca.getProcessador().getNumeroCpusFisicas();
////        Integer nmrCpusLogicasProcessador = looca.getProcessador().getNumeroCpusLogicas();
////
////        String queryProcessador = """
////                    INSERT INTO componentes VALUES
////                                            (4, %d, 3, 'nome', '%s', 'Nome do Processador'),
////                                            (5, %d, 3, 'nmrPacotesFisicos', '%s', 'Numero de pacotes físicos do processador'),
////                                            (7, %d, 3, 'potencia', '%s', 'Potencia do processador'),
////                                            (8, %d, 3, 'nmrCpusFisicos', '%s', 'Numero de CPUs físicas do processador'),
////                                            (9, %d, 3, 'nmrCpusLogicas', '%s', 'Numero de CPUs Logicas do processador')
////                """.formatted(
////                idMaquina,
////                nomeProcessador,
////                idMaquina,
////                potenciaProcessador,
////                idMaquina,
////                nmrPacotesFisicosProcessador,
////                idMaquina,
////                nmrCpusFisicosProcessador,
////                idMaquina,
////                nmrCpusLogicasProcessador
////        );
////
////        con.executarQuery(queryProcessador);
//
//
//////            Rede
////        String nomeRede = looca.getRede().getParametros().getNomeDeDominio();
////        String nomeExibicaoRede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getNomeExibicao();
////        List enderecoIPv4Rede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoIpv4();
////        List enderecoIPv6Rede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoIpv6();
////        String enderecoMACRede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoMac();
////        String hostnameRede = looca.getRede().getParametros().getHostName();
////        List servidoresDNSRede = looca.getRede().getParametros().getServidoresDns();
////
////        String queryRede = """
////                    INSERT INTO componentes VALUES
////                                            (10, %d, 4, 'nome', '%s', 'Nome da rede'),
////                                            (11, %d, 4, 'nomeExibicao', '%s', 'Nome de exibição da rede'),
////                                            (12, %d, 4, 'enderecoIPv4', '%s', 'Endereço IPv4 da rede'),
////                                            (13, %d, 4, 'enderecoIPv6', '%s', 'Endereço IPv6 da rede'),
////                                            (14, %d, 4, 'enderecoMAC', '%s', 'Endereço MAC da rede'),
////                                            (15, %d, 4, 'hostname', '%s', 'hostname da rede'),
////                                            (16, %d, 4, 'servidoresDNS', '%s', 'servidores DNS da rede')
////                """.formatted(
////                idMaquina,
////                nomeRede,
////                idMaquina,
////                nomeExibicaoRede,
////                idMaquina,
////                enderecoIPv4Rede,
////                idMaquina,
////                enderecoIPv6Rede,
////                idMaquina,
////                enderecoMACRede,
////                idMaquina,
////                hostnameRede,
////                idMaquina,
////                servidoresDNSRede
////        );
////
////        con.executarQuery(queryRede);
//
////            Integer qtdDiscosDisco = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();
////        for (int i = 0; i < qtdDiscosDisco; i++) {
////
////
////    //            DISCO
////            String nomeDisco = looca.getGrupoDeDiscos().getDiscos().get(i).getNome();
////            Long tamanhoDisco = (looca.getGrupoDeDiscos().getDiscos().get(i).getTamanho() / 1000000000);
////
////            String queryDisco = """
////                        INSERT INTO componentes VALUES
////                                                (16, %d, 5, 'qtdDiscos', '%s', 'Quantidade de disco no computador'),
////                                                (17, %d, 5, 'nome', '%s', 'Nome do disco'),
////                                                (18, %d, 5, 'tamanho', '%s', 'tamanho do disco')
////                    """.formatted(
////                    idMaquina,
////                    qtdDiscosDisco,
////                    idMaquina,
////                    nomeDisco,
////                    idMaquina,
////                    tamanhoDisco
////            );
////
////            con.executarQuery(queryDisco);
////        }
//
////        Integer qtdVolumesVolume = looca.getGrupoDeDiscos().getQuantidadeDeVolumes();
////        for (int i = 1; i < qtdVolumesVolume; i++) {
////
////    //            VOLUME
////            String nomeVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getNome();
////            String UUIDVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getUUID();
////            Long totalVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getTotal();
////            Long disponivelVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getDisponivel();
////            String tipoVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getTipo();
////
////            String queryVolume = """
////                            INSERT INTO componentes VALUES
////                                                    (19, %d, 6, 'qtdVolumes', '%s', 'quantidade de volumes no computador'),
////                                                    (20, %d, 6, 'UUID', '%s', 'UUID do volume'),
////                                                    (21, %d, 6, 'nome', '%s', 'nome do volume'),
////                                                    (22, %d, 6, 'total', '%s', 'tamanho total do volume'),
////                                                    (23, %d, 6, 'disponivel', '%s', 'tamanho disponivel do volume'),
////                                                    (24, %d, 6, 'tipo', '%s', 'tipo do volume')
////                        """.formatted(
////                    idMaquina,
////                    qtdVolumesVolume,
////                    idMaquina,
////                    UUIDVolume,
////                    idMaquina,
////                    nomeVolume,
////                    idMaquina,
////                    totalVolume,
////                    idMaquina,
////                    disponivelVolume,
////                    idMaquina,
////                    tipoVolume
////            );
////
////            con.executarQuery(queryVolume);
////        }
//
//    }

    public void atualizarFixo(){

//        Looca looca = new Looca();
//
//        System.out.println("""
//        Atualizando Componentes fixos do computador
//        ............................................""");
//
//        Conexao con = new Conexao();
//
//        String modeloSistema = looca.getRede().getParametros().getHostName();
//        Instant inicializadoSistema = looca.getSistema().getInicializado();
//        Long totalMemoria = (looca.getMemoria().getTotal() / 1000000000);
//        String nomeProcessador = looca.getProcessador().getNome();
//        Integer nmrPacotesFisicosProcessador = looca.getProcessador().getNumeroPacotesFisicos();
//        Integer nmrCpusFisicosProcessador = looca.getProcessador().getNumeroCpusFisicas();
//        Integer nmrCpusLogicasProcessador = looca.getProcessador().getNumeroCpusLogicas();
//        String nomeRede = looca.getRede().getParametros().getNomeDeDominio();
//        String nomeExibicaoRede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getNomeExibicao();
//        List enderecoIPv4Rede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoIpv4();
//        List enderecoIPv6Rede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoIpv6();
//        String enderecoMACRede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoMac();
//        String hostnameRede = looca.getRede().getParametros().getHostName();
//        List servidoresDNSRede = looca.getRede().getParametros().getServidoresDns();
//        Integer qtdDiscosDisco = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();
//
//
//
//
//
//        String sql = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'modelo';
//
//                """.formatted(
//                        modeloSistema,
//                        idMaquina,
//                        1
//
//                    );
//
//        con.executarQuery(sql);
//
//        Integer qtdVolumesVolume = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();
//        for (int i = 1; i < qtdVolumesVolume; i++) {
//            String nomeVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getNome();
//            String UUIDVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getUUID();
//            Long totalVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getTotal();
//            Long disponivelVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getDisponivel();
//            String tipoVolume = looca.getGrupoDeDiscos().getVolumes().get(i).getTipo();
//
//        String sql1 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'tipo';
//                """.formatted(
//                tipoVolume,
//                idMaquina,
//                6);
//        con.executarQuery(sql1);
//
//        String sql2 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'disponivel';
//                """.formatted(
//                totalVolume,
//                idMaquina,
//                6);
//
//        con.executarQuery(sql2);
//
//
//        String sql3 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'total';
//                """.formatted(
//                tipoVolume,
//                idMaquina,
//                6);
//
//        con.executarQuery(sql3);
//
//        String sql4 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nome';
//                """.formatted(
//                nomeVolume,
//                idMaquina,
//                6);
//
//        con.executarQuery(sql4);
//
//        String sql5 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'UUID';
//                """.formatted(
//                UUIDVolume,
//                idMaquina,
//                6);
//
//        con.executarQuery(sql5);
//        }
//
//        for (int i = 0; i < qtdDiscosDisco; i++) {
//            String nomeDisco = looca.getGrupoDeDiscos().getDiscos().get(i).getNome();
//            Long tamanhoDisco = (looca.getGrupoDeDiscos().getDiscos().get(i).getTamanho() / 1000000000);
//
//        String sql6 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'qtdVolumes';
//                """.formatted(
//                tamanhoDisco,
//                idMaquina,
//                5);
//
//        con.executarQuery(sql6);
//
//        String sql7 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'tamanho';
//                """.formatted(
//                nomeDisco,
//                idMaquina,
//                5);
//
//        con.executarQuery(sql7);
//        }
//
//        String sql8 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nome';
//                """.formatted(
//                qtdDiscosDisco,
//                idMaquina,
//                5);
//
//        con.executarQuery(sql8);
//
//        String sql9 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'qtdDiscos';
//                """.formatted(
//                servidoresDNSRede,
//                idMaquina,
//                4);
//
//        con.executarQuery(sql9);
//
//        String sql10 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'servidoresDNS';
//                """.formatted(
//                hostnameRede,
//                idMaquina,
//                4);
//
//        con.executarQuery(sql10);
//
//        String sql11 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'hostname';
//                """.formatted(
//                enderecoMACRede,
//                idMaquina,
//                4);
//
//        con.executarQuery(sql11);
//
//        String sql12 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'enderecoMAC';
//                """.formatted(
//                enderecoIPv6Rede,
//                idMaquina,
//                4);
//
//        con.executarQuery(sql12);
//
//        String sql13 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'enderecoIPv6';
//                """.formatted(
//                enderecoIPv4Rede,
//                idMaquina,
//                4);
//
//        con.executarQuery(sql13);
//
//        String sql14 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'enderecoIPv4';
//                """.formatted(
//                nomeExibicaoRede,
//                idMaquina,
//                4);
//
//        con.executarQuery(sql14);
//
//        String sql15 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nomeExebicao';
//                """.formatted(
//                nomeRede,
//                idMaquina,
//                4);
//
//        con.executarQuery(sql15);
//
//        String sql16 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nome';
//                """.formatted(
//                nomeRede,
//                idMaquina,
//                4);
//
//        con.executarQuery(sql16);
//
//        String sql17 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nmrCpusLogicas';
//                """.formatted(
//                nmrCpusLogicasProcessador,
//                idMaquina,
//                3);
//
//        con.executarQuery(sql17);
//
//        String sql18 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nmrCpusFisicos';
//                """.formatted(
//                nmrCpusFisicosProcessador,
//                idMaquina,
//                3);
//
//        con.executarQuery(sql18);
//
//        String sql19 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nmrPacotesFisicos';
//                """.formatted(
//                nmrPacotesFisicosProcessador,
//                idMaquina,
//                3);
//
//        con.executarQuery(sql19);
//
//
//        String sql20 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'nome';
//                """.formatted(
//                nomeProcessador,
//                idMaquina,
//                3);
//
//        con.executarQuery(sql20);
//
//
//        String sql21 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'total';
//                """.formatted(
//                totalMemoria,
//                idMaquina,
//                2);
//
//        con.executarQuery(sql21);
//
//
//        String sql22 = """
//
//                UPDATE componentes SET valorCampo = '%s' where fkMaquina = '%d' and fkTipoComponente = '%d' and nomeCampo = 'inicializado';
//                """.formatted(
//                inicializadoSistema,
//                idMaquina,
//                1);
//
//        con.executarQuery(sql22);
    }




    @Override
    public String toString() {
//        SERVIÇÕES E PROCESSOS SÃO OS MESMO MAS COM INFOS DIFERENTES
        return "Computador{" +
//                "  \nsistema=" + sistema +
//                ", \nmemoria=" + memoria +
//                ", \nprocessador=" + processador +
//                ", \nUso: (processadorCacheLoader)=" + processadorCacheLoader.getUso() +
//                ", \nredeInterface=" + redeInterface +
//                ", \nredeParametros=" + redeParametros +
//                ", \nDisco=" + grupoDeDiscos.getDiscos() +
//                ", \nVolume=" + grupoDeDiscos.getVolumes() +
//                ", \nQuantidade de Discos=" + grupoDeDiscos.getQuantidadeDeDiscos() +
//                ", \nQuantidade de Volumes=" + grupoDeDiscos.getQuantidadeDeVolumes() +
//                ", \nTamanho total (disco)=" + grupoDeDiscos.getTamanhoTotal() +
//                ", \nTotal Serviços=" + servicoGrupo.getTotalDeServicos() +
//                ", \nTotal Serviços Inativos=" + servicoGrupo.getTotalServicosInativos() +
//                ", \nTotal Serviços Inativos=" + servicoGrupo.getTotalServicosAtivos() +
//                ", \nServições ativos=" + servicoGrupo.getServicosAtivos() +
//                ", \nServições inativos=" + servicoGrupo.getServicosInativos() +
//                ", \nProcessos=" + processoGrupo +
//                ", \nTotal de processos=" + processoGrupo.getTotalProcessos() +
//                ", \nTotal de Thereads=" + processoGrupo.getTotalThreads() +
//                ", \nProcessos=" + processoGrupo.getProcessos() +
                "  \n}";
    }

}

package br.ufpb.dcx.rodrigor.atividade.sca;

import br.ufpb.dcx.rodrigor.atividade.sca.persistencia.json.GerenciadorAlunosJSON;
import br.ufpb.dcx.rodrigor.atividade.sca.persistencia.xml.GerenciadorAlunosXML;
import br.ufpb.dcx.rodrigor.atividade.sca.text_ui.MenusTexto;

public class SCA {


    public static void main(String[] args) {


        //new MenusTexto(new GerenciadorAlunosXML("alunos.xml")).exibirMenu();
        new MenusTexto(new GerenciadorAlunosJSON("alunos.json")).exibirMenu();

    }
}

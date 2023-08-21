package br.ufpb.dcx.rodrigor.atividade.sca.persistencia.xml;
import br.ufpb.dcx.rodrigor.atividade.sca.model.Aluno;
import br.ufpb.dcx.rodrigor.atividade.sca.persistencia.ErroPersistenciaException;
import br.ufpb.dcx.rodrigor.atividade.sca.persistencia.GerentePersistenciaAlunos;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorAlunosXML implements GerentePersistenciaAlunos {
    private String nomeArquivo;
    private XStream xstream;

    public GerenciadorAlunosXML(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.xstream = new XStream(new DomDriver());
        this.xstream.addPermission(AnyTypePermission.ANY);
        this.xstream.alias("aluno", Aluno.class);
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void cadastrarAluno(Aluno aluno) {
        List<Aluno> listaAlunos = recuperarAlunos();

        if (listaAlunos == null) {
            listaAlunos = new ArrayList<>();
        }

        listaAlunos.add(aluno);
        salvarAlunos(listaAlunos);
    }

    public List<Aluno> recuperarAlunos() {
        try {
            File arquivo = new File(nomeArquivo);

            if (arquivo.exists()) {
                FileInputStream fis = new FileInputStream(arquivo);
                ObjectInputStream ois = xstream.createObjectInputStream(fis);
                List<Aluno> listaAlunos = (List<Aluno>) ois.readObject();
                ois.close();
                return listaAlunos;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new ErroPersistenciaException(e.getMessage());
        }

        return null;
    }

    private void salvarAlunos(List<Aluno> listaAlunos) {
        try {
            File arquivo = new File(nomeArquivo);
            FileOutputStream fos = new FileOutputStream(arquivo);
            ObjectOutputStream oos = xstream.createObjectOutputStream(fos);
            oos.writeObject(listaAlunos);
            oos.close();
        } catch (IOException e) {
            throw new ErroPersistenciaException("Erro ao salvar alunos no arquivo XML: " + e.getMessage());
        }
    }
}

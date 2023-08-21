package br.ufpb.dcx.rodrigor.atividade.sca.persistencia.json;

import br.ufpb.dcx.rodrigor.atividade.sca.model.Aluno;
import br.ufpb.dcx.rodrigor.atividade.sca.persistencia.ErroPersistenciaException;
import br.ufpb.dcx.rodrigor.atividade.sca.persistencia.GerentePersistenciaAlunos;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorAlunosJSON implements GerentePersistenciaAlunos {
    private String nomeArquivo;
    private ObjectMapper objectMapper;

    public GerenciadorAlunosJSON(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
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
                List<Aluno> listaAlunos = objectMapper.readValue(fis, new TypeReference<List<Aluno>>() {});
                fis.close();
                return listaAlunos;
            }
        } catch (IOException e) {
            throw new ErroPersistenciaException("Erro ao recuperar alunos do arquivo JSON: " + e.getMessage());
        }

        return null;
    }

    private void salvarAlunos(List<Aluno> listaAlunos) {
        try {
            File arquivo = new File(nomeArquivo);
            FileOutputStream fos = new FileOutputStream(arquivo);
            objectMapper.writeValue(fos, listaAlunos);
            fos.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar alunos no arquivo JSON: " + e.getMessage());
        }
    }
}

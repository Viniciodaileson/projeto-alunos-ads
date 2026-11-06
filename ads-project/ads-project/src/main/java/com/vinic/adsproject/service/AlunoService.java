package com.vinic.adsproject.service;

import com.vinic.adsproject.domain.Aluno;
import com.vinic.adsproject.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public Aluno salvar(Aluno aluno) {
        if (alunoRepository.existsByEmail(aluno.getEmail())) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com este e-mail.");
        }

        return alunoRepository.save(aluno);
    }

    public void deletar(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new IllegalArgumentException("Aluno não encontrado para exclusão.");
        }
        alunoRepository.deleteById(id);
    }

    public Aluno atualizar(Long id, Aluno dadosAtualizados) {
        return alunoRepository.findById(id)
                .map(alunoExistente -> {
                  
                    if (!alunoExistente.getEmail().equals(dadosAtualizados.getEmail()) &&
                            alunoRepository.existsByEmail(dadosAtualizados.getEmail())) {
                        throw new IllegalArgumentException("Este e-mail já está sendo usado por outro aluno.");
                    }

                    alunoExistente.setNome(dadosAtualizados.getNome());
                    alunoExistente.setEmail(dadosAtualizados.getEmail());
                    alunoExistente.setCurso(dadosAtualizados.getCurso());
                    return alunoRepository.save(alunoExistente);
                })
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado para atualização."));
    }

}

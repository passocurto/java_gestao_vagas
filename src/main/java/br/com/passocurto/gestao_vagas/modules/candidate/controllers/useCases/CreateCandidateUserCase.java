package br.com.passocurto.gestao_vagas.modules.candidate.controllers.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.passocurto.gestao_vagas.exceptions.UserFoundException;
import br.com.passocurto.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.passocurto.gestao_vagas.modules.candidate.controllers.CandidateRepository;

@Service
public class CreateCandidateUserCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {

        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.candidateRepository.save(candidateEntity);
    }

}

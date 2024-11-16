package br.com.passocurto.gestao_vagas.modules.candidate.useCases;

import java.sql.PseudoColumnUsage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.passocurto.gestao_vagas.exceptions.UserFoundException;
import br.com.passocurto.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.passocurto.gestao_vagas.modules.candidate.controllers.CandidateRepository;

@Service
public class CreateCandidateUserCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity) {

        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(candidateEntity.getPassword());

        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity);
    }

}

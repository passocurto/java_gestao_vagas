package br.com.passocurto.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.passocurto.gestao_vagas.exceptions.JobNotFoundException;
import br.com.passocurto.gestao_vagas.exceptions.UsernameNotFoundException;
import br.com.passocurto.gestao_vagas.modules.candidate.controllers.CandidateRepository;
import br.com.passocurto.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.passocurto.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.passocurto.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    // ID candidato
    // ID vaga
    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
        // validar se candidato existe
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException();
                });
        // validar vaga existe

        this.jobRepository.findById(idJob)
                .orElseThrow(() -> {
                    throw new JobNotFoundException();
                });

        // candidato se inscrever na vaga
        var applyJob = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        applyJob = applyJobRepository.save(applyJob);

        return applyJob;
    }

}

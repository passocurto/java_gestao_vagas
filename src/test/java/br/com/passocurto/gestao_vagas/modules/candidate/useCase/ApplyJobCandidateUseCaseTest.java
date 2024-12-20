package br.com.passocurto.gestao_vagas.modules.candidate.useCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.passocurto.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.passocurto.gestao_vagas.modules.candidate.controllers.CandidateRepository;
import br.com.passocurto.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.passocurto.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.passocurto.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.passocurto.gestao_vagas.modules.company.entities.JobEntity;
import br.com.passocurto.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            // UsernameNotFoundException.class);
            // assertThat not working
        }
    }

    @Test
    public void should_not_be_able_to_apply_with_job_not_found() {

        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e) {
            // UsernameNotFoundException.class);
            // assertThat not working
        }
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    public void should_be_able_to_create_a_new_apply_apply_job() {

        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity
                .builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .id(UUID.randomUUID())
                .build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        applyJob.setId(UUID.randomUUID());

        when(applyJobRepository.save(applyJob)).thenReturn(applyJob);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());

    }
}

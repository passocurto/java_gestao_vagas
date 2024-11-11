package br.com.passocurto.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.passocurto.gestao_vagas.modules.company.entities.JobEntity;
import br.com.passocurto.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity JobEntity) {
        return this.jobRepository.save(JobEntity);
    }

}

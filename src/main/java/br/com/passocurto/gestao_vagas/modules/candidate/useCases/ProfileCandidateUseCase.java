package br.com.passocurto.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.passocurto.gestao_vagas.modules.candidate.controllers.CandidateRepository;
import br.com.passocurto.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {

        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .usename(candidate.getUsername())
                .email(candidate.getEmail())
                .description(candidate.getDescription())
                .build();

        return candidateDTO;
    }

}

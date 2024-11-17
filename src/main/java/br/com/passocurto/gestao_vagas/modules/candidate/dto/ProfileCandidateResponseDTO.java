package br.com.passocurto.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    private UUID id;
    @Schema(example = "Maria")
    private String name;
    @Schema(example = "maria")
    private String usename;
    @Schema(example = "maria@gmail.com")
    private String email;
    @Schema(example = "descricao da maria")
    private String description;

}

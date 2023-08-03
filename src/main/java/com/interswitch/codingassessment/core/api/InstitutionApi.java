package com.interswitch.codingassessment.core.api;

import com.interswitch.codingassessment.core.models.dto.AssignInstitutionDto;
import com.interswitch.codingassessment.core.models.dto.InstitutionRequestDto;
import com.interswitch.codingassessment.core.service.InstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 8/1/23
 **/
@RestController
@RequestMapping("api/v1/institution")
@RequiredArgsConstructor
public class InstitutionApi {

    private final InstitutionService institutionService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object addInstitution(@RequestBody InstitutionRequestDto institutionRequestDto){
        return institutionService.addInstitution(institutionRequestDto);
    }

    @PostMapping(path = "assign", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object assignInstitution(@RequestBody AssignInstitutionDto assignInstitutionDto){
        return institutionService.assignInstitutionToUser(assignInstitutionDto);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getInstitution(@PathVariable("id") Long id){
        return institutionService.getInstituteById(id);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAllInstitution(){
        return institutionService.getInstituteAll();
    }
}

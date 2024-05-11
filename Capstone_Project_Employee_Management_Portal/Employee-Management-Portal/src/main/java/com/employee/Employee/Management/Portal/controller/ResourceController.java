package com.employee.Employee.Management.Portal.controller;


import com.employee.Employee.Management.Portal.dto.*;
import com.employee.Employee.Management.Portal.entity.Resource;
import com.employee.Employee.Management.Portal.service.RequestResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ResourceController {


    @Autowired
    private RequestResourceService requestResourceService;

    @PostMapping("/requestResource/create")
    public ApiResponseDto createRequestResource(
            @RequestBody final RequestResourceDto requestResourceDto) {
        requestResourceService.createRequestResource(requestResourceDto);
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("Requested resource successfully");
        return apiResponseDto;

    }

    @PostMapping("/requestResource/isRequested")
    public RequestedOutDto isRequested(
             @RequestBody final RequestedDto requestedDto) {
        RequestedOutDto requestedOutDto = requestResourceService
                .isRequested(requestedDto);
        return requestedOutDto;
    }

    @GetMapping("/getAll/project/byManager/{email}")
    public final List<RequestResourceManagerProjectDto> getAllByManagerEmail(
            @PathVariable final String email){
        List<RequestResourceManagerProjectDto> projectList = requestResourceService
                .getAllByManagerEmail(email);
        return projectList;

    }

    @GetMapping("/requestResource/getAll/requests")
    public List<ResourceRequestsAdminOutDto> getAllResourceRequests() {
        List<ResourceRequestsAdminOutDto> outDtoList = requestResourceService
                .getAllResourceRequests();
        return outDtoList;

    }

    @DeleteMapping("/requestResource/reject/{id}")
    public ApiResponseDto rejectResourceRequest(@PathVariable final Long id){
        requestResourceService.rejectResourceRequest(id);
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("Resource request rejected successfully");
        return apiResponseDto;

    }

    @PostMapping("/request/accept/{id}")
    public final ApiResponseDto acceptRequest(@PathVariable final Long id) {
        ApiResponseDto apiResponseDto = requestResourceService.acceptRequest(id);
        return apiResponseDto;
    }
}

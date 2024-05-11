package com.employee.Employee.Management.Portal.service;

import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.dto.RegisterDto;
import com.employee.Employee.Management.Portal.dto.UpdateSkillDto;
import com.employee.Employee.Management.Portal.entity.Project;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.repository.ProjectRepository;
import com.employee.Employee.Management.Portal.repository.SkillsRepository;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillsRepository skillsRepository;


    public RegisterDto getEmployee(final String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        RegisterDto resultDto = new RegisterDto();
        resultDto.setEmpId(user.getEmpId());
        resultDto.setName(user.getName());
        resultDto.setEmail(user.getEmail());
        resultDto.setContactNo(user.getContactNo());
        resultDto.setDob(user.getDob());
        resultDto.setDoj(user.getDoj());
        resultDto.setLocation(user.getLocation());
//        resultDto.setAssignedSkills(user.getAssignedSkills());
        if(user.getEmpProjectId() == null){
            resultDto.setProjectName("N/A");
        }else {
            Optional<Project> project = projectRepository.findById(user.getEmpProjectId());
            if (!project.isPresent()) {
                throw new RuntimeException("Project not found");
            }
            resultDto.setProjectName(project.get().getProjectName());
        }
        User manager = userRepository.findById(user.getEmpManagerId()).orElse(null);
        if(manager == null){
            throw new RuntimeException("Manager not found");
        }
        resultDto.setManagerName(manager.getName());
        return resultDto;
    }


    // Check for the skills of the employee and update the skills
    // very ver important
    public ApiResponseDto updateSkill(UpdateSkillDto updateSkillDto) {
        String email = updateSkillDto.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found" + email));
        user.setAssignedSkills(updateSkillDto.getSkills());
        return new ApiResponseDto("Skills updated successfully");

    }
}

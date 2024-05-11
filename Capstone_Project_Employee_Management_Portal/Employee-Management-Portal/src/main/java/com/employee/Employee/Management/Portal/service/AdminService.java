package com.employee.Employee.Management.Portal.service;

import com.employee.Employee.Management.Portal.dto.*;
import com.employee.Employee.Management.Portal.entity.Project;
import com.employee.Employee.Management.Portal.entity.Role;
import com.employee.Employee.Management.Portal.entity.Skills;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.repository.ProjectRepository;
import com.employee.Employee.Management.Portal.repository.SkillsRepository;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SkillsRepository skillsRepository;
    @Autowired
    private ProjectRepository projectRepository;



    public List<RegisterOutDto> getAllEmployee() {
        List<User> managerEntities = userRepository.findAllByRole(Role.EMPLOYEE);
        return convertToRegisterDto(managerEntities);
    }

    public List<RegisterOutDto> getAllEmployeeAndManager() {
        List<User> employeeAndManagerEntities = userRepository.findAllByRoleNot(Role.ADMIN);
        return convertToRegisterDto(employeeAndManagerEntities);
    }

    private List<RegisterOutDto> convertToRegisterDto(final List<User> managerEntities) {
        List<RegisterOutDto> registerDtos = new ArrayList<>();
        for (User user : managerEntities) {
            RegisterOutDto registerOutDto = new RegisterOutDto();
            registerOutDto.setEmpId(user.getEmpId());
            registerOutDto.setName(user.getName());
            registerOutDto.setEmail(user.getEmail());
            registerOutDto.setContactNo(user.getContactNo());
            registerOutDto.setDesignation(user.getDesignation());
            registerOutDto.setDob(user.getDob());
            registerOutDto.setDoj(user.getDoj());
            registerOutDto.setLocation(user.getLocation());
            registerOutDto.setEmpProjectId(user.getEmpProjectId());
            registerOutDto.setRole(user.getRole());
            registerOutDto.setAssignedSkills(user.getAssignedSkills());

            if(user.getEmpManagerId() != null) {
                Optional<User> manager = userRepository.findById(user.getEmpManagerId());
                if(manager.isPresent()) {
                   User managerUser = manager.get();
                     registerOutDto.setManagerName(managerUser.getName());
                }
            }
            if (user.getEmpProjectId() != null){
                Optional<Project> projectEntity = projectRepository.findById(user.getEmpProjectId());
                if(projectEntity != null){
                    registerOutDto.setProjectName(projectEntity.get().getProjectName());
                }
            }
            registerDtos.add(registerOutDto);
        }
        return registerDtos;

    }

    public List<ManagerOutDto> getAllManagers() {
        List<User> managerEntities = userRepository
                .findAllByRole(Role.MANAGER);
        return convertToManagerDtoList(managerEntities);
    }

    private List<ManagerOutDto> convertToManagerDtoList(List<User> managerEntities) {
        List<ManagerOutDto> managerOutDtos = new ArrayList<>();
        for (User user : managerEntities) {
            ManagerOutDto managerOutDto = new ManagerOutDto();
            managerOutDto.setId(user.getId());
            managerOutDto.setEmpId(user.getEmpId());
            managerOutDto.setName(user.getName());
            managerOutDto.setEmail(user.getEmail());
            managerOutDto.setContactNo(user.getContactNo());
            managerOutDto.setDesignation(user.getDesignation());
            managerOutDto.setLocation(user.getLocation());
            managerOutDtos.add(managerOutDto);
        }
        return managerOutDtos;
    }

    public List<ManagerInfoDto> getAllManagersInfo() {
        List<User> managerEntities = userRepository
                .findAllByRole(Role.MANAGER);
        return managerEntities.stream().map(this::convertToManagerInfoDto)
                .collect(Collectors.toList());
    }

    private ManagerInfoDto convertToManagerInfoDto(User user) {
        ManagerInfoDto managerInfoDto = new ManagerInfoDto();
        managerInfoDto.setId(user.getId());
        managerInfoDto.setManagerName(user.getName());
        managerInfoDto.setManagerEmployeeId(user.getEmpId());
        return managerInfoDto;
    }

    public ApiResponseDto addProject(ProjectDto projectDto) {

        Project projectEntity = new Project();
        projectEntity.setProjectName(projectDto.getProjectName());
        projectEntity.setDescription(projectDto.getDescription());
        projectEntity.setStartDate(projectDto.getStartDate());
        Optional<User> user = userRepository.findById(projectDto.getManager());
        projectEntity.setManager(user.get());
        projectRepository.save(projectEntity);
        return new ApiResponseDto("Project added successfully");
    }

    public List<ProjectDto> getAllProjects() {
        List<Project> projectEntities = projectRepository.findAll();
        List<ProjectDto> projectDtos = convertToProjectDtoList(projectEntities);
        return projectDtos;
    }

    private List<ProjectDto> convertToProjectDtoList(List<Project> entities) {
        List<ProjectDto> dtos = new ArrayList<>();
        for (Project project : entities) {
            ProjectDto projectDto = new ProjectDto();
            projectDto.setId(project.getId());
            projectDto.setProjectName(project.getProjectName());
            projectDto.setDescription(project.getDescription());
            projectDto.setStartDate(project.getStartDate());
            projectDto.setManager(project.getManager().getId());

            List<User> teamMembers = userRepository.findAllByEmpProjectId(project.getId());
            Optional<User> headEntity = userRepository.findById(project.getManager().getId());
            if (headEntity.isPresent()) {
                projectDto.setHead(headEntity.get().getName());
            }
            List<String> teamMemberNames = new ArrayList<>();
            for(User teamMember : teamMembers) {
                teamMemberNames.add(teamMember.getName());
            }
            projectDto.setTeamMembers(teamMemberNames);
            dtos.add(projectDto);
        }
        return dtos;
    }

    public List<AssignProjectOutDto> getAllProjectsForAssign() {
        List<Project> projectEntities = projectRepository.findAll();
       List<AssignProjectOutDto> assignProjectOutDtos = projectEntities.stream().map(project -> {
            AssignProjectOutDto assignProjectOutDto = new AssignProjectOutDto();
            assignProjectOutDto.setId(project.getId());
            assignProjectOutDto.setProjectName(project.getProjectName());
            return assignProjectOutDto;
        }).collect(Collectors.toList());
       return assignProjectOutDtos;

    }


    public List<ProjectOutDto> getAllByManagerId(Long managerId) {
        // Fetch the manager entity by ID
        User manager = userRepository.findById(managerId).orElse(null);

        List<ProjectOutDto> projectOutDtos = new ArrayList<ProjectOutDto>();

        if (manager != null) {
            // Query projects by manager ID
            List<Project> projectEntities = projectRepository.findAllByManager(manager);

            for (Project project : projectEntities) {
                ProjectOutDto projectOutDto = new ProjectOutDto();
                projectOutDto.setId(project.getId());
                projectOutDto.setProjectName(project.getProjectName());
                // Convert manager ID to string
                projectOutDto.setManager(String.valueOf(managerId));
//                projectOutDto.setManager(project.getManager() + "");
                projectOutDto.setDescription(project.getDescription());
                projectOutDto.setStartDate(project.getStartDate());

                // Fetch team members for the project
                List<User> teamMembers = userRepository.findAllByEmpProjectId(project.getId());

                List<String> teamMemberNames = new ArrayList<>();
                for (User teamMember : teamMembers) {
                    teamMemberNames.add(teamMember.getName());
                }
                projectOutDto.setTeamMembers(teamMemberNames);
                projectOutDtos.add(projectOutDto);
            }
        }

        return projectOutDtos;
    }

    public ApiResponseDto assignProject(AssignProjectDto assignProjectDto) {
        User user = userRepository.findByEmpId(assignProjectDto.getEmpId()).orElse(null);
        Optional<Project> project = projectRepository.findById(assignProjectDto.getId());
        user.setEmpManagerId(project.get().getManager().getId());
        user.setEmpProjectId(assignProjectDto.getId());
        userRepository.save(user);

        return new ApiResponseDto("Project assigned successfully");
    }


    // Check this method too for skills.
    // very very important
    public List<RegisterDto> getFilteredEmployee(FilterDto filterEmployeeDto) {
        List<User> userEntities = userRepository.findAllByRole(Role.EMPLOYEE);
        List<RegisterDto> registerDtos = new ArrayList<RegisterDto>();
        for (User employee : userEntities) {
            RegisterDto empDto = new RegisterDto();
           empDto.setName(employee.getName());
           empDto.setEmail(employee.getEmail());
           empDto.setEmpId(employee.getEmpId());
           empDto.setDesignation(employee.getDesignation());
           empDto.setContactNo(employee.getContactNo());
           empDto.setDob(employee.getDob());
           empDto.setDoj(employee.getDoj());
           empDto.setLocation(employee.getLocation());
            Set<Long> skillIds = employee.getAssignedSkills().stream()
                    .map(Skills::getId)
                    .collect(Collectors.toSet());
           empDto.setAssignedSkills(skillIds);

           if(employee.getEmpManagerId() == null) {
              empDto.setProjectName("N/A");
              empDto.setEmpProjectId(employee.getEmpProjectId());
           }else {
               Project project = projectRepository
                       .findById(employee.getEmpProjectId()).get();
               empDto.setProjectName(project.getProjectName());
               empDto.setEmpProjectId(employee.getEmpProjectId());
           }
           User manager = userRepository.findById(employee.getEmpManagerId()).orElse(null);
           empDto.setManagerName(manager.getName());
           if (filterEmployeeDto.getSkills() == null || filterEmployeeDto.getSkills().isEmpty()) {
               if (filterEmployeeDto.getChecked() && empDto.getProjectName().equals("N/A")) {
                   registerDtos.add(empDto);
               } else if (!filterEmployeeDto.getChecked()) {
                   registerDtos.add(empDto);
               }

           }else {
               boolean hasMatchingSkills = empDto.getAssignedSkills().stream()
                       .anyMatch(skill -> filterEmployeeDto.getSkills().contains(skill));

               if (filterEmployeeDto.getChecked() && hasMatchingSkills && empDto.getProjectName().equals("N/A")) {
                   registerDtos.add(empDto);
               } else if (!filterEmployeeDto.getChecked() && hasMatchingSkills) {
                   registerDtos.add(empDto);

               }
           }
           }
        return registerDtos;

    }

    public void unassignProject(String empId) {
        User employee = userRepository.findByEmpId(empId).orElse(null);
        User admin = userRepository.findByRole(Role.ADMIN).orElse(null);
        employee.setEmpProjectId(null);
        employee.setEmpManagerId(admin.getId());
        userRepository.save(employee);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public SkillsOutDto getEmployeeId(final Long id) {
        User employee = userRepository.findById(id).orElse(null);
        SkillsOutDto skillsOutDto = new SkillsOutDto();
        skillsOutDto.setAssignedSkills(employee.getAssignedSkills());
        return skillsOutDto;

    }

    // Servic for Update Employee
}

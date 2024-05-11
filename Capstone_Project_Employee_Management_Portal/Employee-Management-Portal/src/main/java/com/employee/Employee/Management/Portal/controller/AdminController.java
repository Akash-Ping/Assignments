package com.employee.Employee.Management.Portal.controller;


import com.employee.Employee.Management.Portal.dto.*;
import com.employee.Employee.Management.Portal.entity.Skills;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import com.employee.Employee.Management.Portal.service.AdminService;
import com.employee.Employee.Management.Portal.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private AdminService adminService;


    @PostMapping("/register")
    public final ApiResponseDto registerAdmin(@RequestBody final RegisterDto registerDto) {
        ApiResponseDto response = registerService.adminRegister(registerDto);
        return response;
    }

    @PostMapping("/login")
    public final LoginOutDto loginUser(@RequestBody final LoginInDto loginInDto) {
        LoginOutDto loginResponse = registerService.userLogin(loginInDto);
        return loginResponse;
    }

    @GetMapping("/all")
    public final List<User> getAllEmployee(){
        List<User> users = registerService.getUserDetails();
        return users;
    }

    @GetMapping("/allSkills")
    public final List<Skills> getAllSkills(){
        return registerService.getAllSkills();
    }

    @PostMapping("/addUser")
    public final ApiResponseDto addUser(@RequestBody final RegisterDto registerDto) {
        ApiResponseDto response = registerService.addUser(registerDto);
        return response;
    }

    @GetMapping("/getAllEmployees")
    public List<RegisterOutDto> getAllEmployees() {
        List<RegisterOutDto> employees = adminService.getAllEmployee();
        return employees;
    }

    @GetMapping("/Employees/{id}")
    public SkillsOutDto getEmployeesSkill(@PathVariable final Long id) {
        SkillsOutDto employees = adminService.getEmployeeId(id);
        return employees;
    }


    @GetMapping("/getAllEmployeesAndManagers")
    public List<RegisterOutDto> getAllEmployeesAndManagers() {
        List<RegisterOutDto> employeesAndManagers = adminService
                .getAllEmployeeAndManager();
        return employeesAndManagers;
    }


    @GetMapping("/getAllManagers")
    public List<ManagerOutDto> getAllManagers() {
        List<ManagerOutDto> allManagers = adminService.getAllManagers();
        return allManagers;
    }

    @GetMapping("/getAllManagersInfo")
    public List<ManagerInfoDto> getAllManagersInfo() {
        List<ManagerInfoDto> managerInfoList = adminService.getAllManagersInfo();
        return managerInfoList;
    }

    @PostMapping("/addProject")
    public ApiResponseDto addProject(@RequestBody ProjectDto projectDto) {
        ApiResponseDto response = adminService.addProject(projectDto);
        return response;

    }

    @GetMapping("/getAllProjects")
    public List<ProjectDto> getAllProjects() {
        List<ProjectDto> projectDtoList = adminService.getAllProjects();
        return projectDtoList;
    }

    @GetMapping("/getAllProjectsForAssign")
    public List<AssignProjectOutDto> getAllProjectsForAssign() {
        List<AssignProjectOutDto> assignProjectOutDtoList = adminService
                .getAllProjectsForAssign();
        return assignProjectOutDtoList;
    }

    @GetMapping("/getAll/project/{managerId}")
    public List<ProjectOutDto> getAllByManagerId(@PathVariable Long managerId) {
        List<ProjectOutDto> projectOutDtoList = adminService.getAllByManagerId(managerId);
        return projectOutDtoList;
    }

    @PostMapping("/assignProject")
    public ApiResponseDto assignProject(
             @RequestBody final AssignProjectDto assignProjectDto) {
        ApiResponseDto apiResponseDto = adminService
                .assignProject(assignProjectDto);
        return apiResponseDto;
    }


    @PostMapping("/filter")
    public List<RegisterDto> getFilteredEmployee(
            @RequestBody final FilterDto filterEmployeeDto) {
        List<RegisterDto> employeeList = adminService
                .getFilteredEmployee(filterEmployeeDto);
        return employeeList;

    }

    @PutMapping("/unassignProject/{empId}")
    public ApiResponseDto unassignProject(@PathVariable final String empId) {
        adminService.unassignProject(empId);
       ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("Project unassigned successfully");
        return apiResponseDto;

    }

    @DeleteMapping("/delete/{id}")
    public ApiResponseDto deleteEmployee(@PathVariable final Long id){
        adminService.deleteUser(id);
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("Employee deleted successfully");
        return apiResponseDto;

    }


        // Need delete employee controller


}

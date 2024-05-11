package com.employee.Employee.Management.Portal.service;

import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.dto.LoginInDto;
import com.employee.Employee.Management.Portal.dto.LoginOutDto;
import com.employee.Employee.Management.Portal.dto.RegisterDto;
import com.employee.Employee.Management.Portal.entity.Role;
import com.employee.Employee.Management.Portal.entity.Skills;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.repository.SkillsRepository;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SkillsRepository skillsRepository;

    public final ApiResponseDto adminRegister(final RegisterDto registerDto) {
        if (!registerDto.getEmail()
                .equalsIgnoreCase("admin@nucleusteq.com")) {
            ApiResponseDto reponse = new ApiResponseDto();
            reponse.setMessage("You are not authorized to register as admin");
            return reponse;
        }
        if (userRepository.findByRole(Role.ADMIN).isPresent()) {
            ApiResponseDto reponse = new ApiResponseDto();
            reponse.setMessage("Admin already registered");
            return reponse;
        }
        User adminUser = new User();

        adminUser.setRole(Role.ADMIN);
        adminUser.setEmpManagerId(0L);
        adminUser.setEmail(registerDto.getEmail());
        adminUser.setName(registerDto.getName());
        adminUser.setEmpId(registerDto.getEmpId());
        adminUser.setDesignation(registerDto.getDesignation());
        adminUser.setContactNo(registerDto.getContactNo());
        adminUser.setDob(registerDto.getDob());
        adminUser.setDoj(registerDto.getDoj());
        adminUser.setLocation(registerDto.getLocation());
        adminUser.setPassword(registerDto.getPassword());
        adminUser.setEmpManagerId(registerDto.getEmpManagerId());
        adminUser.setRole(adminUser.getRole());

        this.userRepository.save(adminUser);
        ApiResponseDto reponse = new ApiResponseDto();
        reponse.setMessage("Admin registered successfully");

        return reponse;

    }

    public final LoginOutDto userLogin(final LoginInDto loginInDto) {
        Optional<User> user = userRepository.findByEmail(loginInDto.getEmail());

        if (user.isPresent() && user.get().getPassword().equals(loginInDto.getPassword())) {
            LoginOutDto loginOutDto = new LoginOutDto();
            loginOutDto.setMessage("Login successful");
            loginOutDto.setName(user.get().getName());
            loginOutDto.setEmail(user.get().getEmail());
            loginOutDto.setRole(user.get().getRole());
            loginOutDto.setId(user.get().getId());
            return loginOutDto;

        } else {
            LoginOutDto loginOutDto = new LoginOutDto();
            loginOutDto.setMessage("Invalid credentials");
            return loginOutDto;

        }
    }


    public ApiResponseDto addUser(final RegisterDto addUserDto) {
        Optional<User> userRole = userRepository.findByRole(Role.ADMIN);
        if (userRole.isPresent()) {
            addUserDto.setEmpManagerId(userRole.get().getId());
        } else {
            addUserDto.setEmpManagerId(null);
        }

        ApiResponseDto response = new ApiResponseDto();
        User user = new User();
        user.setName(addUserDto.getName());
        user.setRole(addUserDto.getRole());
        user.setEmpProjectId(addUserDto.getEmpProjectId());
        user.setPassword(addUserDto.getPassword());
        user.setDob(addUserDto.getDob());
        user.setDoj(addUserDto.getDoj());
        user.setRole(addUserDto.getRole());
        user.setEmail(addUserDto.getEmail());
        user.setEmpId(addUserDto.getEmpId());
        user.setDesignation(addUserDto.getDesignation());
        user.setContactNo(addUserDto.getContactNo());
        user.setLocation(addUserDto.getLocation());
        user.setEmpManagerId(addUserDto.getEmpManagerId());

        // Fetch skills from database based on skill IDs
        List<Long> skillIds = new ArrayList<>(addUserDto.getAssignedSkills());
        List<Skills> skills = skillsRepository.findAllById(skillIds);

        // Associate skills with the user
        Set<Skills> assignedSkills = new HashSet<>(skills);
        user.setAssignedSkills(assignedSkills);

        this.userRepository.save(user);
        response.setMessage("Employee added successfully");
        return response;
    }



    public List<User> getUserDetails(){
        return userRepository.findAll();
    }

    public List<Skills> getAllSkills() {
        return skillsRepository.findAll();
    }
//    @Transactional
//    public User createUserWithSkills(String userName, List<String> skill) {
//        User user = new User();
//        user.setName(userName);
//
//        for (String skills : skill) {
//            Skills skills1 = skillsRepository.findBySkill_Name(skills)
//                    .orElseThrow(() -> new IllegalArgumentException("Skill not found with id: " + skill));
//            user.getAssignedSkills().add(skills1);
//        }
//
//        return userRepository.save(user);
//    }
}

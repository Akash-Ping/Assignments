package com.employee.Employee.Management.Portal.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "`user`")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "dob")
    private String dob;

    @Column(name = "doj")
    private String doj;

    @Column(name = "designation")
    private String designation;

    @Column(name = "email")
    private String email;

    @Column(name = "emp_id")
    private String empId;

    @Column(name = "location")
    private String location;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @JsonManagedReference
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
            name = "user_skill",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skills> assignedSkills = new HashSet<>();

    @Column(name = "emp_manager_id")
    private Long empManagerId;

    @Column(name = "emp_project_id")
    private Long empProjectId;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Project> projects;


    public User() {
        super();
    }

//    public User(Long id, String name, String contactNo, String dob, String doj, String designation, String email, String empId, String location, String password, Role role, List<Skills> assignedSkills, Long empManagerId, Long empProjectId) {
//        this.id = id;
//        this.name = name;
//        this.contactNo = contactNo;
//        this.dob = dob;
//        this.doj = doj;
//        this.designation = designation;
//        this.email = email;
//        this.empId = empId;
//        this.location = location;
//        this.password = password;
//        this.role = role;
//        this.assignedSkills = assignedSkills;
//        this.EmpManagerId = empManagerId;
//        this.EmpProjectId = empProjectId;
//    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getContactNo() {
//        return contactNo;
//    }
//
//    public void setContactNo(String contactNo) {
//        this.contactNo = contactNo;
//    }
//
//    public String getDob() {
//        return dob;
//    }
//
//    public void setDob(String dob) {
//        this.dob = dob;
//    }
//
//    public String getDoj() {
//        return doj;
//    }
//
//    public void setDoj(String doj) {
//        this.doj = doj;
//    }
//
//    public String getDesignation() {
//        return designation;
//    }
//
//    public void setDesignation(String designation) {
//        this.designation = designation;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getEmpId() {
//        return empId;
//    }
//
//    public void setEmpId(String empId) {
//        this.empId = empId;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public Set<Skills> getAssignedSkills() {
//        return assignedSkills;
//    }
//
//    public void setAssignedSkills(Set<Skills> assignedSkills) {
//        this.assignedSkills = assignedSkills;
//    }
//
//    public Long getEmpManagerId() {
//        return empManagerId;
//    }
//
//    public void setEmpManagerId(Long empManagerId) {
//        empManagerId = empManagerId;
//    }
//
//    public Long getEmpProjectId() {
//        return empProjectId;
//    }
//
//    public void setEmpProjectId(Long empProjectId) {
//        empProjectId = empProjectId;
//    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class FilterDto {

    private List<String> skills;

    private boolean checked;

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "FilterDto [skills=" + skills + ", checked=" + checked + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterDto filterDto = (FilterDto) o;
        return checked == filterDto.checked && Objects.equals(skills, filterDto.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skills, checked);
    }

    public List<String> getSkills() {
        if (skills != null) {
            return new ArrayList<>(skills);
        } else {
            return new ArrayList<>();
        }
    }

    public void setSkills(List<String> skills) {
        this.skills = new ArrayList<>(skills);
    }
}

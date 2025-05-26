package domain;

import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @Column(length=6)
    private String empId;
    @Column(length = 10, nullable = false)
    private String empName;

    //@OneToOne             //- fetch type : EAGER
    //@OneToMany            //- fetch type : Lazy
    @ManyToOne //- fetch type : EAGER , 즉시로딩
    @JoinColumn(name="dept_id")
    private Department department;
    private String joinDate;
    private long salary;

    public Employee() {
    }

    public Employee(String empId, String empName, Department department, String joinDate, long salary) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.joinDate = joinDate;
        this.salary = salary;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", department=" + department +
                ", joinDate='" + joinDate + '\'' +
                ", salary=" + salary +
                '}';
    }
}

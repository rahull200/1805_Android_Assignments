package codebind.example.assign_12_webclient.recyclerdata;

public class Layout {
    private String first_name;
    private String last_name;
    private Integer salary;
    private Integer emp_code;

    public Layout(String first_name, String last_name, Integer salary, Integer emp_code) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.salary = salary;
        this.emp_code = emp_code;
    }

    public String getFirst_name() {
        return first_name;
    }


    public String getLast_name() {
        return last_name;
    }


    public Integer getSalary() {
        return salary;
    }


    public Integer getEmp_code() {
        return emp_code;
    }

}

package pro.sky.gots.coursework1;

import java.text.DecimalFormat;

import static java.lang.System.out;

/** Class Employee, который содержит информацию о Ф.И.О.,
    отделе и зарплате сотрудника.
    Отделы для простоты должны быть названы от 1 до 5.
 **/
public class Employee {
    private static int nextId;
    static private DecimalFormat salaryFormat = new DecimalFormat("###,###,##0.00");
    private  int id;
    private String fullName;
    private int departmentId;
    private double salary;

    public static String formatSalary(double salary) {
        return salaryFormat.format(salary) + " руб.";
    }

    @Override
    public String toString() {
        return  id + ". "
                + fullName + ": "
                + departmentId + "-й отдел, зарплата: "
                +  formatSalary(salary);
    }

    public String getFormattedSalary() {
        return formatSalary(salary);
    }
    public Employee(String FIO, int departmentId, double salary) {
        id = ++nextId;
        this.fullName = FIO;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public double getSalary() {
        return salary;
    }

    /**
     *
     * @return String contains all the information about employee except a department id.
     */
    public String toShortString() {
        return id + ". " + fullName + " Зарплата: " + getFormattedSalary();
    }
}

package pro.sky.gots.coursework1;

import static java.lang.System.out;

public class Main {
    static final boolean isBasicTask = false;
    static final boolean isIncreasedComplexityTask = false;
    static final boolean isVeryDifficultTask = true;

    static EmployeeBook employeeBook = new EmployeeBook();

    public static void main(String[] args) {
        out.println("Курсовая работа # 1. \"Информация о сотрудниках компании\".\n");

        employeeBook.recruitEmployees();

        if (isBasicTask) {
            doBasicTask();
        }
        if (isIncreasedComplexityTask) {
            doIncreasedComplexityTask();
        }

        if (isVeryDifficultTask) {
            doVeryDifficultTask();
        }

    }

    private static void doVeryDifficultTask() {
        employeeBook.printEmployeesList();
        employeeBook.removeEmployee("Глинка М.И.");
        employeeBook.removeEmployee(7);
        employeeBook.printEmployeesList();

        String fullNameToAdd1 = "Семёнов Н.Н.";
        String fullNameToAdd2 = "Тамм И.Е.";
        String fullNameToAdd3 = "НеДоленДобавиться А.Б.";

        out.println("addEmployee() = "
                + employeeBook.addEmployee(fullNameToAdd1, 2, EmployeeBook.generateRandomSalary()));
        out.println("addEmployee() = "
                + employeeBook.addEmployee(fullNameToAdd2, 2, EmployeeBook.generateRandomSalary()));
        out.println("addEmployee() = "
                + employeeBook.addEmployee(fullNameToAdd3, 2, EmployeeBook.generateRandomSalary()));
        out.println();

        employeeBook.printEmployeesByDepartments();
        employeeBook.printEmployeesList();

        employeeBook.changeDepartment(fullNameToAdd1, 1);
        employeeBook.changeSalary(fullNameToAdd2, 430_000.00);
        employeeBook.changeSalary(fullNameToAdd3, -10_000.00);

        employeeBook.printEmployeesList();
    }

    private static void doBasicTask() {
        employeeBook.printEmployeesList();
        employeeBook.printMonthlyPayroll();
        employeeBook.printMinWageEmployeeInfo();
        employeeBook.printMaxWageEmployeeInfo();
        employeeBook.printAverageSalary();
        employeeBook.printFullNamesList();
    }

    private static void doIncreasedComplexityTask() {

        employeeBook.printEmployeesList();
        employeeBook.indexSalary(employeeBook.generateRandomSalaryIndexingPercentage());
        employeeBook.printEmployeesList();

        int departmentId;
        do {
            departmentId = employeeBook.generateRandomDepartmentId();
        } while (employeeBook.isEmpty(departmentId));
        employeeBook.printMinWageEmployee(departmentId);
        employeeBook.printMaxWageEmployee(departmentId);
        employeeBook.printAverageSalary(departmentId);
        employeeBook.printMonthlyPayroll(departmentId);
        employeeBook.printEmployeesList(departmentId);
        employeeBook.indexSalary(departmentId, employeeBook.generateRandomSalaryIndexingPercentage());
        employeeBook.printEmployeesList(departmentId);
        employeeBook.printAverageSalary(departmentId);
        employeeBook.printMonthlyPayroll(departmentId);
        double averageSalary = employeeBook.calcAverageSalary();
        employeeBook.printEmployeesWithSalaryLessThan(averageSalary);
        employeeBook.printEmployeesWithSalaryGreaterOrEqualTo(averageSalary);
        employeeBook.printEmployeesWithSalaryLessThan(0);
    }
}
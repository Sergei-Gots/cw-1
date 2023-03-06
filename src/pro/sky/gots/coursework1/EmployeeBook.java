package pro.sky.gots.coursework1;

import java.util.Random;

import static java.lang.System.out;

public class EmployeeBook {
    private static final int EMPLOYEES_COUNT = 10;
    private static final int DEPARTMENT_COUNT = 5;
    private static final Random random = new Random();
    private final Employee[] employees;

    public EmployeeBook() {
        employees = new Employee[EMPLOYEES_COUNT];
    }

    public boolean addEmployee(String fullName, int departmentId, double salary) {
        checkDepartmentId(departmentId);
        return addEmployee(new Employee(fullName, departmentId, salary));
    }

    public boolean addEmployee(Employee employee) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                return true;
            }
        }
        return false;
    }

    public boolean removeEmployee(String fullName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getFullName().equals(fullName)) {
                employees[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean removeEmployee(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId() == id) {
                employees[i] = null;
                return true;
            }
        }
        return false;
    }

    public Employee getEmployee(String fullName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getFullName().equals(fullName)) {
                return employees[i];
            }
        }
        return null;
    }

    public Employee changeSalary(String fullName, double newSalary) {
        Employee employee = getEmployee(fullName);
        if (employee == null) {
            out.println("null");
            return null;
        }
        employee.setSalary(newSalary);
        return employee;
    }

    public void printEmployeesByDepartments() {
        out.println("Списки сотрудников компании по отделам.");
        for (int i = 1; i <= DEPARTMENT_COUNT; i++) {
            if(!isEmpty(i)) {
                printEmployeesList(i);
            } else {
                out.println("Сотрудников в отделе на данный момент нет.\n");
            }
        }
    }

    public Employee changeDepartment(String fullName, int newDepartmentId) {
        checkDepartmentId(newDepartmentId);
        Employee employee = getEmployee(fullName);
        if (employee == null) {
            return null;
        }
        employee.setDepartmentId(newDepartmentId);
        return employee;
    }
    public static int generateRandomSalaryIndexingPercentage() {
        int minPercentage = 10;
        return minPercentage + random.nextInt(20);
    }

    private static void checkDepartmentId(int departmentId) {
        if (departmentId < 1 || departmentId > DEPARTMENT_COUNT) {
            throw new IllegalArgumentException("Задан несуществующий номер отдела: " + departmentId);
        }
    }

    static double generateRandomSalary() {
        return 230_000 + 8_000 * random.nextInt(8);
    }

    static int generateRandomDepartmentId() {
        return random.nextInt(DEPARTMENT_COUNT) + 1;
    }

    public boolean isEmpty(int departmentId) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartmentId() == departmentId) {
                return false;
            }
        }
        return true;
    }

    public void printAverageSalary(int departmentId) {
        checkDepartmentId(departmentId);
        out.print("Средняя зарплата в " + departmentId + "-м отделе составляет "
                + Employee.formatSalary(calcAverageSalary(departmentId)) + "\n\n");
    }

    public void printMaxWageEmployee(int departmentId) {
        Employee employee = findMaxWageEmployee(departmentId);
        out.println("Сотрудник с максимальной зарплатой в " + departmentId + "-м отделе:");
        out.print("\t" + employee.getFullName());
        out.print(" Зарплата составляет " + employee.getFormattedSalary());
        out.print("\n\n");
    }

    public void printMinWageEmployee(int departmentId) {
        Employee employee = findMinWageEmployee(departmentId);
        out.println("Сотрудник с минимальной зарплатой в " + departmentId + "-м отделе:");
        out.print("\t" + employee.getFullName());
        out.print(" Зарплата составляет " + employee.getFormattedSalary());
        out.print("\n\n");
    }

    public void printEmployeesWithSalaryGreaterOrEqualTo(double targetSalary) {
        out.println("Список сотрудников с зарплатой, равной или превышающей " + Employee.formatSalary(targetSalary) + ':');
        boolean areFound = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getSalary() >= targetSalary) {
                areFound = true;
                out.println(employees[i].toShortString());
            }
        }
        if (!areFound) {
            out.println("\tтаковых сотрудников не найдено.");
        }
        out.println();
    }

    public void printEmployeesWithSalaryLessThan(double targetSalary) {
        out.println("Список сотрудников с зарплатой, меньшей чем " + Employee.formatSalary(targetSalary) + ':');
        boolean areFound = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getSalary() < targetSalary) {
                out.println(employees[i].toShortString());
            }
        }
        if (!areFound) {
            out.println("\tтаковых сотрудников не найдено.");
        }
        out.println();
    }

    public void printEmployeesList(int departmentId) {
        checkDepartmentId(departmentId);
        out.println("Список сотрудников " + departmentId + "-го отдела:");
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && departmentId == employees[i].getDepartmentId()) {
                out.println(employees[i].toShortString());
            }
        }
        out.println();
    }

    public void indexSalary(int departmentId, int salaryIndexationPercentage) {
        out.println("Индексация зарплаты.");
        checkDepartmentId(departmentId);
        out.print("Производится индексация зарплаты для всех сотрудников " + departmentId + "-го отдела.");
        out.println("Увеличение составит " + salaryIndexationPercentage + "%.");

        double salaryMultiplier = (100.0 + salaryIndexationPercentage) / 100.0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && departmentId == employees[i].getDepartmentId()) {
                employees[i].setSalary(employees[i].getSalary() * salaryMultiplier);
            }

        }
        out.println();
    }

    public void indexSalary(int salaryIndexationPercentage) {
        out.println("Индексация зарплаты.");
        out.print("Производится индексация зарплаты для всех сотрудников компании.");
        out.println("Увеличение составит " + salaryIndexationPercentage + "%.");

        double salaryMultiplier = (100.0 + salaryIndexationPercentage) / 100.0;
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null) {
                employees[i].setSalary(employees[i].getSalary() * salaryMultiplier);
            }
        }

        out.println();
    }

    public double calcAverageSalary(int departmentId) {
        double sum = 0;
        int count = 0;
        checkDepartmentId(departmentId);

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartmentId() == departmentId) {
                count++;
                sum += employees[i].getSalary();
            }
        }
        return sum / count;
    }

    public Employee findMaxWageEmployee(int departmentId) {
        double maxWage = Double.MIN_VALUE;
        Employee employee = null;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartmentId() == departmentId
                    && employees[i].getSalary() > maxWage) {
                maxWage = employees[i].getSalary();
                employee = employees[i];
            }
        }
        return employee;
    }

    public Employee findMinWageEmployee(int departmentId) {
        double minWage = Double.MAX_VALUE;
        Employee employee = null;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null
                    && employees[i].getDepartmentId() == departmentId
                    && employees[i].getSalary() < minWage) {
                minWage = employees[i].getSalary();
                employee = employees[i];
            }
        }
        return employee;
    }

    public void printAverageSalary() {
        out.println("Средняя зарплата по компании составляет " + Employee.formatSalary(calcAverageSalary()));
        out.println();
    }

    public void printMaxWageEmployeeInfo() {
        out.print("Сотрудник с максимальной зарплатой:\n\t");
        out.println(employees[findMaxWageEmployeeIndex()]);
        out.println();
    }

    public void printMinWageEmployeeInfo() {
        out.print("Сотрудник с минимальной зарплатой:\n\t");
        out.println(employees[findMinWageEmployeeIndex()]);
        out.println();
    }

    public void printMonthlyPayroll() {
        out.println("Cумма затрат на зарплаты в компании за месяц составляет "
                + Employee.formatSalary(calcMonthlyPayroll()));
        out.println();
    }

    public void printMonthlyPayroll(int departmentId) {
        out.println("Cумма затрат на зарплаты в " + departmentId + "-м отделе за месяц составляет "
                + Employee.formatSalary(calcMonthlyPayroll(departmentId)));
        out.println();
    }

    public void printFullNamesList() {
        out.println("Список сотрудников:");
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                out.println(employees[i].getId() + ". " + employees[i].getFullName());
            }
        }
        out.println();
    }

    public double calcAverageSalary() {
        return calcMonthlyPayroll() / employees.length;

    }

    public int findMinWageEmployeeIndex() {
        double minWage = Double.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && minWage > employees[i].getSalary()) {
                minWage = employees[i].getSalary();
                index = i;
            }
        }
        return index;
    }

    public int findMaxWageEmployeeIndex() {
        double maxWage = Double.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && maxWage < employees[i].getSalary()) {
                maxWage = employees[i].getSalary();
                index = i;
            }
        }
        return index;
    }

    public double calcMonthlyPayroll(int departmentId) {
        checkDepartmentId(departmentId);
        double sum = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartmentId() == departmentId) {
                sum += employees[i].getSalary();
            }
        }
        return sum;
    }

    public double calcMonthlyPayroll() {
        double sum = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                sum += employees[i].getSalary();
            }
        }
        return sum;
    }

    public void printEmployeesList() {
        out.println("Сводка по сотрудникам:");
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                out.println(employees[i]);
            }
        }
        out.println();
    }

    public void recruitEmployees() {
        employees[0] = new Employee("Майков-Никитин А.Н.", generateRandomDepartmentId(), generateRandomSalary());
        employees[1] = new Employee("Чехов А.П.", generateRandomDepartmentId(), generateRandomSalary());
        employees[2] = new Employee("Нефёдов-Эрьзя C.Д.", generateRandomDepartmentId(), generateRandomSalary());
        employees[3] = new Employee("Понятов А.М.", generateRandomDepartmentId(), generateRandomSalary());
        employees[4] = new Employee("Майков-Никитин А.Н.", generateRandomDepartmentId(), generateRandomSalary());
        employees[5] = new Employee("Глинка М.И.", generateRandomDepartmentId(), generateRandomSalary());
        employees[6] = new Employee("Павлов И.П.", generateRandomDepartmentId(), generateRandomSalary());
        employees[7] = new Employee("Ландау Л.Д.", generateRandomDepartmentId(), generateRandomSalary());
        employees[8] = new Employee("Рахманинов С.В.", generateRandomDepartmentId(), generateRandomSalary());
        employees[9] = new Employee("Черенков П.А.", generateRandomDepartmentId(), generateRandomSalary());

    }
}
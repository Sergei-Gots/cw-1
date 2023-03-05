package pro.sky.gots.coursework1;

import java.util.Random;

import static java.lang.System.out;

public class Main {
    private static int EMPLOYEES_COUNT = 10;
    private static int DEPARTMENT_COUNT = 5;

    private static Employee employees[];
    private static Random random = new Random();

    static {
        employees = new Employee[EMPLOYEES_COUNT];
    }

    public static void main(String[] args) {
        out.println("Курсовая работа # 1. \"Информация о сотрудниках компании\".\n");

        recruitEmployees();
        printInfo();
        printMonthlyPayroll();
        printMinWageEmployeeInfo();
        printMaxWageEmployeeInfo();
        printAverageSalary();
        printFullNamesList();

    }

    private static void printAverageSalary() {
        out.println("Средняя зарплата по компании составляет " + Employee.formatSalary(calcAverageWage()));
        out.println();
    }

    private static void printMaxWageEmployeeInfo() {
        out.print("Сотрудник с максимальной зарплатой:\n\t");
        out.println(employees[findMaxWageEmployeeIndex()]);
        out.println();
    }

    private static void printMinWageEmployeeInfo() {
        out.print("Сотрудник с минимальной зарплатой:\n\t");
        out.println(employees[findMinWageEmployeeIndex()]);
        out.println();
    }

    private static void printMonthlyPayroll() {
        out.println("Cумма затрат на зарплаты в месяц составляет "
                + Employee.formatSalary(calcMonthlyPayroll()));
        out.println();
    }

    private static void printFullNamesList() {
        out.println("Список сотрудников:");
        for (int i = 0; i < employees.length; i++) {
            out.println(employees[i].getId() + ". " + employees[i].getFullName());
        }
        out.println();
    }

    private static double calcAverageWage() {
        return calcMonthlyPayroll() / employees.length;

    }

    private static int findMinWageEmployeeIndex() {
        double minWage = Double.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < employees.length; i++) {
            if (minWage > employees[i].getSalary()) {
                minWage = employees[i].getSalary();
                index = i;
            }
            ;
        }
        return index;
    }

    private static int findMaxWageEmployeeIndex() {
        double maxWage = Double.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < employees.length; i++) {
            if (maxWage < employees[i].getSalary()) {
                maxWage = employees[i].getSalary();
                index = i;
            }
            ;
        }
        return index;
    }

    private static double calcMonthlyPayroll() {
        double sum = 0;
        for (int i = 0; i < employees.length; i++) {
            sum += employees[i].getSalary();
        }
        return sum;
    }

    private static void printInfo() {
        out.println("Сводка по сотрудникам:");
        for (int i = 0; i < employees.length; i++) {
            out.println(employees[i]);
        }
        out.println();
    }

    private static void recruitEmployees() {
        employees[0] = new Employee("Майков-Никитин А.Н.", assignDepartment(), assignSalary());
        employees[1] = new Employee("Чехов А.П.", assignDepartment(), assignSalary());
        employees[2] = new Employee("Нефёдов-Эрьзя C.Д.", assignDepartment(), assignSalary());
        employees[3] = new Employee("Понятов А.М.", assignDepartment(), assignSalary());
        employees[4] = new Employee("Майков-Никитин А.Н.", assignDepartment(), assignSalary());
        employees[5] = new Employee("Глинка М.И.", assignDepartment(), assignSalary());
        employees[6] = new Employee("Павлов И.П.", assignDepartment(), assignSalary());
        employees[7] = new Employee("Ландау Л.Д.", assignDepartment(), assignSalary());
        employees[8] = new Employee("Рахманинов С.В.", assignDepartment(), assignSalary());
        employees[9] = new Employee("Черенков П.А.", assignDepartment(), assignSalary());

    }

    private static double assignSalary() {
        return 230_000 + 8_000 * random.nextInt(8);
    }

    private static int assignDepartment() {
        return random.nextInt(DEPARTMENT_COUNT) + 1;
    }
}
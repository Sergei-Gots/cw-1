package pro.sky.gots.coursework1;

import java.util.Random;

import static java.lang.System.out;

public class Main {
    static final boolean isBasicTask = false;
    static final boolean isIncreasedComplexityTask = true;
    private static int EMPLOYEES_COUNT = 10;
    private static int DEPARTMENT_COUNT = 5;
    private static Employee employees[];
    private static Random random = new Random();

    static {
        employees = new Employee[EMPLOYEES_COUNT];
    }

    {
        printAverageSalary();
    }

    public static void main(String[] args) {
        out.println("Курсовая работа # 1. \"Информация о сотрудниках компании\".\n");
        recruitEmployees();
        if (isBasicTask) {
            basicTask();
        }
        if (isIncreasedComplexityTask) {
            increasedComplexityTask();
        }

    }

    /**
     * - **Повышенная сложность**
     * <p>
     * Создать дополнительные статические методы для решения следующих задач.
     * <p>
     * 1. Проиндексировать зарплату (вызвать изменение зарплат у всех сотрудников на величину аргумента в %).
     * 2. Получить в качестве параметра номер отдела (1–5) и найти (всего 6 методов):
     * 1. Сотрудника с минимальной зарплатой.
     * 2. Сотрудника с максимальной зарплатой.
     * 3. Сумму затрат на зарплату по отделу.
     * 4. Среднюю зарплату по отделу (учесть, что количество людей в отделе отличается от employees.length).
     * 5. Проиндексировать зарплату всех сотрудников отдела на процент, который приходит в качестве параметра.
     * 6. Напечатать всех сотрудников отдела (все данные, кроме отдела).
     * 3. Получить в качестве параметра число и найти:
     * 1. Всех сотрудников с зарплатой меньше числа (вывести id, Ф. И. О. и зарплатой в консоль).
     * 2. Всех сотрудников с зарплатой больше (или равно) числа (вывести id, Ф. И. О. и зарплатой в консоль).
     * <p>
     * - Критерии оценки
     * <p>
     * – С помощью **шести** методов можно вывести данные:
     * <p>
     * - сотрудник с минимальной зарплатой,
     * - сотрудник с максимальной зарплатой,
     * - сумма затрат на зарплату по отделу,
     * - средняя зарплата по отделу,
     * - индексация зарплаты на %,
     * - печать всех данных всех сотрудников одного отдела, кроме номера отдела.
     * <p>
     * – Реализован параметр поиска всех сотрудников с зарплатой больше определенного числа, которое можно указать.
     * <p>
     * – Реализован параметр поиска всех сотрудников с зарплатой меньше определенного числа, которое можно указать.
     * <p>
     * – Программа работает корректно при изменении любых данных о сотрудниках и выводит верный результат.
     **/
    private static void increasedComplexityTask() {

        printEmployeesList();
        indexSalary(generateRandomSalaryIndexingPercentage());
        printEmployeesList();

        int departmentId;
        do {
            departmentId = generateRandomDepartmentId();
        } while (isEmpty(departmentId));
        printMinWageEmployee(departmentId);
        printMaxWageEmployee(departmentId);
        printAverageSalary(departmentId);
        printEmployeesList(departmentId);
        indexSalary(departmentId, generateRandomSalaryIndexingPercentage());
        printEmployeesList(departmentId);
        double averageSalary = calcAverageSalary();
        printEmployeesWithSalaryLessThan(averageSalary);
        printEmployeesWithSalaryGreaterOrEqualTo(averageSalary);
        printEmployeesWithSalaryLessThan(0);
    }

    private static boolean isEmpty(int departmentId) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartmentId() == departmentId) {
                return false;
            }
        }
        return true;
    }

    private static int generateRandomSalaryIndexingPercentage() {
        int minPercentage = 10;
        return minPercentage + random.nextInt(20);
    }

    private static void printAverageSalary(int departmentId) {
        checkDepartmentId(departmentId);
        out.print("Средняя зарплата в " + departmentId + "-м отделе составляет "
                + Employee.formatSalary(calcAverageSalary(departmentId)) + "\n\n");
    }

    private static void printMaxWageEmployee(int departmentId) {
        Employee employee = findMaxWageEmployee(departmentId);
        out.println("Сотрудник с максимальной зарплатой в " + departmentId + "-м отделе:");
        out.print("\t" + employee.getFullName());
        out.print(" Зарплата составляет " + employee.getFormattedSalary());
        out.print("\n\n");
    }

    private static void printMinWageEmployee(int departmentId) {
        Employee employee = findMinWageEmployee(departmentId);
        out.println("Сотрудник с минимальной зарплатой в " + departmentId + "-м отделе:");
        out.print("\t" + employee.getFullName());
        out.print(" Зарплата составляет " + employee.getFormattedSalary());
        out.print("\n\n");
    }


    private static void printEmployeesWithSalaryGreaterOrEqualTo(double targetSalary) {
        out.println("Список сотрудников с зарплатой, равной или превышающей " + Employee.formatSalary(targetSalary) + ':');
        boolean areFound = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getSalary() >= targetSalary) {
                areFound = true;
                out.println(employees[i].toShortString());
            }
        }
        if(!areFound) {
            out.println("\tтаковых сотрудников не найдено.");
        }
        out.println();
    }

    private static void printEmployeesWithSalaryLessThan(double targetSalary) {
        out.println("Список сотрудников с зарплатой, меньшей чем " + Employee.formatSalary(targetSalary) + ':');
        boolean areFound = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getSalary() < targetSalary) {
                out.println(employees[i].toShortString());
            }
        }
        if(!areFound) {
            out.println("\tтаковых сотрудников не найдено.");
        }
        out.println();
    }

    private static void printEmployeesList(int departmentId) {
        checkDepartmentId(departmentId);
        out.println("Список сотрудников " + departmentId + "-го отдела:");
        for (int i = 0; i < employees.length; i++) {
            if (departmentId == employees[i].getDepartmentId()) {
                out.println(employees[i].toShortString());
            }
        }
        out.println();
    }

    private static void indexSalary(int departmentId, int salaryIndexationPercentage) {
        out.println("Индексация зарплаты.");
        checkDepartmentId(departmentId);
        out.print("Производится индексация зарплаты для всех сотрудников " + departmentId + "-го отдела.");
        out.println("Увеличение составит " + salaryIndexationPercentage + "%.");

        double salaryMultiplier = (100.0 + salaryIndexationPercentage) / 100.0;
        for (int i = 0; i < employees.length; i++) {
            if (departmentId == employees[i].getDepartmentId()) {
                employees[i].setSalary(employees[i].getSalary() * salaryMultiplier);
            }

        }
        out.println();
    }

    private static void indexSalary(int salaryIndexationPercentage) {
        out.println("Индексация зарплаты.");
        out.print("Производится индексация зарплаты для всех сотрудников компании.");
        out.println("Увеличение составит " + salaryIndexationPercentage + "%.");

        double salaryMultiplier = (100.0 + salaryIndexationPercentage) / 100.0;
        for (int i = 0; i < employees.length; i++) {
            employees[i].setSalary(employees[i].getSalary() * salaryMultiplier);
        }

        out.println();
    }


    private static void checkDepartmentId(int departmentId) {
        if (departmentId < 1 || departmentId > DEPARTMENT_COUNT) {
            throw new IllegalArgumentException("Задан несуществующий номер отдела: " + departmentId);
        }
    }

    private static double calcAverageSalary(int departmentId) {
        double sum = 0;
        int count = 0;
        checkDepartmentId(departmentId);

        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartmentId() == departmentId) {
                count++;
                sum += employees[i].getSalary();
            }
        }
        return sum / count;
    }

    private static Employee findMaxWageEmployee(int departmentId) {
        double maxWage = Double.MIN_VALUE;
        Employee employee = null;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartmentId() == departmentId
                    && employees[i].getSalary() > maxWage) {
                maxWage = employees[i].getSalary();
                employee = employees[i];
            }
        }
        return employee;
    }

    private static Employee findMinWageEmployee(int departmentId) {
        double minWage = Double.MAX_VALUE;
        Employee employee = null;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartmentId() == departmentId
                    && employees[i].getSalary() < minWage) {
                minWage = employees[i].getSalary();
                employee = employees[i];
            }
        }
        return employee;
    }

    private static void basicTask() {
        printEmployeesList();
        printMonthlyPayroll();
        printMinWageEmployeeInfo();
        printMaxWageEmployeeInfo();
        printAverageSalary();
        printFullNamesList();
    }

    private static void printAverageSalary() {
        out.println("Средняя зарплата по компании составляет " + Employee.formatSalary(calcAverageSalary()));
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

    private static double calcAverageSalary() {
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

    private static void printEmployeesList() {
        out.println("Сводка по сотрудникам:");
        for (int i = 0; i < employees.length; i++) {
            out.println(employees[i]);
        }
        out.println();
    }

    private static void recruitEmployees() {
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

    private static double generateRandomSalary() {
        return 230_000 + 8_000 * random.nextInt(8);
    }

    private static int generateRandomDepartmentId() {
        return random.nextInt(DEPARTMENT_COUNT) + 1;
    }
}
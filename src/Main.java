import java.util.Scanner;
import java.util.InputMismatchException;


public class Main {
    private static FenwickTree<Long> tree;
    private static final Operations<Long> SUM_OPERATION = new SumOperation();
    private static int arraySize = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("1. Создать дерево Фенвика");
            System.out.println("2. Показать дерево Фенвика");
            System.out.println("3. Обновить элемент");
            System.out.println("4. Вывести префиксную сумму");
            System.out.println("5. Вывести сумму на отрезке");
            System.out.println("6. Закрыть программу");
            try {
                choice = scanner.nextInt();
                if (choice < 1 || choice > 6) {
                    System.out.println("Введите число от 1 до 6");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка. Введено неверное число");
                scanner.next();
                choice = 0;
            }

            switch (choice) {
                case 1:
                    try {
                        arraySize = scanner.nextInt();
                        if (arraySize <= 0) {
                            System.out.println("Размер массива должен быть положительным.");
                            break;
                        }

                        System.out.println("Введите длину массива");
                        int n = scanner.nextInt();
                        tree = new FenwickTree<>(n, SUM_OPERATION);
                        System.out.print("Введите " + arraySize + " целых чисел (Long) через пробел: ");
                        Long[] initialArr = new Long[arraySize];
                        for (int i = 0; i < arraySize; i++) {
                            initialArr[i] = scanner.nextLong();
                        }

                        tree.build(initialArr);
                    } catch (InputMismatchException e) {
                        System.out.println("Ошибка. введено число за пределами диапазона");
                        scanner.nextLine();
                    } catch (NegativeArraySizeException e) {
                        System.out.println("Ошибка. введено отрицательное число");
                    }
                    break;

                case 2:
                    if (tree != null) {
                        tree.printTree();
                    } else {
                        System.out.println("Нет существующего дерева");
                    }
                    break;

                case 3:
                    if (tree != null) {
                        try {
                            System.out.println("Введите индекс элемента для замены");
                            int index = scanner.nextInt();
                            if (index >= 0 && index < tree.getSize()) {
                                System.out.println("Введите значение");
                                long delta = scanner.nextInt();
                                tree.update(index, delta);
                            }
                            else {
                                System.out.println("Не существует ячейки с данным индексом");
                            }
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Введено некорректное значение");
                            scanner.next();
                        }
                    } else {
                        System.out.println("Нет существующего дерева");
                    }
                    break;

                case 4:
                    if (tree != null) {
                        System.out.println("Введите индекс для нахождения префиксной суммы");
                        int index = scanner.nextInt();
                        System.out.println("Префиксная сумма:" + tree.getPrefixSum(index));
                    }
                    else {
                        System.out.println("Нет существующего дерева");
                    }
                    break;
                case 5:
                    if (tree != null) {
                        try {
                            System.out.println("Введите индекс левого элемента");
                            int left = scanner.nextInt();
                            System.out.println("Введите индекс правого элемента");
                            int right = scanner.nextInt();
                            if ((left >= 0 && left < tree.getSize()) && (right >= 0 && right < tree.getSize())) {
                                System.out.println("Сумма на отрезке [" + left + ":" + right + "] = " + tree.getRangeSum(left, right));
                            } else {
                                System.out.println("Не существует ячейки с данным индексом");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введено некорректное значение");
                            scanner.next();
                        }
                    }
                    else {
                        System.out.println("Нет существующего дерева");
                    }
                    break;
                case 6:
                    System.out.println("Завершение работы");
            }
        }
        while (choice != 6);
    }
}

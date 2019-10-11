package com.javarush.task.task24.task2405;

/* 
Black box
*/

/*
1. Восстанови логику метода someAction для поля solutionAction.
2. Пример вывода смотри в комментарии к методу main.

Не изменяй метод main!
*/

public class Solution implements Action {
    public static int countActionObjects;
    private int param;

    private Action solutionAction = new Action() {
        private void method() {
            for (int i = 5; i >= 1; i--) {
                System.out.println(i);
            }
        }

        public void someAction() {
            if (param > 0) {
                method();
                new FirstClass() {
                    @Override
                    public Action getDependantAction() {
                        super.someAction();
                        return null;
                    }
                }.someAction();
                new SecondClass().someAction();
                param = 0;
                System.out.println(SecondClass.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM + param);
            } else {
                new SecondClass().someAction();
                System.out.println(SecondClass.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM + param);
            }
        }
    };

    public Solution(int param) {
        this.param = param;
    }

    @Override
    public void someAction() {
        solutionAction.someAction();
    }

    /**
     * 5
     * 4
     * 3
     * 2
     * 1
     * class FirstClass, method someAction
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = 0
     * Count of created Action objects is 2
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = -1
     * Count of created Action objects is 3
     */
    public static void main(String[] args) {
        Solution solution = new Solution(5);
        solution.someAction();
        System.out.println("Count of created Action objects is " + countActionObjects);

        solution = new Solution(-1);
        solution.someAction();
        System.out.println("Count of created Action objects is " + countActionObjects);
    }
}

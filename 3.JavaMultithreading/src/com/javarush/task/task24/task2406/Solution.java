package com.javarush.task.task24.task2406;

import java.math.BigDecimal;

/* 
Наследование от внутреннего класса
*/

/*
Внутри класса Solution создай 2 внутренних public класса Apt3Bedroom, BigHall.
Унаследуй их от Apartments и Hall.
*/

public class Solution {
    public class Building {
        public class Hall extends Building {
            private BigDecimal square;

            public Hall(BigDecimal square) {
                this.square = square;
            }

            public Hall() {

            }
        }

        public class Apartments extends Building {
        }
    }

    public class Apt3Bedroom extends Building.Apartments {
        public Apt3Bedroom (Apartments apartments) {
            apartments.super();
        }
    }

    public class BigHall extends Building.Hall {
        public BigHall (Hall hall) {
            hall.super();
        }
    }

    public static void main(String[] args) {

    }
}

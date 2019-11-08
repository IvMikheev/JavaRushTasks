package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "shop")
@XmlRootElement
public class Shop {

    @XmlElement(name = "goods")
    public Goods goods;

    @XmlElement(name = "count")
    public int count = 12;

    @XmlElement(name = "profit")
    public double profit = 123.4;

    @XmlElement(name = "secretData")
    public String[] secretData = new String[5];

    public Shop() {
    }

    @XmlType(name = "goods")
    @XmlRootElement
    public static class Goods {

        @XmlAttribute(name = "names")
        public List<String> names = new ArrayList<>(2);
    }
}

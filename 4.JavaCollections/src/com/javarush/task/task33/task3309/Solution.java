package com.javarush.task.task33.task3309;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.StringWriter;

@XmlType(name = "solution")
@XmlRootElement
public class Solution {
    @XmlElement(name = "name")
    public String name = "solName";
    @XmlElement(name = "weight")
    public int weight = 1;
    @XmlElement(name = "name")
    public String name2 = "solName2";

    public Solution() {
    }

    public static String toXmlWithComment(Object obj, String tagName, String comment) throws Exception {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, writer);
        StringBuilder sb = new StringBuilder();
        String regex = ".*(<" + tagName + ">|<" + tagName + "/>).*";
        try (BufferedReader reader = new BufferedReader(new StringReader(writer.toString()))) {
            String str;
            while ((str = reader.readLine()) != null) {
                if (str.matches(regex)) {
                    sb.append("<!--").append(comment).append("-->\n");
                }
                sb.append(str).append("\n");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        System.out.println(toXmlWithComment(solution, "name", "comment"));
    }
}

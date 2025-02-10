package Generics;

import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
public class Generic {

    // Élimination des conversions. L'extrait de code suivant sans génériques nécessite une conversion :

    public void NoGenerics(){
        List list = new ArrayList();
        list.add("hello");
        String s = (String) list.get(0);

    }

    // Lorsqu'il est réécrit pour utiliser des génériques, le code ne nécessite pas de casting :
 public void withGenerics(){
     List<String> list = new ArrayList<String>();
     list.add("hello");
     String s = list.get(0);   // no cast

 }
}

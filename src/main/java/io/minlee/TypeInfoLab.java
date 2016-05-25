package io.minlee;

import sun.rmi.rmic.iiop.InterfaceType;

import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by minlee on 5/25/16.
 */
public class TypeInfoLab {

    public static boolean classImplementsInterface(Object error, String thisInterface){

        Class cls = null;
        try {
            cls = Class.forName(thisInterface);
        }
        catch(ClassNotFoundException ex) {
        }
        if(cls.isInstance(error)){
            return true;
        }
        return false;
    }

    public static String listAllMembers(Object error){
        String allFields = "";
        Field[] members = error.getClass().getDeclaredFields();
        for(Field entry : members){
            allFields += entry+ "\n";
        }
        return allFields;
    }

    public static String getClassHierarchy(Object error) {
        ArrayList<String> hierarchy = new ArrayList<String>();
        String output = "";
        Class classes = error.getClass();
        while (classes!=null){
            hierarchy.add(classes.getName());
            classes = classes.getSuperclass();
        }
        int count = 0;
        for(int i = hierarchy.size()-1 ; i >= 0 ; i--){
            for(int j = 0 ; j < count ; j++){
                output+="\t";
            }
            output+=hierarchy.get(i)+"\n";
            count++;
        }
        return output;
    }

    public static ArrayList instantiateClassHierarchy(Object error){
        ArrayList<Object> hierarchy = new ArrayList<Object>();
        Class classes = error.getClass();
        while (classes!=null){
            try {
                hierarchy.add(classes.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            classes = classes.getSuperclass();
        }
        return hierarchy;
    }
}

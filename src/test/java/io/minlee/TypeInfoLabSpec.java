package io.minlee;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Created by minlee on 5/25/16.
 */
public class TypeInfoLabSpec {

    @Test
    public void classImplementsInterfaceTest(){
        assertTrue(TypeInfoLab.classImplementsInterface( new Error(), "java.io.Serializable"));
        assertTrue(TypeInfoLab.classImplementsInterface(new HashMap<String,String>(),"java.lang.Cloneable"));
    }

    @Test
    public void listAllMembersTest(){
        String expectedValue = "static final long java.lang.Error.serialVersionUID\n";
        String actualValue = TypeInfoLab.listAllMembers(new Error());
        assertEquals(expectedValue,actualValue);
    }

    @Test
    public void getClassHierarchyTest(){
        String expectedHierarchy = "java.lang.Object\n\tjava.lang.Throwable\n\t\tjava.lang.Error\n\t\t\tjava.lang.AssertionError\n";
        String actualHierarchy = TypeInfoLab.getClassHierarchy(new AssertionError());
        assertEquals(expectedHierarchy,actualHierarchy);
    }
    @Test
    public void instantiateClassHierarchyTest(){
        ArrayList<Object> actualList = TypeInfoLab.instantiateClassHierarchy(new Throwable());
        assertTrue(actualList.get(0) instanceof Throwable);
        assertTrue(actualList.get(1) instanceof Object);

    }
}

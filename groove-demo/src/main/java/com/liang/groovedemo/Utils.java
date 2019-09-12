package com.liang.groovedemo;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Utils {

    public void complie2Class(File file) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable units = fileMgr.getJavaFileObjects(file);
        JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
        t.call();
        try {
            fileMgr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadClass(String className,File file) {
        String fileUrl = "file:/" + file.getParent();
        System.out.println(fileUrl);
        try {
            URL[] urls = new URL[]{new URL(fileUrl)};
            URLClassLoader ul = new URLClassLoader(urls, ClassLoader.getSystemClassLoader());
            Class c = ul.loadClass(getClass().getPackage().getName() + "." + className);
            System.out.println(c.newInstance().getClass().getName());
            Object o = c.newInstance();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }



}

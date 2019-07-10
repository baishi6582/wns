package com.woniu.findjar.service;

import java.io.File;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.woniu.findjar.vo.ClassPath;

/**
 * @author woniu
 * @date 2019/07/09 23:19
 */

public class FindJar {

    public static void FindClassInLocalSystem(String path, String classname, List<ClassPath> list) {
        if (path.charAt(path.length() - 1) != '\\') {
            path += '\\';
        }
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Error: Path not Existed! Please Check it out!");
            return;
        }
        String[] filelist = file.list();
        for (int i = 0; i < filelist.length; i++) {
            File temp = new File(path + filelist[i]);
            if ((temp.isDirectory() && !temp.isHidden() && temp.exists())) {
                FindClassInLocalSystem(path + filelist[i], classname, list);
            } else {
                if (filelist[i].endsWith("jar")) {
                    try {
                        JarFile jarfile = new JarFile(path + filelist[i]);
                        Enumeration<JarEntry> e = jarfile.entries();
                        while (e.hasMoreElements()) {
                            JarEntry entry = (JarEntry)e.nextElement();
                            String name = entry.getName();
                            if (name.equals(classname)) {
                                ClassPath classPath = new ClassPath(list.size() + 1, path + filelist[i]);
                                list.add(classPath);
                            }
                        }
                    } catch (Exception eee) {
                        eee.printStackTrace();
                    }
                }
            }
        }
    }
}

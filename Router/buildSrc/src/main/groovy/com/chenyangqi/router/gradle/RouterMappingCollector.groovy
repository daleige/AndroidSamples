package com.chenyangqi.router.gradle

import java.util.jar.JarEntry
import java.util.jar.JarFile

class RouterMappingCollector {
    private static final String PACKAGE_NAME = "com/chenyangqi/app/mapping"
    private static final String CLASS_NAME_PREFIX = "RouterMapping_"
    private static final String CLASS_FILE_SUFFIX = ".class"

    private final Set<String> mappingClassName = new HashSet<>()

    Set<String> getMappingClassName() {
        return mappingClassName
    }

    /**
     * 收集目录中的RouterMapping_xxx.class文件
     * @param classFile
     */
    void collector(File classFile) {
        if (classFile == null || !classFile.exists()) return
        if (classFile.isFile()) {
            if (classFile.absolutePath.contains(PACKAGE_NAME)
                    && classFile.name.startsWith(CLASS_NAME_PREFIX)
                    && classFile.name.endsWith(CLASS_FILE_SUFFIX)) {
                String className = classFile.name.replace(CLASS_FILE_SUFFIX, "")
                mappingClassName.add(className)
            }
        } else {
            //是目录 则递归调用
            classFile.listFiles().each {
                collect()
            }
        }
    }

    /**
     * 收集jar包中的RouterMapping_xxx
     */
    void collectorFromJarFile(File jarFile) {
        Enumeration enumeration = new JarFile(jarFile).entries()
        while (enumeration.hasMoreElements()) {
            JarEntry jarEntry = (JarEntry) enumeration.nextElement()
            String entryName = jarEntry.getName()
            if (entryName.contains(PACKAGE_NAME)
                    && entryName.contains(CLASS_NAME_PREFIX)
                    && entryName.contains(CLASS_FILE_SUFFIX)) {
                String className = entryName
                        .replace(PACKAGE_NAME, "")
                        .replace("/", "")
                        .replace(CLASS_FILE_SUFFIX, "")
                mappingClassName.add(className)
            }
        }
    }
}
package com.chenyangqi.router.gradle

import java.util.jar.JarEntry
import java.util.jar.JarFile

class RouterMappingCollector {
    //TODO 这里存在不同系统目录分隔符不一致的问题，所以不能简单的通过字符串去匹配包名，考虑通过正则表达式去实现
    private static final String PACKAGE_NAME = 'com/chenyangqi/app/mapping'
    private static final String CLASS_NAME_PREFIX = 'RouterMapping_'
    private static final String CLASS_FILE_SUFFIX = '.class'

    private final Set<String> mappingClassNames = new HashSet<>()

    /**
     * 获取收集好的映射表类名
     * @return
     */
    Set<String> getMappingClassName() {
        return mappingClassNames;
    }

    /**
     * 收集class文件或者class文件目录中的映射表类。
     * @param classFile
     */
    void collect(File classFile) {
        if (classFile == null || !classFile.exists()) return
        if (classFile.isFile()) {
            if (classFile.name.startsWith(CLASS_NAME_PREFIX)
                    && classFile.name.endsWith(CLASS_FILE_SUFFIX)) {
                String className =
                        classFile.name.replace(CLASS_FILE_SUFFIX, "")
                mappingClassNames.add(className)
            }
        } else {
            classFile.listFiles().each {
                collect(it)
            }
        }
    }

    /**
     * 收集JAR包中的目标类
     * @param jarFile
     */
    void collectFromJarFile(File jarFile) {

        Enumeration enumeration = new JarFile(jarFile).entries()

        while (enumeration.hasMoreElements()) {
            JarEntry jarEntry = (JarEntry) enumeration.nextElement()
            String entryName = jarEntry.getName()
            if (entryName.contains(CLASS_NAME_PREFIX)
                    && entryName.contains(CLASS_FILE_SUFFIX)) {
                println("---->" + PACKAGE_NAME)
                String className = entryName
                        .replace(PACKAGE_NAME, "")
                        .replace("/", "")
                        .replace(CLASS_FILE_SUFFIX, "")
                mappingClassNames.add(className)
            }
        }
    }
}

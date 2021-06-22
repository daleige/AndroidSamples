package com.chenyangqi.router.processor;

import com.chenyangqi.router.annotations.Destination;
import com.google.auto.service.AutoService;

import java.io.Writer;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executors;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * @author : ChenYangQi
 * date   : 6/20/21 12:01
 * desc   :Destination的注解处理器
 */
@AutoService(Processor.class)
public class DestinationProcessor extends AbstractProcessor {
    private final String TAG = getClass().getSimpleName();

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (roundEnvironment.processingOver()) {
            System.out.println(TAG + "  >>> processingOver...");
            return false;
        }
        System.out.println(TAG + "  >>> start process...");
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(Destination.class);
        System.out.println(TAG + " >>> all elementsAnnotatedWith count size=" + elementsAnnotatedWith.size());
        if (elementsAnnotatedWith.size() < 1) {
            return false;
        }

        //生产自动生产的类名
        String className = "RouterMapping_" + System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("package com.chenyangqi.app.mapping;\n\n");
        builder.append("import java.util.HashMap;\n");
        builder.append("import java.util.Map;\n\n");
        builder.append("public class ").append(className).append(" {\n\n");
        builder.append("    public static Map<String, String> get() {\n");
        builder.append("        Map<String, String> mapping = new HashMap<>();\n");

        for (Element element : elementsAnnotatedWith) {
            final TypeElement typeElement = (TypeElement) element;
            final Destination destination = typeElement.getAnnotation(Destination.class);
            if (destination == null) {
                continue;
            }
            final String url = destination.url();
            final String description = destination.description();
            final String realPath = typeElement.getQualifiedName().toString();
            System.out.println(TAG + " >>>  url:" + url);
            System.out.println(TAG + " >>>  description:" + description);
            System.out.println(TAG + " >>>  realPath:" + realPath);

            builder.append("        mapping.put(")
                    .append("\"")
                    .append(url)
                    .append("\", ")
                    .append("\"")
                    .append(realPath)
                    .append("\");\n");
        }

        builder.append("        return mapping;\n");
        builder.append("    }\n");
        builder.append("}\n");

        String mappingFullClassName = "com.chenyangqi.app.mapping." + className;
        System.out.println(TAG + " >>> mappingFullClassName：" + mappingFullClassName);
        System.out.println(TAG + " >>> class content：\n" + builder);

        //写入StringBuilder到存放RouterMapping的目录中
        try {
            JavaFileObject source = processingEnv.getFiler().createSourceFile(mappingFullClassName);
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
            System.out.println(TAG + " >>> create file " + mappingFullClassName + " success");
        } catch (Exception e) {
            throw new RuntimeException("Error while create file:" + mappingFullClassName, e);
        }

        System.out.println(TAG + " >>> end process...");
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        System.out.println(TAG + " >>> run fun getSupportedAnnotationTypes() ");
        return Collections.singleton(Destination.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        System.out.println(TAG + " >>> run fun getSupportedSourceVersion() ");
        return SourceVersion.RELEASE_8;
    }
}

package com.chenyangqi.router.processor;

import com.chenyangqi.router.annotations.Destination;
import com.google.auto.service.AutoService;

import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * @author : ChenYangQi
 * date   : 6/20/21 12:01
 * desc   :Destination的注解处理器
 */
@AutoService(Processor.class)
public class DestinationProcessor extends AbstractProcessor {
    private final String TAG = "DestinationProcessor";

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println(TAG + "  >>> start process...");
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(Destination.class);
        System.out.println(TAG + " >>> all elementsAnnotatedWith count size=" + elementsAnnotatedWith.size());
        if (elementsAnnotatedWith.size() < 1) {
            return false;
        }
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
        }
        System.out.println(TAG + " >>> end process...");
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(
                Destination.class.getCanonicalName()
        );
    }

//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return SourceVersion.RELEASE_8;
//    }
}

package com.example.lib;

import com.example.apt_annotation.BindView;
//import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

//自动生成META-INF/services/javax.annotation.processing.Processor文件（在gradle版本5.0以上有时存在兼容性问题，可手动创建）
//@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {


    private Messager messager;
    private Elements elementUtils;
    private Map<String, ClassCreatorProxy> mProxyMap = new HashMap<>();

    //初始化
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        elementUtils = processingEnv.getElementUtils();
    }


    //指定这个注解处理器是处理哪个注解的。
    @Override
    public Set<String> getSupportedAnnotationTypes() {

//        方法               值
//        getName            my.ExternalClassConfig
//        getCanonicalName   my.ExternalClassConfig
//        getSimpleName      ExternalClassConfig
//        getName            my.ExternalClassConfig$InternalConfig
//        getCanonicalName   my.ExternalClassConfig.InternalConfig
//        getSimpleName      InternalConfig


        LinkedHashSet<String> linkedHashSet = new LinkedHashSet();
        String canonicalName = BindView.class.getCanonicalName();
        linkedHashSet.add(canonicalName);
        return linkedHashSet;
    }


    //指定Java使用的版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        //return super.getSupportedSourceVersion();
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.NOTE, "开始扫描注解");
        //得到所有注解
        Set<? extends Element> allElements = roundEnv.getElementsAnnotatedWith(BindView.class);
        for (Element element : allElements) {
            VariableElement variableElement = (VariableElement) element;
            TypeElement classElement = (TypeElement) variableElement.getEnclosingElement();
            String fullClassName = classElement.getQualifiedName().toString();
            ClassCreatorProxy creatorProxy = mProxyMap.get(fullClassName);
            if (creatorProxy == null) {
                creatorProxy = new ClassCreatorProxy(elementUtils, classElement);
                mProxyMap.put(fullClassName, creatorProxy);
            }

            BindView annotation = variableElement.getAnnotation(BindView.class);
            int id = annotation.value();
            creatorProxy.putElement(id, variableElement);
        }

        //通过遍历mProxyMap,创建Java文件
        for (String key : mProxyMap.keySet()) {
            ClassCreatorProxy proxyInfo = mProxyMap.get(key);
            try {
                messager.printMessage(Diagnostic.Kind.NOTE, " --> create " + proxyInfo.getProxyClassFullName());
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(proxyInfo.getProxyClassFullName(), proxyInfo.getTypeElement());
                Writer writer = jfo.openWriter();
                writer.write(proxyInfo.generateJavaCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                messager.printMessage(Diagnostic.Kind.NOTE, " --> create " + proxyInfo.getProxyClassFullName() + "error");
            }
        }

        messager.printMessage(Diagnostic.Kind.NOTE, "process finish ...");
        return true;



    }
}

package com.chenyangqi.router.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class RouterPlugin implements Plugin<Project> {

    //注入插件逻辑
    @Override
    void apply(Project project) {
        println("int RouterPlugin,apply from ${project.name}")
        //注册扩展参数
        project.getExtensions().create("router", RouterExtension)
        //当配置阶段结束时获取设置的wikiDir
        project.afterEvaluate {
            RouterExtension extension = project["router"]
            println("设置的保存wikiDir路径：${extension.wikiDir}")
        }
    }
}
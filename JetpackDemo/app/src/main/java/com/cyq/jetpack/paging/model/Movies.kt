package com.cyq.jetpack.paging.model

/**
 * @author : ChenYangQi
 * date   : 2020/10/11 22:41
 * desc   : 电影列表数据类
 */
data class Movies(
    val count: Int,
    val start: Int,
    val subjects: List<Subject>,
    val total: Int
)

data class Subject(
    val id: Int,
    val images: Images,
    val title: String,
    val year: Int
)

data class Images(
    val large: String
)
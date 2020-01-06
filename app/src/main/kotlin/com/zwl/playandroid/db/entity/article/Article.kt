package com.zwl.playandroid.db.entity.article

import androidx.room.Entity
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.zwl.playandroid.db.converters.TagConverter
import java.io.Serializable

/**
 * 注意：除了文字标题，链接，其他字段都可能为null，一定要注意布局下发 null 时的显示情况。
 */
@Entity(tableName = "articles", primaryKeys = ["id"])
@TypeConverters(TagConverter::class)
data class Article(
    val apkLink: String?,
    val audit: Int,
    val author: String?,
    val chapterId: Int,
    val chapterName: String?,
    val collect: Boolean,
    val courseId: Int,
    val desc: String?,
    val envelopePic: String?,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String?,
    val niceShareDate: String?,
    val origin: String?,
    val prefix: String?,
    val projectLink: String?,
    val publishTime: Long,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String?,
    val superChapterId: Int,
    val superChapterName: String?,
    val tags: MutableList<Tag>?,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
) : Serializable {
    override fun toString(): String {
        return title
    }
}
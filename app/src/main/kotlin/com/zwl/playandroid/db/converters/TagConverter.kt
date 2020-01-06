package com.zwl.playandroid.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zwl.playandroid.db.User
import com.zwl.playandroid.db.entity.article.Tag

/**
 * Create: 2019-12-31 14:42
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class TagConverter {

    @TypeConverter
    fun fromTags(tags: MutableList<Tag>?): String? {
        return Gson().toJson(tags)
    }

    @TypeConverter
    fun stringToTags(string: String?): MutableList<Tag> {
        return Gson().fromJson<MutableList<Tag>>(
            string,
            object : TypeToken<MutableList<Tag>>() {}.type
        )
    }
}
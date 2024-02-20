package `in`.bpj4.newsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import `in`.bpj4.newsapp.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun  sourceToString(source: Source): String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun  stringToSource(source: String): Source{
        return source.split(",").let{
            Source(it[0], it[1])
        }
    }


}
package id.kotlin.training.movies.ext

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

const val DEFAULT_DATE = "dd MMMM yyyy"

internal inline fun <reified T : Any> clazz() = T::class.java

internal fun getDate(date: String): String {
    val format = DateTimeFormat.forPattern(DEFAULT_DATE)
    return DateTime(date).toString(format)
}

internal fun loadImage(context: Context,
                       url: String,
                       imageView: ImageView) {

    fun setMemoryCategory(context: Context) {
        Glide.get(context).setMemoryCategory(MemoryCategory.NORMAL)
    }

    setMemoryCategory(context)
    GlideApp.with(context)
            .load(url)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imageView)
}
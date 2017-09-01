package id.kotlin.training.movies.data.local

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class Movie(var title: String,
                 var desc: String,
                 var date: String,
                 var image: String,
                 var vote: Double) : Parcelable {
    companion object CREATOR : Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(desc)
        parcel.writeString(date)
        parcel.writeString(image)
        parcel.writeDouble(vote)
    }

    override fun describeContents(): Int {
        return 0
    }
}
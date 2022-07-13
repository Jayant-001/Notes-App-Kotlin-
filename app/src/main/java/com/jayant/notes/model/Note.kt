package com.jayant.notes.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val subtitle: String,
    val description: String,
    val priority: String,
    val date: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeValue(id)
        p0?.writeString(title)
        p0?.writeString(subtitle)
        p0?.writeString(description)
        p0?.writeString(priority)
        p0?.writeString(date)
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }
}
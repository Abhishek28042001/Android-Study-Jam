package com.example.asj.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "Notes")
@Parcelize
class Notes(@PrimaryKey
            var id:Int? =null,
            var title : String,
            var subTitle: String,
var notes:String, var date: String,var priority:String) : Parcelable



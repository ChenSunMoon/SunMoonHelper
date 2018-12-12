package com.sunmoon.helper.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.sunmoon.helper.model.Remind
import io.reactivex.Flowable

@Dao
interface RemindDao {
    @Query("SELECT * from remind")
    fun getAll():Flowable<List<Remind>>
}
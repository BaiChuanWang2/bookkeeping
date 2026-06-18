package com.bai.bookkeeping.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bai.bookkeeping.data.local.dao.ChatDao
import com.bai.bookkeeping.data.local.entity.ChatEntity

@Database(entities = [ChatEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}
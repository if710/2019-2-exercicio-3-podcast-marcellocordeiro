package br.ufpe.cin.android.podcast.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemFeedDAO {

    @Query("SELECT * FROM ItemFeed")
    fun getAll(): List<ItemFeed>

    @Query("SELECT * FROM ItemFeed ORDER BY pubDate DESC")
    fun getAllSorted(): LiveData<List<ItemFeed>>
    //fun getAllSorted(): DataSource.Factory<Int, ItemFeed>

    @Query("SELECT * FROM ItemFeed WHERE uid = :id")
    fun getById(id: Int): ItemFeed?

    @Query("UPDATE ItemFeed SET fileLocation = :fl, currentLength = 0 WHERE uid = :id")
    fun updateFileLocationById(id: Int, fl: String?)

    @Query("UPDATE ItemFeed SET currentLength = :cl WHERE uid = :id")
    fun updateCurrentLengthById(id: Int, cl: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg entries: ItemFeed)

    @Query("DELETE FROM ItemFeed")
    fun deleteAll()
}

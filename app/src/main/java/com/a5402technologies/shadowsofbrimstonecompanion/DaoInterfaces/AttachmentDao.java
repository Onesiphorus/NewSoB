package com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.Attachment;

import java.util.List;

@Dao
public interface AttachmentDao {
    @Insert
    void insert(Attachment attachment);

    @Query("DELETE FROM attachment_table")
    void deleteAllAttachment();

    @Query("SELECT attachment_name FROM attachment_table ORDER BY attachment_name ASC")
    LiveData<List<String>> getAllAttachmentNames();

    @Query("SELECT * FROM attachment_table ORDER BY attachment_name ASC")
    LiveData<List<Attachment>> getAllAttachment();

    @Query("SELECT * FROM attachment_table WHERE attachment_name = :name")
    LiveData<Attachment> getAttachmentByName(String name);

}

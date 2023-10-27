package com.example.data.search

import android.content.ContentResolver
import android.provider.MediaStore
import com.example.domain.usecase.search.FindAllAudioFilesContract
import javax.inject.Inject

class FindAllAudioFiles @Inject constructor(
    private val contentResolver: ContentResolver,
) : FindAllAudioFilesContract {
    override fun findAllAudioFiles(): Map<String, String> {

        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = "${MediaStore.Audio.Media.DURATION} >= ?"
        val selectionArgs = arrayOf(45000.toString())


        val music: MutableMap<String, String> = mutableMapOf()

        val projection = arrayOf(
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
        )

        val cursor = contentResolver.query(uri, projection, selection, selectionArgs, null)

        cursor?.use {
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

            while (it.moveToNext()) {
                val title = it.getString(titleColumn)
                val path = it.getString(dataColumn)
                music[title] = path
            }
        }
        return music
    }
}

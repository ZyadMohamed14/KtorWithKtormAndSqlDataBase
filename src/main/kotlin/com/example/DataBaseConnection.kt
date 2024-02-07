package com.example

import org.ktorm.database.Database
import org.ktorm.dsl.*

object DataBaseConnection {
    val database = Database.connect (
        url = "jdbc:mysql://localhost:3306/notes",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "localhost123"
    )
    fun insetIntoDataBase(){

        database.insert(NoteEntity){
            set(it.note,"Hello Benz")

        }
        database.insert(NoteEntity){

            set(it.note,"Hello frist connection")
        }
    }
    fun getDataFromDataBase() :Query {
        val notes = database.from(NoteEntity).select()

        /*
         for (rowQuery in notes){

            println("id : ${rowQuery[NoteEntity.id]} , Note : ${rowQuery[NoteEntity.note]}")
        }
         */
        return notes

    }
    fun updateData(){
        database.update(NoteEntity){
            set(it.note,"Hello Zyad")
            where { it.id eq 2 }
        }
    }
    fun deleteFormDataBase(){
        database.delete(NoteEntity){
            it.id eq 3
        }
    }
}
package com.app.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Firebase
        val db = Firebase.firestore

        // Variable name collection
        val collectionName = "Personas"

        // LEER DATOS
        db.collection(collectionName)
            .get()
            .addOnSuccessListener { query ->
                for (document in query) {
                    // document.id
                    // document.data
                    Log.i("DOC", "Documento con ID: ${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("ERROR", "No se pudo leer $exception")
            }

        // INSERTAR DATOS
        /*
        val persona = hashMapOf(
            "nombre" to "Jhon",
            "apellido" to "Alex",
            "edad" to 39
        )

        val collectionName = "Personas"

        db.collection(collectionName).add(persona).addOnSuccessListener {docRef ->
            Log.e("EXITO", "Registro exitoso del ID: ${docRef.id}")
        } .addOnFailureListener{e ->
            Log.e("ERROR", "No se pudo registrar $e")
        }
        */

        // ACTUALIZAR DATOS
        val idDocumento = "ABWcGHKCro0Jj8UMt0r4"

        val persona = hashMapOf(
            "nombre" to "Maria",
            "apellido" to "Rosa Bella",
            "edad" to 34
        )

        db.collection(collectionName)
            .document(idDocumento)
            .set(persona) // combinar el campo: (persona, SetOptions.merge())
            .addOnSuccessListener {
                Log.i("INFO", "Dato Actualizado $persona")
            } .addOnFailureListener {e ->
                Log.e("ERROR", "No se pudo actualizar $e")
            }

        // ELIMINAR DATO
        val idDoc = "xhLVUbvmJmpVRPyXSpqo"

        db.collection(collectionName)
            .document(idDoc)
            .delete()
            .addOnSuccessListener {
                Log.i("INFO", "Dato Eliminado: $persona")
            } .addOnFailureListener {e ->
                Log.e("ERROR", "No se pudo eliminar $e")
            }
    }
}
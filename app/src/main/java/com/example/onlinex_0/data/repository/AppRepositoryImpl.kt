package com.example.onlinex_0.data.repository

import android.util.Log
import com.example.onlinex_0.data.common.GameUiCommon
import com.example.onlinex_0.data.common.UserUiCommon
import com.example.onlinex_0.data.models.GameUiModel
import com.example.onlinex_0.data.models.UserUiModel
import com.example.onlinex_0.domain.AppRepository
import com.example.onlinex_0.utils.navigator.extension.setNewMap
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {
    override val userDataStateFlow = MutableStateFlow<List<UserUiCommon>>(emptyList())
    override val gameDataStateFlow = MutableStateFlow<GameUiCommon?>(null)
    override val battleFlow = MutableStateFlow<String?>("")
    val state  = MutableStateFlow(false)


    private val userPref = Firebase.database.getReference("users")
    private val gamePref = Firebase.database.getReference("games")

    private var myId = ""
    private var myName = ""
    private var gameId = ""

    init {

        userPref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val results = ArrayList<UserUiCommon>()
                snapshot.children.forEach { innerSnapshot ->
                    val uuid = innerSnapshot.key!!
                    val model = innerSnapshot.getValue<UserUiModel>()
                    if (model != null && model.name != myName && model.pairname == "secondUser") {
                        results.add(UserUiCommon(uuid, model.name))
                    }
                }
                userDataStateFlow.tryEmit(results)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        gamePref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { innerSnapshot ->
                    val uuid = innerSnapshot.key!!
                    val model = innerSnapshot.getValue<GameUiModel>()

                    if (model != null && model.secondName == myName) {
                        battleFlow.tryEmit(model.secondName)
                    }

                    if (model != null && (model.firstId == myId || model.secondId == myId)) {
                        gameDataStateFlow.tryEmit(
                            GameUiCommon(
                                uuid,
                                model.firstId,
                                model.firstName,
                                model.firstHod,
                                model.firstSign,
                                model.secondId,
                                model.secondName,
                                model.secondHod,
                                model.secondSign,
                                model.winner,
                                model.winnerName,
                                model.indexHod,
                                model.map,
                                model.hasWay
                            )
                        )
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


    override fun addUser(user: String): Flow<Result<Unit>> = callbackFlow {
        val users = UserUiModel(user)
        val uuid = userPref.push().key ?: UUID.randomUUID().toString()

        myId = uuid
        myName = user
        userPref.child(uuid).setValue(users).addOnSuccessListener {
            trySend(Result.success(Unit))
        }.addOnFailureListener {
            trySend(Result.failure(it))
        }
        awaitClose()
    }

    override fun getAllUsers(): Flow<Result<List<UserUiCommon>>> = callbackFlow {
        userPref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val results = ArrayList<UserUiCommon>()
                snapshot.children.forEach { innerSnapshot ->
                    val uuid = innerSnapshot.key!!
                    val model = innerSnapshot.getValue<UserUiModel>()

                    if (model != null && model.name != myName && model.pairname == "x") {
                        results.add(UserUiCommon(uuid, model.name))
                    }
                }
                userDataStateFlow.tryEmit(results)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    override fun attachUser(pairId: String, pairName: String): Flow<Result<Unit>> = callbackFlow {
        userPref.child(myId).setValue(UserUiModel(pairName, myName)).addOnSuccessListener {
            val uuid = userPref.push().key ?: UUID.randomUUID().toString()
            val gameModel = GameUiModel(
                uuid,
                myId,
                myName,
                true,
                "0",
                pairId,
                pairName,
                false,
                "2",
                false,
                ""
            )

            gameId = uuid
            gamePref.child(uuid).setValue(gameModel).addOnSuccessListener {
                trySend(Result.success(Unit))
            }.addOnFailureListener {
                trySend(Result.failure(it))
            }
        }
        awaitClose()
    }

    override fun updateData(dataUICommon: GameUiCommon, newMap: String): Flow<Result<Unit>> =
        callbackFlow {
            gamePref.child(dataUICommon.gameId).setValue(dataUICommon.setNewMap(newMap))
                .addOnSuccessListener {
                    trySend(Result.success(Unit))
                }.addOnFailureListener {
                    trySend(Result.failure(it))
                }

            awaitClose()
        }

    override fun removeValue() {
        gamePref.child(myId).removeValue()
    }

    override fun getName(): String = myName

    override fun removeAllData() {
        gamePref.child(myId).removeValue()
        gameDataStateFlow.value = null
        battleFlow.value = null
    }
}
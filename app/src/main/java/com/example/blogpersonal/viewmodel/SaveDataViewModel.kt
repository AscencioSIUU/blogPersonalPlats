package com.example.blogpersonal.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.blogpersonal.model.Post

class SaveDataViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    init {
        loadPosts()
    }

    fun loadPosts() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        db.collection("posts").whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    _posts.value = snapshot.documents.map { it.toObject(Post::class.java)!! }
                }
            }
    }

    fun addPost(title: String, content: String) {
        val post = mapOf(
            "title" to title,
            "content" to content,
            "userId" to FirebaseAuth.getInstance().currentUser?.uid
        )
        db.collection("posts").add(post)
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}

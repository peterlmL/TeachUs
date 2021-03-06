package com.ppcarrasco.teachus.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Pedro on 28-09-2017.
 */

public class Nodes {
    private DatabaseReference root;
    private StorageReference storage;

    public Nodes()
    {
        root = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance().getReference();
    }

    public DatabaseReference getUsers()
    {
        return root.child("users");
    }

    public DatabaseReference getProffessors()
    {
        return root.child("proffessors");
    }

    public DatabaseReference getDocuments()
    {
        return root.child("documents");
    }

    public DatabaseReference getDocIndex()
    {
        return root.child("documentsIndex");
    }

    public DatabaseReference getLikes(){ return root.child("likes"); }
    public DatabaseReference getDislikes(){ return root.child("dislikes"); }
    public DatabaseReference getLikedBy(){ return root.child("likedBy"); }

    public DatabaseReference getStudentQuestions(){ return root.child("studentQuestions");}
    public DatabaseReference getProffessorQuestions(){ return root.child("proffessorQuestions");}
    public DatabaseReference getQuestions(){return root.child("questions");}

    public StorageReference getStorageDocuments(){
        return storage.child("documents");
    }
    public StorageReference getStorageThumbnails(){
        return storage.child("thumbnails");
    }
}

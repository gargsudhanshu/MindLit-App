package com.vatsa.litapp.Helpers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.vatsa.litapp.Helpers.DBCommentsHelper;
import com.vatsa.litapp.Helpers.DBNotificationsHelper;
import com.vatsa.litapp.Helpers.DBPostsHelper;
import com.vatsa.litapp.Helpers.DBUserHelper;

import org.jetbrains.annotations.NotNull;

public class Syncer {

    FirebaseDatabase fdb;

    DBUserHelper userHelper;
    DBPostsHelper postsHelper;
    DBNotificationsHelper notificationsHelper;
    DBCommentsHelper commentsHelper;

    public Syncer(Context context) {
        fdb = FirebaseDatabase.getInstance();
        userHelper = new DBUserHelper(context);
        postsHelper = new DBPostsHelper(context);
        notificationsHelper = new DBNotificationsHelper(context);
        commentsHelper = new DBCommentsHelper(context);

        fdb.getReference().child("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                addNotificationsListener(snapshot.getKey());
                userHelper.insertUsers(
                        snapshot.child("id").getValue(String.class),
                        snapshot.child("bio").getValue(String.class),
                        snapshot.child("fullname").getValue(String.class),
                        snapshot.child("imageurl").getValue(String.class),
                        snapshot.child("username").getValue(String.class)
                );
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                userHelper.updateUsers(
                        snapshot.child("id").getValue(String.class),
                        snapshot.child("bio").getValue(String.class),
                        snapshot.child("fullname").getValue(String.class),
                        snapshot.child("imageurl").getValue(String.class),
                        snapshot.child("username").getValue(String.class)
                );
            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
                userHelper.deleteUsers(
                        snapshot.child("id").getValue(String.class)
                );
            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) { }
        });

        fdb.getReference().child("Posts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                addCommentListener(snapshot.getKey());
                postsHelper.insertPosts(
                        snapshot.child("postid").getValue(String.class),
                        snapshot.child("category").getValue(String.class),
                        snapshot.child("description").getValue(String.class),
                        snapshot.child("postimage").getValue(String.class),
                        snapshot.child("publisher").getValue(String.class)
                );

            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                postsHelper.updatePosts(
                        snapshot.child("postid").getValue(String.class),
                        snapshot.child("category").getValue(String.class),
                        snapshot.child("description").getValue(String.class),
                        snapshot.child("postimage").getValue(String.class),
                        snapshot.child("publisher").getValue(String.class)
                );
            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
                postsHelper.deletePosts(
                        snapshot.child("postid").getValue(String.class)
                );
            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) { }
        });
    }

    private void addCommentListener(String postId) {
        fdb.getReference().child("Comments").child(postId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                commentsHelper.insertComments(
                        snapshot.child("commentid").getValue(String.class),
                        snapshot.child("comment").getValue(String.class),
                        snapshot.child("publisher").getValue(String.class)
                );
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                commentsHelper.updateComments(
                        snapshot.child("commentid").getValue(String.class),
                        snapshot.child("comment").getValue(String.class),
                        snapshot.child("publisher").getValue(String.class)
                );
            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
                commentsHelper.deleteComments(
                        snapshot.child("commentid").getValue(String.class)
                );
            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) { }
        });
    }

    private void addNotificationsListener(String userId) {
        fdb.getReference().child("Notifications").child(userId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                notificationsHelper.insertNotifications(
                        snapshot.child("postid").getValue(String.class),
                        snapshot.child("userid").getValue(String.class),
                        snapshot.child("ispost").getValue(Boolean.class) ? 1 : 0,
                        snapshot.child("text").getValue(String.class)
                );
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                notificationsHelper.updateNotifications(
                        snapshot.child("postid").getValue(String.class),
                        snapshot.child("userid").getValue(String.class),
                        snapshot.child("ispost").getValue(Boolean.class) ? 1 : 0,
                        snapshot.child("text").getValue(String.class)
                );
            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
                notificationsHelper.deleteNotifications(
                        snapshot.child("postid").getValue(String.class),
                        snapshot.child("userid").getValue(String.class)
                );
            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) { }
        });
    }
}

package com.myaccountant.myaccountant.chatmodule;

/**
 * Created by dice on 5/5/16.
 */
public class ChatMessageChatModule {
    private String author;
    public String message;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private ChatMessageChatModule() {
    }


    public ChatMessageChatModule(String message, String author) {
        super();

        this.message = message;
        this.author = author;
    }
    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
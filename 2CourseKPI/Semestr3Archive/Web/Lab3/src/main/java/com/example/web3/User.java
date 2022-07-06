package com.example.web3;

public class User {
    private boolean isEditor;

    public User(boolean isEditor) {
        this.isEditor = isEditor;
    }

    public boolean isEditor() {
        return isEditor;
    }

    public void setEditor(boolean editor) {
        isEditor = editor;
    }
}

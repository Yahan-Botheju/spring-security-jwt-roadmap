package lk.spring_security.cookie_based_jwt_auth.domain.models;

public class Note {
    private Long noteId;
    private String title;
    private String content;

    private User user;

    public Note(Long noteId, String title, String content, User user) {
        this.noteId = noteId;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Long getNoteId() { return noteId; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public User getUser() { return user; }


    /* __FACTORY_METHOD__ */

    public static Note createNewNote(String title, String content, User user) {
        return new Note(null, title, content, user);
    }

    /* __UPDATE_NOTE__ */

    public void updateNoteModel(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

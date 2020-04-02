package services;

import dataaccess.NoteDB;
import java.util.Date;
import java.util.List;
import models.Notes;
import models.Users;

public class NoteService 
{
    private NoteDB noteDB;

    public NoteService() 
    {
        noteDB = new NoteDB();
    }

    public Notes get(int noteID) throws Exception 
    {
        return noteDB.getNote(noteID);
    }

    public List<Notes> getAll() throws Exception 
    {
        return noteDB.getAll();
    }

    public int update(int noteID, String title, String contents, Date dateCreated) throws Exception 
    {
        Notes note = get(noteID);
        note.setTitle(title);
        note.setContents(contents);
        note.setDatecreated(dateCreated);
        
        return noteDB.update(note);
    }

    public int delete(int noteID) throws Exception 
    {
        Notes deleteNote = noteDB.getNote(noteID);
        
        return noteDB.delete(deleteNote);
    }

    public int insert(int noteID, Date dateCreated, String title, String contents) throws Exception 
    {
        Notes note = new Notes(noteID, dateCreated, title, contents);
        note.setOwner(new Users("anne"));
        
        return noteDB.insert(note);
    }
}

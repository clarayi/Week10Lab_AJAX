package dataaccess;

import models.Notes;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class NoteDB 
{
    public int insert(Notes note)
    {
        int numOfRow = 0;
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try 
        {
            trans.begin();
            em.merge(note);
            trans.commit();
            numOfRow = 1;
        } 
        catch (Exception ex) 
        {
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
        } 
        finally 
        {
            em.close();
        }
        
        return numOfRow;
    }

    public int update(Notes note)
    {
        int numOfRow = 0;
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try 
        {
            trans.begin();
            em.merge(note);
            trans.commit();
            numOfRow = 1;
        } 
        catch (Exception ex) 
        {
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot update " + note.toString(), ex);
        } 
        finally 
        {
            em.close();
        }
        
        return numOfRow;
    }

    public List<Notes> getAll()
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try 
        {
            List<Notes> noteList = em.createNamedQuery("Notes.findAll", Notes.class).getResultList();
            return noteList;                
        } 
        finally 
        {
            em.close();
        }
    }

    public Notes getNote(int noteID)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try 
        {
            Notes note = em.find(Notes.class, noteID);
            return note;
        } 
        finally 
        {
            em.close();
        }
    }

    public int delete(Notes note)
    {
        int numOfRow = 0;
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try 
        {
            trans.begin();
            em.remove(em.merge(note));
            trans.commit();
            
            numOfRow = 1;
        } 
        catch (Exception ex) 
        {
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot delete " + note.toString(), ex);
        } 
        finally 
        {
            em.close();
        }
        
        return numOfRow;
    }
}

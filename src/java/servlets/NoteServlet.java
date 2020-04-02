/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Notes;
import services.NoteService;

/**
 *
 * @author 810783
 */
public class NoteServlet extends HttpServlet 
{
    private HttpSession session = null;
    private NoteService ns = new NoteService();
    private ArrayList<Notes> editList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        System.out.println("<<NoteServlet / In doGet method>>");
        
        session = request.getSession();
        editList.clear();
        
        List<Notes> noteList = null;       
        try 
        {
            noteList = ns.getAll();
           
            for(int i = 0; i < noteList.size(); i++)
            {
                System.out.println(noteList.get(i));
            }
            
            session.setAttribute("noteList", noteList);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("whatToDo", "Add");
        
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String action = request.getParameter("action");
        
        if(action.contains("editClicked="))
        {
            request.setAttribute("whatToDo", "Edit");
            
            editList.clear();
            session.setAttribute("editList", editList);
            
            String[] split = action.split("=");
            String noteidString = split[1];
            int noteid = Integer.parseInt(noteidString);
            
            try 
            {
                Notes editNote = ns.get(noteid);
                
                request.setAttribute("titleBox", editNote.getTitle());
                request.setAttribute("textareaBox", editNote.getContents());
                
                editList.add(editNote);
                session.setAttribute("editList", editList);
                session.setAttribute("editNoteID", editNote.getNoteid());
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("action", null);
        }
        else if(action.contains("add"))
        {
            request.setAttribute("whatToDo", "Add");
            
            int newNoteID = findNewNoteID();
            
            String title = request.getParameter("inputTitle");
            String contents = request.getParameter("inputContents");
            
            try 
            {
                int addRow = ns.insert(newNoteID, new Date(), title, contents);
                
                if(addRow == 1)
                {
                    List<Notes> newNoteList = null;       
                    try 
                    {
                        newNoteList = ns.getAll();
                        session.setAttribute("noteList", newNoteList);
                    } 
                    catch (Exception ex) 
                    {
                        Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("action", null);
        }
        else if(action.contains("delete"))
        {
            String[] split = action.split("=");
            String noteidString = split[1];
            int noteid = Integer.parseInt(noteidString);
            
            try 
            {
                int numOfRow = ns.delete(noteid);
                
                if(numOfRow == 1)
                {
                    request.setAttribute("titleBox", "Note Deleted");
                    request.setAttribute("textareaBox", "Note Deleted");
                    
                    List<Notes> noteList = null;       
                    try 
                    {
                        noteList = ns.getAll();

                        for(int i = 0; i < noteList.size(); i++)
                        {
                            System.out.println(noteList.get(i));
                        }

                        session.setAttribute("noteList", noteList);
                    } 
                    catch (Exception ex) 
                    {
                        Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("action", null);
        }
        else if(action.contains("edit="))
        {
            String[] split = action.split("=");
            String noteidString = split[1];
            int noteid = Integer.parseInt(noteidString);
            
            try 
            {
                String title = request.getParameter("inputTitle");
                String contents = request.getParameter("inputContents");
                Date dateCreated = new Date();
                
                int numOfRow = ns.update(noteid, title, contents, dateCreated);
                
                editList.clear();
                session.setAttribute("editList", editList);
                request.setAttribute("whatToDo", "Add");
                
                List<Notes> noteList = null;       
                try 
                {
                    noteList = ns.getAll();

                    for(int i = 0; i < noteList.size(); i++)
                    {
                        System.out.println(noteList.get(i));
                    }

                    session.setAttribute("noteList", noteList);
                } 
                catch (Exception ex) 
                {
                    Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("action", null);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }
    
    public int findNewNoteID()
    {
        int newNoteID = 0;
        
        List<Notes> noteList = null;       
        try 
        {
            noteList = ns.getAll();
           
            for(int i = 0; i < noteList.size(); i++)
            {
                System.out.println(noteList.get(i));
            }
            
            session.setAttribute("noteList", noteList);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0; i < noteList.size(); i++)
        {
            if(noteList.get(i).getNoteid() > newNoteID)
            {
                newNoteID = noteList.get(i).getNoteid();
            }
        }
        
        System.out.println(newNoteID);
        
        return newNoteID + 1;
    }
}

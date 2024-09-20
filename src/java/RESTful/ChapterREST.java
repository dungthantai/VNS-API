/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTful;

import Entity.Chapters;
import Session.ChaptersFacadeLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
/**
 * REST Web Service
 *
 * @author root
 */
@Path("chapter")
public class ChapterREST {
    ChaptersFacadeLocal chaptersFacade = lookupChaptersFacadeLocal();

    public ChapterREST() {
    }

    @GET
    @Produces("application/xml")
    public List<Chapters> findAllByComicId() {
        return chaptersFacade.findAll();
    }
    
    @GET
    @Path("{id}")
    @Produces("application/xml")
    public Chapters findAllByComicId(@PathParam("id") int id) {
        return chaptersFacade.find(id);
    }

    private ChaptersFacadeLocal lookupChaptersFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (ChaptersFacadeLocal) c.lookup("java:global/Core/Core-ejb/ChaptersFacade!Session.ChaptersFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTful;

import Entity.Comics;
import Session.ComicsFacadeLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("comic")
public class ComicREST {
    ComicsFacadeLocal comicsFacade = lookupComicsFacadeLocal();

    public ComicREST() {
    }

    @GET
    @Produces("application/xml")
    public List<Comics> findAll() {
        return comicsFacade.findAll();
    }
    
    @GET
    @Path("{id}")
    @Produces("application/xml")
    public Comics find(@PathParam("id") int id) {
        return comicsFacade.find(id);
    }

    private ComicsFacadeLocal lookupComicsFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ComicsFacadeLocal) c.lookup("java:global/Core/Core-ejb/ComicsFacade!Session.ComicsFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import pojos.Film;

/**
 *
 * @author Peti
 */
@ManagedBean
@SessionScoped
public class Catalog {
    private List<Film> fullFilmList;
    private List<Film> filteredFilmList;

    /**
     * Creates a new instance of Catalog
     */
    public Catalog() {
        queryFilmsFromDB();
    }
    
    private void queryFilmsFromDB() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        fullFilmList = session.createQuery("FROM Film").list();
        filteredFilmList = new ArrayList<Film>();
        for (Film f : fullFilmList) {
            filteredFilmList.add(f);
        }
        session.close();
    }

    public List<Film> getFilteredFilmList() {
        return filteredFilmList;
    }

    public void setFilteredFilmList(List<Film> filteredFilmList) {
        this.filteredFilmList = filteredFilmList;
    }
}

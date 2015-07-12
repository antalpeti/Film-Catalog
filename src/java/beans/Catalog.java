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
import pojos.Datastore;
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
    private String actSearchParameter;
    private List<String> searchParameterList;
    private String searchText;

    private static final String TITLE = "Title";
    private static final String DIRECTOR = "Director";
    private static final String TIME = "Time";
    private static final String GENRE = "Genre";
    private static final String YEAR = "Year";

    private Film newFilmIns;
    private Film editFilmIns;
    private Datastore newDatastoreIns;
    private Datastore editDatastoreIns;
    private String status;

    private List<Datastore> datastoreList;
    private List<String> datastoreCombo;
    private String actDatastore;

    /**
     * Creates a new instance of Catalog
     */
    public Catalog() {
        queryFilmsFromDB();
        datastoreCombo = new ArrayList<String>();
        queryDatastoreFromDB();
        newFilmIns = new Film();
        newDatastoreIns = new Datastore();
        searchParameterList = new ArrayList<String>();
        searchParameterList.add(TITLE);
        searchParameterList.add(DIRECTOR);
        searchParameterList.add(TIME);
        searchParameterList.add(GENRE);
        searchParameterList.add(YEAR);
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

    private void queryDatastoreFromDB() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        datastoreList = session.createQuery("FROM Datastore").list();
        session.close();
        datastoreCombo.clear();
        for (Datastore ds : datastoreList) {
            datastoreCombo.add(ds.toString());
        }
    }

    public void searchFilm() {
        filteredFilmList.clear();
        for (Film f : fullFilmList) {
            if (((TITLE.equals(actSearchParameter) && f.getTitle().indexOf(searchText) != -1)
                    || (DIRECTOR.equals(actSearchParameter) && f.getDirector().indexOf(searchText) != -1)
                    || (TIME.equals(actSearchParameter) && f.getTime().indexOf(searchText) != -1)
                    || (GENRE.equals(actSearchParameter) && f.getGenre().indexOf(searchText) != -1)
                    || (YEAR.equals(actSearchParameter) && f.getYear() == Integer.parseInt(actSearchParameter)))
                    || (searchText.isEmpty())) {
                filteredFilmList.add(f);
            }
        }
    }

    public String newFilm() {
        for (Datastore ds : datastoreList) {
            if (ds.toString().equals(actDatastore)) {
                newFilmIns.setDatastore(ds);
            }
        }
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(newFilmIns);
        session.getTransaction().commit();
        session.close();
        newFilmIns = new Film();
        status = "Addition of new film was succesful.";
        queryFilmsFromDB();
        return "newfilm2index";
    }

    public String navigateFilm(Film film) {
        editFilmIns = film;

        return "index2editfilm";
    }

    public String editFilm() {
        for (Datastore ds : datastoreList) {
            if (ds.toString().equals(actDatastore)) {
                editFilmIns.setDatastore(ds);
            }
        }
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(editFilmIns);
        session.getTransaction().commit();
        session.close();
        status = "Editon of film was succesful.";
        queryFilmsFromDB();
        return "editfilm2index";
    }

    public String newDatastore() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(newDatastoreIns);
        session.getTransaction().commit();
        session.close();
        newDatastoreIns = new Datastore();
        status = "Addition of new datastore was succesful.";
        queryDatastoreFromDB();
        return "newdatastore2datastores";
    }

    public String navigateDatastore(Datastore datastore) {
        editDatastoreIns = datastore;

        return "datastores2editdatastore";
    }

    public List<Film> getFilteredFilmList() {
        return filteredFilmList;
    }

    public void setFilteredFilmList(List<Film> filteredFilmList) {
        this.filteredFilmList = filteredFilmList;
    }

    public String getActSearchParameter() {
        return actSearchParameter;
    }

    public void setActSearchParameter(String actSearchParameter) {
        this.actSearchParameter = actSearchParameter;
    }

    public List<String> getSearchParameterList() {
        return searchParameterList;
    }

    public void setSearchParameterList(List<String> searchParameterList) {
        this.searchParameterList = searchParameterList;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Datastore> getDatastoreList() {
        return datastoreList;
    }

    public void setDatastoreList(List<Datastore> datastoreList) {
        this.datastoreList = datastoreList;
    }

    public List<String> getDatastoreCombo() {
        return datastoreCombo;
    }

    public void setDatastoreCombo(List<String> datastoreCombo) {
        this.datastoreCombo = datastoreCombo;
    }

    public String getActDatastore() {
        return actDatastore;
    }

    public void setActDatastore(String actDatastore) {
        this.actDatastore = actDatastore;
    }

    public Film getNewFilmIns() {
        return newFilmIns;
    }

    public void setNewFilmIns(Film newFilmIns) {
        this.newFilmIns = newFilmIns;
    }

    public Datastore getNewDatastoreIns() {
        return newDatastoreIns;
    }

    public void setNewDatastoreIns(Datastore newDatastoreIns) {
        this.newDatastoreIns = newDatastoreIns;
    }

    public Film getEditFilmIns() {
        return editFilmIns;
    }

    public void setEditFilmIns(Film editFilmIns) {
        this.editFilmIns = editFilmIns;
    }
}

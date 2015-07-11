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

    private Film film;
    private Datastore datastore;
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
        film = new Film();
        datastore = new Datastore();
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

    public void newFilm() {
        for (Datastore ds : datastoreList) {
            if (ds.toString().equals(actDatastore)) {
                film.setDatastore(ds);
            }
        }
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(film);
        session.getTransaction().commit();
        session.close();
        film = new Film();
        status = "Addition of new film was succesful.";
        queryFilmsFromDB();
    }

    public void newDatastore() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(datastore);
        session.getTransaction().commit();
        session.close();
        datastore = new Datastore();
        status = "Addition of new datastore was succesful.";
        queryDatastoreFromDB();
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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
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

    public Datastore getDatastore() {
        return datastore;
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }
}

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
    private String actSearchParameter;
    private List<String> searchParameterList;
    private String searchText;

    private static final String TITLE = "Title";
    private static final String DIRECTOR = "Director";
    private static final String TIME = "Time";
    private static final String GENRE = "Genre";
    private static final String YEAR = "Year";

    /**
     * Creates a new instance of Catalog
     */
    public Catalog() {
        queryFilmsFromDB();
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
}

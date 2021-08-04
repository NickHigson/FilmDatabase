package Models;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

//This statement means that class "FilmList.java" is the root-element of our example
@XmlRootElement(namespace = "Models")
public class FilmList {

    // XmLElementWrapper generates a wrapper element around XML representation
    @XmlElementWrapper(name = "filmList")
    // XmlElement sets the name of the entities
    @XmlElement(name = "film")
    public ArrayList<Film> filmList;
    private String name;

    public void setFilmList(ArrayList<Film> filmList) {
        this.filmList = filmList;
    }

    public ArrayList<Film> getFilmsList() {
        return filmList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
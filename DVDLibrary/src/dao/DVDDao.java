package dao;

import dto.DVD;

import java.util.List;

public interface DVDDao {

    DVD addDvd(String dvdId, DVD dvd) throws DVDDaoException;

    List<DVD> getAllDvds() throws DVDDaoException;

    DVD getDvd(String dvdId) throws DVDDaoException;

    DVD getDvdByTitle(String dvdTitle) throws DVDDaoException;

    DVD removeDvd(String dvdId) throws DVDDaoException;

    DVD editDvd(String dvdId, DVD dvd) throws DVDDaoException;


}

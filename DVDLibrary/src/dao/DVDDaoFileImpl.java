package dao;

import dto.DVD;

import java.io.*;
import java.util.*;

public class DVDDaoFileImpl implements DVDDao {
    public static final String DVD_FILE = "C:\\Users\\User\\IdeaProjects\\mthreeProjects\\DVDLibrary\\src\\DvdLibrary.txt";
    public static final String DELIMITER = "::";

    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD addDvd(String dvdId, DVD dvd) throws DVDDaoException {
        loadLibrary();
        DVD newDVD = dvds.put(dvdId, dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public List<DVD> getAllDvds() throws DVDDaoException {
        loadLibrary();
        return new ArrayList<DVD>(dvds.values()); //arraylist of dvds
    }

    @Override
    public DVD getDvd(String dvdId) throws DVDDaoException {
        loadLibrary();
        return dvds.get(dvdId);
    }

    @Override
    public DVD removeDvd(String dvdId) throws DVDDaoException {
        loadLibrary();
        DVD removedDvd = dvds.remove(dvdId);
        writeLibrary();
        return removedDvd;
    }

    @Override
    public DVD getDvdByTitle(String dvdTitle) throws DVDDaoException{
        loadLibrary();
        for (String currentDVD : dvds.keySet()) {
            DVD dvd = getDvd(currentDVD);
            if (dvd.getTitle().equals(dvdTitle)) {
                return dvds.get(currentDVD);
            }
        }
        return null;
    }

    @Override
    public DVD editDvd(String dvdId, DVD dvd) throws DVDDaoException{
        loadLibrary();
        DVD editDVD = dvds.replace(dvdId, dvd);
        writeLibrary();
        return editDVD;
    }


    private void loadLibrary() throws DVDDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDDaoException( //catch exception and translate to ClssRosterDaoException
                    "-_- Could not load dvd library into memory.", e);
        }

        String currentLine;

        DVD currentDvd;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDvd= unmarshallDvd(currentLine);

            dvds.put(currentDvd.getDvdId(), currentDvd);
        }
        // close scanner
        scanner.close();
    }


    private DVD unmarshallDvd(String dvdAsText){

        String[] dvdTokens = dvdAsText.split(DELIMITER);

        String dvdId = dvdTokens[0];

        DVD dvdFromFile = new DVD(dvdId);

        dvdFromFile.setTitle(dvdTokens[1]);
        dvdFromFile.setReleaseDate(dvdTokens[2]);
        dvdFromFile.setMpaaRating(dvdTokens[3]);
        dvdFromFile.setDirectorName(dvdTokens[4]);
        dvdFromFile.setStudio(dvdTokens[5]);
        dvdFromFile.setUserRating(dvdTokens[6]);


        return dvdFromFile;
    }


    private String marshallDvd(DVD aDvd){

        String dvdAsText = aDvd.getDvdId() + DELIMITER;
        dvdAsText += aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getMpaaRating() + DELIMITER;
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getUserRating() + DELIMITER;

        return dvdAsText;
    }

    public void writeLibrary() throws DVDDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DVDDaoException(
                    "Could not save dvd data.", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDvds();
        for (DVD currentDvd : dvdList) {

            dvdAsText = marshallDvd(currentDvd);
            // write the Student object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}




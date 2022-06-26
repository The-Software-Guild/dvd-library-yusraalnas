package view;

import dto.DVD;

import java.util.List;

public class DVDView {

    private UserIO io;

    public DVDView(UserIO io){
        this.io = io;
    }

    //get users value
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. Add new DVD");
        io.print("3. View a DVD");
        io.print("4. Search for a DVD");
        io.print("5. Edit a DVD's information");
        io.print("6. Delete a DVD");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public void displayAddDvdBanner() {
        io.print("=== Add DVD ===");
    }

    //get new dvd info to make one
    public DVD getNewDvdInfo() {
        String dvdId = io.readString("Please enter DVD ID");
        String title = io.readString("Please enter DVD title");
        String releaseDate = io.readString("Please enter DVD's release date");
        String mpaa = io.readString("Please enter DVD's MPAA rating");
        String director = io.readString("Please enter DVD director's name");
        String studio = io.readString("Please enter DVD's studio");
        String userRating = io.readString("Please enter your DVD rating");

        //create new student object with studentID then fill fields
        DVD currentDvd = new DVD(dvdId);
        currentDvd.setTitle(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaa);
        currentDvd.setDirectorName(director);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD successfully added.  Please hit enter to continue");
    }


    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    //List DVDs
    public void displayDvdList(List<DVD> dvdList) {
        for (DVD currentDvd : dvdList) {
            String dvdInfo = String.format("#%s : %s %s %s %s %s %s",
                    currentDvd.getDvdId(),
                    currentDvd.getTitle(),
                    currentDvd.getReleaseDate(),
                    currentDvd.getMpaaRating(),
                    currentDvd.getDirectorName(),
                    currentDvd.getStudio(),
                    currentDvd.getUserRating());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }


    public void displayDisplayDvdBanner () {
        io.print("=== Display DVD ===");
    }

    public void displayRemoveDvdBanner () {
        io.print("=== Remove DVD ===");
    }

    public String getDvdIdChoice() {
        return io.readString("Please enter the DVD ID.");
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if(dvdRecord != null){
            io.print("DVD successfully removed.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDvd(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getDvdId());
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMpaaRating());
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public String getDvdTitleChoice() {
        return io.readString("Please enter the DVD title.");
    }

    public void displayEditDvdBanner () {
        io.print("=== Edit DVD ===");
    }


    public DVD editDvdInfo(DVD currentDvd) {
        String title = io.readString("Please enter DVD title");
        String releaseDate = io.readString("Please enter DVD's release date");
        String mpaa = io.readString("Please enter DVD's MPAA rating");
        String director = io.readString("Please enter DVD director's name");
        String studio = io.readString("Please enter DVD's studio");
        String userRating = io.readString("Please enter your DVD rating");

        //create new student object with studentID then fill fields
        currentDvd.setTitle(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaa);
        currentDvd.setDirectorName(director);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    //add/edit/delete as many dvds in one session
    public String getNumberChoice() {
        return io.readString("Please enter how many DVDs you would like to do this to.");
    }


}

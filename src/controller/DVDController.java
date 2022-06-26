package controller;

import dao.DVDDao;
import dao.DVDDaoException;
import dao.DVDDaoFileImpl;
import dto.DVD;
import view.DVDView;

import java.util.List;

public class DVDController {

    private DVDView view;
    private DVDDaoFileImpl dao;

    public DVDController(DVDDaoFileImpl dao, DVDView view){
        this.dao = dao;
        this.view = view;
    }

    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        addDvd();
                        break;
                    case 3:
                        viewDvd();
                        break;
                    case 4:
                        searchDvd();
                        break;
                    case 5:
                        editDvd();
                        break;
                    case 6:
                        removeDvd();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            dao.writeLibrary();
            exitMessage();

        } catch (DVDDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    //get selection from view
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }



    private void listDvds() throws DVDDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void addDvd() throws DVDDaoException {
        view.displayAddDvdBanner();
        int numTimes = Integer.parseInt(view.getNumberChoice());

        for (int i = 1; i <= numTimes; i++) {
            view.displayAddDvdBanner();
            DVD newDvd = view.getNewDvdInfo();
            dao.addDvd(newDvd.getDvdId(), newDvd);
            view.displayCreateSuccessBanner();
        }
    }

    private void viewDvd() throws DVDDaoException {
        view.displayDisplayDvdBanner();
        String dvdId = view.getDvdIdChoice();
        DVD dvd = dao.getDvd(dvdId);
        view.displayDvd(dvd);
    }

    private void editDvd() throws DVDDaoException{
        view.displayEditDvdBanner();
        int numTimes = Integer.parseInt(view.getNumberChoice());

        for (int i = 1; i <= numTimes; i++) {
            view.displayEditDvdBanner();
            String dvdId = view.getDvdIdChoice();
            DVD dvd = dao.getDvd(dvdId);
            DVD editDvd = view.editDvdInfo(dvd);
            dao.editDvd(editDvd.getDvdId(), editDvd);
            view.displayCreateSuccessBanner();
        }
    }

    private void removeDvd() throws DVDDaoException {
        view.displayRemoveDvdBanner();
        int numTimes = Integer.parseInt(view.getNumberChoice());

        for (int i = 1; i <= numTimes; i++) {
            view.displayRemoveDvdBanner();
            String dvdId = view.getDvdIdChoice();
            DVD removedDvd = dao.removeDvd(dvdId);
            view.displayRemoveResult(removedDvd);
        }
    }

    private void searchDvd() throws DVDDaoException{
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        DVD dvd = dao.getDvdByTitle(dvdTitle);
        view.displayDvd(dvd);
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

}


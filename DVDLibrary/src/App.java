import controller.DVDController;
import dao.DVDDao;
import dao.DVDDaoFileImpl;
import view.DVDView;
import view.UserIO;
import view.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DVDView myView = new DVDView(myIo);
        DVDDaoFileImpl myDao = new DVDDaoFileImpl();
        DVDController controller = new DVDController(myDao, myView);
        controller.run();
    }
}

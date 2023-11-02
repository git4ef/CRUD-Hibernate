package ef;

import ef.util.FlywayMigrateUtil;
import ef.view.MenuView;

public class Main {
    public static void main(String[] args) {

       // FlywayMigrateUtil.migrateDB();
        MenuView menuView = new MenuView();
        menuView.runMenuView();
    }
}
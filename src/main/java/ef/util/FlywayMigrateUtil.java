package ef.util;

import org.flywaydb.core.Flyway;

public class FlywayMigrateUtil {
    public static void migrateDB(){
        Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/asd", "postgres", "postgres").load();
        flyway.migrate();
    }
}
